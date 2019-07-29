package chatwithinterest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class myserver implements Runnable {

    ServerSocket ssock;
    BufferedReader br;
    BufferedReader brn;
    PrintWriter pw;
    ArrayList<friends> al;
    tablemodel tm;

    myserver() {
        al = new ArrayList<>();
        tm = new tablemodel();

    }

    @Override
    public void run() {
        try {
            ssock = new ServerSocket(4200);
            while (true) {
                Socket sock = ssock.accept();
                new Thread(new clienthandler(sock)).start();
            }

        } catch (IOException e) {
        }

    }

    class clienthandler implements Runnable {

        Socket socket;
        DataInputStream dis;
        DataOutputStream dos;

        public clienthandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    String msg = dis.readUTF();
                    if (msg.equals("signup")) {
                        signup();
                    } else if (msg.equals("login")) {
                        login();
                    } else if (msg.equals("change password")) {
                        change_password();
                    } else if (msg.equals("search")) {
                        search();
                    } else if (msg.equals("friend request")) {
                        send_friend_requests();
                    } else if (msg.equals("pending friend requests")) {
                        pending_friend_requests();
                    } else if (msg.equals("accept request")) {
                        accept_request();
                    } else if (msg.equals("reject request")) {
                        reject_request();
                    }

                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        private void signup() {
            try {
                String email = dis.readUTF();
                String username = dis.readUTF();
                String password = dis.readUTF();
                String secques = dis.readUTF();
                String secans = dis.readUTF();
                String number = dis.readUTF();
                String interests = dis.readUTF();
                String msg = dis.readUTF();

                if (msg.equals("sending file")) {
                    String filename = dis.readUTF();
                    long size = dis.readLong();
                    String profilepath = "F:\\profile pics\\" + email + "_" + filename;
                    System.out.println(filename + " " + size);
                    FileOutputStream fos = new FileOutputStream(profilepath);
                    long count = 0;
                    byte b[] = new byte[10000];
                    while (true) {
                        int read = dis.read(b, 0, b.length);
                        fos.write(b, 0, read);
                        count = count + read;
                       
                        if (count == size) {
                            dos.writeUTF("DONE");
                            fos.flush();
                            break;
                        }
                    }
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery("select * from users where email='" + email + "'");

                    if (rs.next()) {
                        dos.writeUTF("fail");
                    } else {
                        rs.moveToInsertRow();
                        rs.updateString("email", email);
                        rs.updateString("username", username);
                        rs.updateString("password", password);
                        rs.updateString("securityques", secques);
                        rs.updateString("securityans", secans);
                        rs.updateString("contact", number);
                        rs.updateString("interests", interests);
                        rs.updateString("profile", profilepath);
                        rs.insertRow();
                        dos.writeUTF("success");
                    }
                    dos.flush();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void login() {
            try {
                String email = dis.readUTF();
                String password = dis.readUTF();
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from users where email='" + email + "' and password='" + password + "'");
                if (rs.next()) {
                    dos.writeUTF("success");
                } else {
                    dos.writeUTF("fail");
                }

            } catch (Exception e) {
            }
        }

        private void change_password() {
            try {
                String op = dis.readUTF();
                String np = dis.readUTF();
                String cp = dis.readUTF();
                String em = dis.readUTF();
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from users where email='" + em + "' ");
                if (rs.next()) {
                    rs.updateString("password", np);
                    rs.updateRow();
                    dos.writeUTF("success");
                    dos.flush();

                } else {
                    dos.writeUTF("fail");
                    dos.flush();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        private void search() {
            try {
                String value = dis.readUTF();
                String interest = dis.readUTF();
                String fromemail = dis.readUTF();
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from users where username like '%" + value + "%' "
                        + "and interests like '%" + interest + "%' and email not in(select toemail from friends where fromemail='" + fromemail + "')");
                dos.writeUTF("receive msg");
                dos.flush();
                while (rs.next()) {
                    String email = rs.getString("email");
                    String username = rs.getString("username");
                    dos.writeUTF(email);
                    dos.writeUTF(username);
                    dos.flush();
                }
                dos.writeUTF("done");
                dos.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void send_friend_requests() {
            try {
                String fromemail = dis.readUTF();
                String toemail = dis.readUTF();
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from friends");
                rs.moveToInsertRow();
                rs.updateString("fromemail", fromemail);
                rs.updateString("toemail", toemail);
                rs.insertRow();
                dos.writeUTF("done");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void pending_friend_requests() {
            try {
                String loggedinemail = dis.readUTF();
                System.out.println("email test " + loggedinemail);
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from friends where toemail='" + loggedinemail + "' and status='pending' ");

                dos.writeUTF("start");
                dos.flush();
                while (rs.next()) {

                    String fromemail = rs.getString("fromemail");
                    Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs2 = stmt2.executeQuery("select * from users where email  like '%" + fromemail + "%' ");
                    if (rs2.next()) {
                        String username = rs2.getString("username");
                        System.out.println(username + " " + fromemail);
                        dos.writeUTF(username);
                        dos.writeUTF(fromemail);
                        dos.flush();
                    }
                }

                dos.writeUTF("done");
                dos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        private void accept_request() {
            try {
                String loggedinemail = dis.readUTF();
                String friendemail = dis.readUTF();
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from friends where toemail='" + loggedinemail + "' and fromemail='" + friendemail + "' ");
                if (rs.next()) {
                    rs.updateString("status", "friends");
                    rs.updateRow();
                    dos.writeUTF("success");
                } else {
                    dos.writeUTF("fail");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        private void reject_request() {
            try {
                String loggedinemail = dis.readUTF();
                String friendemail = dis.readUTF();
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from friends where toemail='" + loggedinemail + "' and fromemail='" + friendemail + "' ");
                if (rs.next()) {
                    rs.updateString("status", "rejected");
                    rs.updateRow();
                    dos.writeUTF("success");
                } else {
                    dos.writeUTF("fail");
                }
                dos.flush();
            } catch (Exception e) {
            }
        }

    }

    class tablemodel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return al.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return al.get(rowIndex).sno;
            } else if (columnIndex == 1) {
                return al.get(rowIndex).name;
            } else {
                return al.get(rowIndex).email;
            }
        }

    }

    public static void main(String[] args) {
        myserver myserver = new myserver();
        Thread t1 = new Thread(myserver);
        t1.start();
    }

}
