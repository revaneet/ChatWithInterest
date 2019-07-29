package chatwithinterest;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class serveronline extends javax.swing.JFrame {

    myserver ms;
    ArrayList<friends> al;
//    ArrayList<friends> al;
    tablemodel tm;

    public serveronline() {

        initComponents();
        setSize(665, 460);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Server");
        al = new ArrayList<>();
        tm = new tablemodel();
        tbonline.setModel(tm);
        try {
            Image scaledInstance = ImageIO.read(new File(System.getProperty("user.home") + "/images/blue.jpg"))
                    .getScaledInstance(48, 48, Image.SCALE_SMOOTH);
            setIconImage(scaledInstance);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbonline = new javax.swing.JTable();
        btstart = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jScrollPane1.setViewportView(tbonline);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 90, 640, 364);

        btstart.setText("START SERVER");
        btstart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btstartActionPerformed(evt);
            }
        });
        getContentPane().add(btstart);
        btstart.setBounds(10, 10, 640, 36);

        jLabel1.setText("Online Users....................");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 50, 640, 36);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btstartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btstartActionPerformed
        ms = new myserver();
        new Thread(ms).start();
    }//GEN-LAST:event_btstartActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(serveronline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(serveronline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(serveronline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(serveronline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new serveronline().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btstart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbonline;
    // End of variables declaration//GEN-END:variables

    class myserver implements Runnable {

        ServerSocket ssock;
        ArrayList<clienthandler> alonlineclients = new ArrayList<>();

        @Override
        public void run() {
            try {
                ssock = new ServerSocket(4200);
                System.out.println("Server started");
                while (true) {
                    Socket sock = ssock.accept();
                    new Thread(new clienthandler(sock)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        class clienthandler implements Runnable {

            Socket socket;
            DataInputStream dis;
            DataOutputStream dos;
            int i = 0;
            String email;

            public clienthandler(Socket socket) {
                this.socket = socket;
            }

            @Override
            public void run() {
                try {
                    dis = new DataInputStream(socket.getInputStream());
                    dos = new DataOutputStream(socket.getOutputStream());
//                    System.out.println("dis dos created");
                    while (true) {
                        String msg = dis.readUTF();
                        System.out.println("in online server " + msg);
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
                        } else if (msg.equals("online friends")) {
                            online_friends();
                            break;
                        } else if (msg.equals("send message")) {
                            send_message();
                        } else if (msg.equals("send old chats")) {
                            send_old_chats();
                        } else if (msg.equals("post image")) {
                            post_image();
                        } else if (msg.equals("post status")) {
                            post_status();
                        } else if (msg.equals("send stories")) {
                            send_stories();
                        } else if (msg.equals("send image story")) {
                            send_image_story();
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
                            System.out.println("Server " + count);
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
                    ResultSet rs = stmt.executeQuery("select * from users where email='" + email + "' "
                            + "and password='" + password + "'");
                    if (rs.next()) {
                        i++;
                        this.email = email;
                        friends f = new friends(i, rs.getString("username"), email);
                        al.add(f);
                        alonlineclients.add(this);
                        tm.fireTableDataChanged();
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
                    ResultSet rs = stmt.executeQuery("select * from friends where toemail='" + loggedinemail + "' "
                            + "and fromemail='" + friendemail + "' ");
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

            private void online_friends() {
                try {
                    String loggedinemail = dis.readUTF();
                    int len = 0;
                    dos.writeUTF("start");
                    while (len < al.size()) {
                        String friendemail = al.get(len).email;

                        if (friendemail.equals(loggedinemail)) {
                            len++;
                            continue;
                        }

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        String query = "select * from friends where (fromemail='" + loggedinemail + "' "
                                + "or toemail='" + loggedinemail + "') and status='friends'";
                        System.out.println(query);
                        ResultSet rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            if (rs.getString("fromemail").equals(loggedinemail)) {
                                dos.writeUTF(rs.getString("toemail"));
                            } //to be continue
                            else {
                                dos.writeUTF(rs.getString("fromemail"));
                            }

                            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs2 = stmt2.executeQuery("select * from users where email='" + friendemail + "' ");
                            if (rs2.next()) {
                                dos.writeUTF(rs2.getString("username"));
                            }

                        }
                        len++;
                    }
                    dos.writeUTF("done");
                    dos.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            private void send_message() {
                try {
                    String loggedinemail = dis.readUTF();
                    String friendemail = dis.readUTF();
                    String msg = dis.readUTF();
                    String side = dis.readUTF();
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery("select * from messages");
//                    if(rs.next()){
                    rs.moveToInsertRow();
                    rs.updateString("msgfrom", loggedinemail);
                    rs.updateString("msgto", friendemail);
                    rs.updateString("message", msg);
                    if (side.equals("smiley")) {
                        rs.updateString("msgtype", side);
                    } else {
                        rs.updateString("msgtype", "text");
                    }
                    rs.insertRow();

                    for (int i = 0; i < alonlineclients.size(); i++) {
                        clienthandler ch = alonlineclients.get(i);
                        String em = ch.email;
                        System.out.println(em + " " + friendemail + " " + (i));
                        if (em.equalsIgnoreCase(friendemail)) {
                            ch.dos.writeUTF("receive msg");
                            ch.dos.writeUTF(loggedinemail);
                            ch.dos.writeUTF(msg);
                            ch.dos.writeUTF(side);
                            System.out.println("Actual message sent");
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void send_old_chats() {
                try {
                    String loggedinemail = dis.readUTF();
                    String friendemail = dis.readUTF();

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery(" select * from messages where msgfrom='" + loggedinemail + "' "
                            + "and msgto='" + friendemail + "' or msgfrom='" + friendemail + "' and msgto='" + loggedinemail + "' ");
                    dos.writeUTF("sending old chats");
                    while (rs.next()) {
                        dos.writeUTF(rs.getString("msgfrom"));
                        dos.writeUTF(rs.getString("mid"));
                        dos.writeUTF(rs.getString("msgto"));
                        dos.writeUTF(rs.getString("message"));
                        dos.writeUTF(rs.getString("msgtype"));
                        dos.writeUTF(rs.getString("msgtype"));

                    }
                    dos.writeUTF("done");
                    dos.writeUTF(friendemail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void post_image() {
                try {
                    String loggedinemail = dis.readUTF();
                    String msg = dis.readUTF();
                    String filename = dis.readUTF();
                    long size = dis.readLong();
                    String profilepath = "C:\\Users\\admin\\StoryImages\\" + filename;
                    FileOutputStream fos = new FileOutputStream(profilepath);
                    long count = 0;
                    byte b[] = new byte[10000];
                    while (true) {
                        int read = dis.read(b, 0, b.length);
                        fos.write(b, 0, read);
                        count = count + read;

                        if (count == size) {

                            fos.flush();
                            break;
                        }
                    }
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery("select * from stories");
                    rs.moveToInsertRow();
                    rs.updateString("fromemail", loggedinemail);
                    rs.updateString("storytype", "image");
                    rs.updateString("story", profilepath);
                    rs.insertRow();

                    dos.writeUTF("imagesuccess");
                    System.out.println("image success");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void post_status() {
                try {
                    String loggedinemail = dis.readUTF();
                    String status = dis.readUTF();

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery("select * from stories");
                    rs.moveToInsertRow();
                    rs.updateString("fromemail", loggedinemail);
                    rs.updateString("storytype", "text");
                    rs.updateString("story", status);
                    rs.insertRow();
                    System.out.println("success status");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void send_stories() {
                try {
                    String loggedinemail = dis.readUTF();
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(credentials.dburlconnectivity, credentials.dbusername, credentials.dbpassword);
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    // group by groups the duplicate queries using sid from stories
                    long time = 1000 * 60 * 60 * 24;
                    Date d = new Date();
                    Timestamp t = new Timestamp(d.getTime() - time);
                    String query = " select * from friends,stories,users where (friends.fromemail='" + loggedinemail + "' "
                            + "or friends.toemail='" + loggedinemail + "') and datetime>'" + t + "' and status='friends' group by stories.sid ";
//                    System.out.println(query);
                    ResultSet rs = stmt.executeQuery(query);
                    dos.writeUTF("sending friends");
                    while (rs.next()) {
                        dos.writeUTF(rs.getString("username"));
                        dos.writeUTF(rs.getString("sid"));
                        dos.writeUTF(rs.getString("fromemail"));
                        dos.writeUTF(rs.getString("storytype"));
                        dos.writeUTF(rs.getString("story"));
                        dos.writeUTF(rs.getString("datetime"));
                    }
                    dos.writeUTF("done");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            private void send_image_story() {
                try {
                    String friendemail = dis.readUTF();
                    String path = dis.readUTF();
                    dos.writeUTF("sending");
                    byte b[] = new byte[10000];
                    long count = 0;
                    long size = new File(path).length();
                    dos.writeLong(size);
                    FileInputStream fis = new FileInputStream(new File(path));
                    while (true) {
                        int r = fis.read(b, 0, b.length);
                        dos.write(b, 0, r);
                        count = count + r;
                        if (count == size) {
                            dis.readUTF();
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        String colnames[] = {"Sr no", "Name", "Email"};

        @Override
        public String getColumnName(int column) {
            return colnames[column];
        }

    }
}
