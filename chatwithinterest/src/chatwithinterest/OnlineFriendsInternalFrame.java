package chatwithinterest;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class OnlineFriendsInternalFrame extends javax.swing.JInternalFrame {

    String loggedinemail;
    ArrayList<friends> al;
    tablemodel tm;
    DataOutputStream dos;
    int x,y;

    public OnlineFriendsInternalFrame(String em, DataOutputStream dos,int x,int y) {
        initComponents();
        this.x=x;
        this.y=y;
        lbOnlineBg.setSize(x, y);
        try {
            Image scaledInstance = ImageIO.read(new File(System.getProperty("user.home") + "/images/blue.jpg"))
                    .getScaledInstance(lbOnlineBg.getWidth(), lbOnlineBg.getHeight(), Image.SCALE_SMOOTH);
            lbOnlineBg.setIcon(new ImageIcon(scaledInstance));
        } catch (Exception e) {
        }
        this.dos = dos;
        loggedinemail = em;
        setTitle("online friends");
        al = new ArrayList<>();
        tm = new tablemodel();
        tbonline.setModel(tm);
        new Thread(new client()).start();
    }

    class client implements Runnable {

        Socket sock;
        DataInputStream dis;
        DataOutputStream dos;

        @Override
        public void run() {
            try {

                try {
                    sock = new Socket("127.0.0.1", 4200);
                    dis = new DataInputStream(sock.getInputStream());
                    dos = new DataOutputStream(sock.getOutputStream());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                dos.writeUTF("online friends");
                dos.writeUTF(loggedinemail);
                String msg = dis.readUTF();
                int i = 0;
                if (msg.equals("start")) {
                    while (true) {
                        String friendemail = dis.readUTF();
//                        System.out.println(friendemail + " in online friends");
                        if (friendemail.equals("done")) {
                            System.out.println("finish");
                            break;
                        }
                        i++;
                        String username = dis.readUTF();
                        friends f = new friends(i, username, friendemail);
                        al.add(f);
                        tm.fireTableDataChanged();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbonline = new javax.swing.JTable();
        startchat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbOnlineBg = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        tbonline.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "sno","name","email"
            }
        ));
        jScrollPane1.setViewportView(tbonline);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 102, 581, 300);

        startchat.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        startchat.setText("Start Chat");
        startchat.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        startchat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        startchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startchatActionPerformed(evt);
            }
        });
        getContentPane().add(startchat);
        startchat.setBounds(250, 420, 140, 50);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Online Friends");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(220, 30, 170, 40);
        getContentPane().add(lbOnlineBg);
        lbOnlineBg.setBounds(0, 0, 700, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startchatActionPerformed
        String friendemail;
        int index = tbonline.getSelectedRow();
        friendemail = al.get(index).email;
        System.out.println(friendemail);

        if (userhome.alchat.size() == 0) {
            chatwindow cw = new chatwindow(loggedinemail, friendemail, dos);
            userhome.alchat.add(cw);
            cw.setVisible(true);
            return;
        }

        for (int i = 0; i < userhome.alchat.size(); i++) {
            if (userhome.alchat.get(i).friendemail.equals(friendemail)) {
                chatwindow cwold = userhome.alchat.get(i);
                cwold.requestFocus();
                System.out.println("if");
            } else {
                chatwindow cw = new chatwindow(loggedinemail, friendemail, dos);
                userhome.alchat.add(cw);
                cw.setVisible(true);
                System.out.println("else");
            }
        }


    }//GEN-LAST:event_startchatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbOnlineBg;
    private javax.swing.JButton startchat;
    private javax.swing.JTable tbonline;
    // End of variables declaration//GEN-END:variables
}
