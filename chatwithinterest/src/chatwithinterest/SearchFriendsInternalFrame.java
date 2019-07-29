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

 class friends {

        String name, email;
        int sno;

        friends(int sno, String name, String email) {
            this.sno = sno;
            this.name = name;
            this.email = email;

        }

    }

public class SearchFriendsInternalFrame extends javax.swing.JInternalFrame {

    String value, interest;
    ArrayList<friends> al = new ArrayList<>();
    tablemodel mt;
    String fromemail;
    Socket sock;
    DataInputStream dis;
    DataOutputStream dos; 
    
    

    public SearchFriendsInternalFrame(String em,int x,int y) {
        initComponents();
        fromemail=em;
        lbSearchFriendsbg.setSize(x, y);
        try {
            
            Image scaledInstance = ImageIO.read(new File(System.getProperty("user.home") + "/images/blue.jpg"))
                    .getScaledInstance(lbSearchFriendsbg.getWidth(), lbSearchFriendsbg.getHeight(), Image.SCALE_SMOOTH);
            lbSearchFriendsbg.setIcon(new ImageIcon(scaledInstance));
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            sock = new Socket("127.0.0.1", 4200);
            dis = new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

        mt = new tablemodel();
        tbfriends.setModel(mt);
    }

    class client implements Runnable {

        @Override
        public void run() {
            try {

                dos.writeUTF("search");
                dos.writeUTF(value);
                dos.writeUTF(interest);
                dos.writeUTF(fromemail);
                dos.flush();
                String msg = dis.readUTF();
                if (msg.equals("receive msg")) {
                    int i = 0;
                    al.clear();
                    while (true) {
                        String email = dis.readUTF();
                        if (email.equals("done")) {
                            System.out.println("Finish");
                            break;
                        } else {
                            String username = dis.readUTF();
                            i++;

                            friends f = new friends(i, username, email);
                            al.add(f);
                        }
                        mt.fireTableDataChanged();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    
    class tablemodel extends AbstractTableModel {

        String names[]={"sno","name","email"};
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
        public String getcolumnname (int column)
        {
            return names[column];
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfsearch = new javax.swing.JTextField();
        btsearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbfriends = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbinterest = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        lbSearchFriendsbg = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Search Friends");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(244, 11, 160, 29);
        getContentPane().add(tfsearch);
        tfsearch.setBounds(30, 110, 410, 40);

        btsearch.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btsearch.setText("Search");
        btsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsearchActionPerformed(evt);
            }
        });
        getContentPane().add(btsearch);
        btsearch.setBounds(450, 110, 100, 40);

        tbfriends.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbfriends);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 159, 524, 230);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Choose Interest : ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 70, 140, 30);

        cbinterest.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--select interest--","movies", "sports", "tv series", "tattoos","health","makeup","science","history","automobiles","commerce","art","literature" }));
        getContentPane().add(cbinterest);
        cbinterest.setBounds(180, 70, 140, 30);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setText("Send Friend Request");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(220, 420, 200, 32);
        getContentPane().add(lbSearchFriendsbg);
        lbSearchFriendsbg.setBounds(0, 0, 710, 480);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsearchActionPerformed
        value = tfsearch.getText();
        interest = cbinterest.getSelectedItem() + "";
        if(interest.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "select interest first");
        }
        else
        {
            new Thread(new client()).start();
        }

    }//GEN-LAST:event_btsearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(tbfriends.getSelectionModel().isSelectionEmpty()){
           
           JOptionPane.showMessageDialog(rootPane, "select a user first");
       }
       else{
   
               int index=tbfriends.getSelectedRow();
                int sno= al.get(index).sno;
                String name=al.get(index).name;
                String toemail=al.get(index).email;
                try {
                dos.writeUTF("friend request");
                dos.writeUTF(fromemail);
                dos.writeUTF(toemail);
                dos.flush();
                String msg=dis.readUTF();
                JOptionPane.showMessageDialog(rootPane, msg);
                
           } catch (Exception e) {
               e.printStackTrace();
           }
                        
          
           
           
       }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btsearch;
    private javax.swing.JComboBox cbinterest;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbSearchFriendsbg;
    private javax.swing.JTable tbfriends;
    private javax.swing.JTextField tfsearch;
    // End of variables declaration//GEN-END:variables
}
