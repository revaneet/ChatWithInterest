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

public class login2 extends javax.swing.JFrame {

    String email, password;
    Socket sock;
    DataInputStream dis;
    DataOutputStream dos;
    boolean flag = false;

    public login2() {
        initComponents();
        setLocationRelativeTo(null);
        setSize(600, 500);
        lbbglogin.setSize(600,500);
        setLocationRelativeTo(null);
        
        try {
            Image scaledInstance = ImageIO.read(new File(System.getProperty("user.home") + "/images/nine.jpg"))
                    .getScaledInstance(lbbglogin.getWidth(), lbbglogin.getHeight(), Image.SCALE_SMOOTH);
            lbbglogin.setIcon(new ImageIcon(scaledInstance));
        } catch (Exception e) {
        }

        System.out.println(System.getProperty("user.home"));
        //file path
    }

    class client implements Runnable {

        @Override
        public void run() {
            try {
                if (flag == false) {
                    sock = new Socket("127.0.0.1", 4200);
                    dos = new DataOutputStream(sock.getOutputStream());

                    dis = new DataInputStream(sock.getInputStream());
                    flag = true;
                }
                dos.writeUTF("login");
                dos.writeUTF(email);
                dos.writeUTF(password);
                dos.flush();
                String msg = dis.readUTF();
                if (msg.equals("success")) {
                    login2.this.dispose();
                    userhome uh = new userhome(email, dis, dos);
                    uh.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(login2.this, "user doesn't exit");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfemail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pfpassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        lblogin = new javax.swing.JLabel();
        lbbglogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Email : ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 100, 70, 25);

        tfemail.setText("abc@gmail.com");
        getContentPane().add(tfemail);
        tfemail.setBounds(130, 100, 382, 30);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Password : ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 150, 90, 25);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(230, 210, 90, 37);

        pfpassword.setText("12345");
        getContentPane().add(pfpassword);
        pfpassword.setBounds(130, 150, 382, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Not a member ? sign up now.");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(150, 260, 250, 40);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setText("sign up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(230, 310, 100, 37);

        lblogin.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblogin.setForeground(new java.awt.Color(255, 255, 255));
        lblogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblogin.setText("Admin Login");
        getContentPane().add(lblogin);
        lblogin.setBounds(160, 30, 260, 40);

        lbbglogin.setText("jLabel4");
        getContentPane().add(lbbglogin);
        lbbglogin.setBounds(0, 0, 590, 480);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        email = tfemail.getText();
        password = pfpassword.getText();
        new Thread(new client()).start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        signup su = new signup();
        su.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new login2().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbbglogin;
    private javax.swing.JLabel lblogin;
    private javax.swing.JPasswordField pfpassword;
    private javax.swing.JTextField tfemail;
    // End of variables declaration//GEN-END:variables
}
