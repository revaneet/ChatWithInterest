package chatwithinterest;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ChangePassInternalFrame extends javax.swing.JInternalFrame {

    String op;
    String np;
    String cp;
    Socket sock;
    DataInputStream dis;
    DataOutputStream dos;
    String em;
    int x,y;

    public ChangePassInternalFrame(String email,int x,int y) {
        initComponents();
        em = email;
        this.x=x;
        this.y=y;
        try {
            lbpasswordbg.setSize(x,y);
            Image scaledInstance = ImageIO.read(new File(System.getProperty("user.home") + "/images/blue.jpg"))
                    .getScaledInstance(lbpasswordbg.getWidth(), lbpasswordbg.getHeight(), Image.SCALE_SMOOTH);
            lbpasswordbg.setIcon(new ImageIcon(scaledInstance));
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class client implements Runnable {

        @Override
        public void run() {
            try {
                sock = new Socket("127.0.0.1", 4200);
                dos = new DataOutputStream(sock.getOutputStream());

                dis = new DataInputStream(sock.getInputStream());
                dos.writeUTF("change password");
                dos.writeUTF(op);
                dos.writeUTF(np);
                dos.writeUTF(cp);
                dos.writeUTF(em);
                
                //wait for password change message
                String msg=dis.readUTF();
                if(msg.equals("success"))
                {
                    JOptionPane.showMessageDialog(rootPane,"password changed sucessfully");
                }
                else{
                    JOptionPane.showMessageDialog(rootPane,"error: password not changed");
                }

            } catch (IOException e) {
            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pfcurrentpw = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        pfnewpw = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        pfconfirmpw = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        lbpasswordbg = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Change password ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 0, 290, 43);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Current Password :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 61, 130, 30);
        getContentPane().add(pfcurrentpw);
        pfcurrentpw.setBounds(160, 60, 380, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("New Password :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 95, 130, 30);
        getContentPane().add(pfnewpw);
        pfnewpw.setBounds(160, 100, 380, 30);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Confirm Password :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 136, 130, 30);

        pfconfirmpw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfconfirmpwActionPerformed(evt);
            }
        });
        getContentPane().add(pfconfirmpw);
        pfconfirmpw.setBounds(160, 140, 380, 30);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setText("Change Password");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(160, 190, 380, 40);
        getContentPane().add(lbpasswordbg);
        lbpasswordbg.setBounds(0, 0, 590, 310);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        op = pfcurrentpw.getText();
        np = pfnewpw.getText();
        cp = pfconfirmpw.getText();
        new Thread(new client()).start();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void pfconfirmpwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfconfirmpwActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pfconfirmpwActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbpasswordbg;
    private javax.swing.JPasswordField pfconfirmpw;
    private javax.swing.JPasswordField pfcurrentpw;
    private javax.swing.JPasswordField pfnewpw;
    // End of variables declaration//GEN-END:variables
}
