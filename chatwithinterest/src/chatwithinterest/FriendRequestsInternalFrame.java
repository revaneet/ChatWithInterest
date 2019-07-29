package chatwithinterest;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class FriendRequestsInternalFrame extends javax.swing.JInternalFrame {

    String email;
    Socket sock;
    DataInputStream dis;
    DataOutputStream dos;
    ArrayList<friends> al;
    rqtablemodel tm;
    friends f;

    public FriendRequestsInternalFrame(String em,int x,int y) {
        initComponents();
        email = em;
        al = new ArrayList<>();
        tm = new rqtablemodel();
        tbpendingrequests.setModel(tm);
        lbReqBg.setSize(x, y);
        try {
            
            Image scaledInstance = ImageIO.read(new File(System.getProperty("user.home") + "/images/blue.jpg"))
                    .getScaledInstance(lbReqBg.getWidth(), lbReqBg.getHeight(), Image.SCALE_SMOOTH);
            lbReqBg.setIcon(new ImageIcon(scaledInstance));
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sock = new Socket("127.0.0.1", 4200);
            dis = new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());
            new Thread(new frndrequests()).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class frndrequests implements Runnable {

        @Override
        public void run() {
            try {
                dos.writeUTF("pending friend requests");
                dos.writeUTF(email);
                dos.flush();
                String start = dis.readUTF();
                if (start.equals("start")) {
                    int i = 0;
                    while (true) {
                        String username = dis.readUTF();
                        if (username.equals("done")) {
                            System.out.println("finish");
                            break;
                        } else {
                            String fromemail = dis.readUTF();
                            i++;
                            System.out.println(username+" "+fromemail);
                            f = new friends(i, username, fromemail);
                            al.add(f);

                        }
                        tm.fireTableDataChanged();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbpendingrequests = new javax.swing.JTable();
        btaccept = new javax.swing.JButton();
        btreject = new javax.swing.JButton();
        lbReqBg = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Friend Requests");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(210, 20, 180, 40);

        tbpendingrequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "sno", "name", "email"
            }
        ));
        jScrollPane1.setViewportView(tbpendingrequests);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(26, 130, 546, 310);

        btaccept.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btaccept.setText("Accept");
        btaccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btacceptActionPerformed(evt);
            }
        });
        getContentPane().add(btaccept);
        btaccept.setBounds(84, 80, 170, 40);

        btreject.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btreject.setText("Reject");
        btreject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrejectActionPerformed(evt);
            }
        });
        getContentPane().add(btreject);
        btreject.setBounds(340, 80, 180, 40);
        getContentPane().add(lbReqBg);
        lbReqBg.setBounds(0, 0, 660, 490);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btrejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrejectActionPerformed
        if(tbpendingrequests.getSelectionModel().isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "select user first");
        }
        else
        {
            int r=JOptionPane.showConfirmDialog(rootPane,"are you sure?","confirmation",JOptionPane.YES_NO_OPTION);
            if(r==JOptionPane.YES_OPTION)
            {
                try {
                   int index=tbpendingrequests.getSelectedRow();
                    String friendemail=al.get(index).email;
                    String friend=al.get(index).name;
                    dos.writeUTF("reject request");
                    dos.writeUTF(email);
                    dos.writeUTF(friendemail);
                    dos.flush();
                    String msg=dis.readUTF();
                    if (msg.equals("success"))
                    {
                        JOptionPane.showMessageDialog(rootPane,"request declined");
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }
    }//GEN-LAST:event_btrejectActionPerformed

    private void btacceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btacceptActionPerformed
        if(tbpendingrequests.getSelectionModel().isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "select user first");
        }
        else
        {
            int r=JOptionPane.showConfirmDialog(rootPane,"are you sure?","confirmation",JOptionPane.YES_NO_OPTION);
            if(r==JOptionPane.YES_OPTION)
            {
                int index=tbpendingrequests.getSelectedRow();
                String friendemail=al.get(index).email;
                String friend=al.get(index).name;
                try {
                    dos.writeUTF("accept request");
                    dos.writeUTF(email);
                    dos.writeUTF(friendemail);
                    dos.flush();
                    String msg=dis.readUTF();
                    if(msg.equals("success"))
                    {
                        JOptionPane.showMessageDialog(rootPane, "you are now friends with "+friend);
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }
        
    }//GEN-LAST:event_btacceptActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btaccept;
    private javax.swing.JButton btreject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbReqBg;
    private javax.swing.JTable tbpendingrequests;
    // End of variables declaration//GEN-END:variables

    class rqtablemodel extends AbstractTableModel {

        String names[] = {"sno", "name", "email"};

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

        public String getcolumnname(int columnindex) {
            return names[columnindex];
        }

    }

}
