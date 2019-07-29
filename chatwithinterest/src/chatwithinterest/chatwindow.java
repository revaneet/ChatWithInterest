package chatwithinterest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class chatwindow extends javax.swing.JFrame {

    String loggedinemail;
    String friendemail;
    DataOutputStream dos;
    DataInputStream dis;
    Socket sock;

    chatwindow(String loggedinemail, String friendemail, DataOutputStream dos) {
        this.dos = dos;
        initComponents();
        setLocationRelativeTo(null);
        jptext.setLayout(null);
        setSize(800, 500);
        lb_chatbg.setSize(800, 500);
        this.loggedinemail = loggedinemail;
        this.friendemail = friendemail;
        setTitle(friendemail);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSenderSmileys();
        try {
            Image scaledInstance = ImageIO.read(new File(System.getProperty("user.home") + "/images/white.jpg"))
                    .getScaledInstance(lb_chatbg.getWidth(), lb_chatbg.getHeight(), Image.SCALE_SMOOTH);
            lb_chatbg.setIcon(new ImageIcon(scaledInstance));
            dos.writeUTF("send old chats");
            dos.writeUTF(loggedinemail);
            dos.writeUTF(friendemail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tachat = new javax.swing.JTextArea();
        jpchatsender = new javax.swing.JPanel();
        send1 = new javax.swing.JButton();
        send = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jptext = new javax.swing.JPanel();
        lb_chatbg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        tachat.setColumns(20);
        tachat.setRows(5);
        tachat.setText("hello");
        jScrollPane1.setViewportView(tachat);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(330, 300, 440, 74);

        jpchatsender.setBackground(new java.awt.Color(153, 153, 255));
        jpchatsender.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jpchatsender.setLayout(null);
        getContentPane().add(jpchatsender);
        jpchatsender.setBounds(10, 10, 300, 410);

        send1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        send1.setText("BUZZZZZZ");
        send1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send1ActionPerformed(evt);
            }
        });
        getContentPane().add(send1);
        send1.setBounds(360, 380, 160, 40);

        send.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        send.setText("Send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });
        getContentPane().add(send);
        send.setBounds(580, 380, 160, 40);

        jptext.setBackground(new java.awt.Color(204, 255, 204));
        jptext.setPreferredSize(new java.awt.Dimension(170, 15000));
        jptext.setLayout(null);
        jScrollPane2.setViewportView(jptext);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(330, 10, 440, 280);

        lb_chatbg.setText("jLabel1");
        getContentPane().add(lb_chatbg);
        lb_chatbg.setBounds(0, 0, 790, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        String msg = tachat.getText();
        try {
            System.out.println(loggedinemail + " " + friendemail);
            dos.writeUTF("send message");
            dos.writeUTF(loggedinemail);
            dos.writeUTF(friendemail);
            dos.writeUTF(msg);
            dos.writeUTF(msg);
            System.out.println("Message sent");

            JLabel lb = new JLabel();
            lb.setBounds(5, userhome.y, jptext.getWidth() - 50, 40);
            lb.setText(msg);
            lb.setOpaque(true);
            lb.setBackground(Color.red);
            lb.setHorizontalAlignment(SwingConstants.RIGHT);
            jptext.add(lb);

//            if (userhome.count == 0) {
//                dos.writeUTF("left");
//                JLabel lb = new JLabel();
//                lb.setBounds(5, userhome.y, jptext.getWidth() - 50, 40);
//                lb.setText(msg);
//                lb.setOpaque(true);
//                lb.setBackground(Color.red);
//                lb.setHorizontalAlignment(SwingConstants.RIGHT);
//                jptext.add(lb);
//                userhome.count = 1;
//            } else {
//                dos.writeUTF("right");
//                JLabel lb = new JLabel();
//                lb.setBounds(5, userhome.y, jptext.getWidth() - 50, 40);
//                lb.setText(msg);
//                lb.setOpaque(true);
//                lb.setBackground(Color.green);
//                lb.setHorizontalAlignment(SwingConstants.LEFT);
//                jptext.add(lb);
//                userhome.count = 0;
//            }
            userhome.y = userhome.y + 45;
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_sendActionPerformed

    private void send1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send1ActionPerformed
        // TODO add your handling code here:
        try {
            dos.writeUTF("send message");
            dos.writeUTF(loggedinemail);
            dos.writeUTF(friendemail);
            dos.writeUTF("buzz");
            dos.writeUTF("buzz");
            dos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_send1ActionPerformed

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
            java.util.logging.Logger.getLogger(chatwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chatwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chatwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chatwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chatwindow("", "", null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpchatsender;
    public javax.swing.JPanel jptext;
    private javax.swing.JLabel lb_chatbg;
    public javax.swing.JButton send;
    public javax.swing.JButton send1;
    public javax.swing.JTextArea tachat;
    // End of variables declaration//GEN-END:variables

    int y = 5;
    int lastheight;

    private void setSenderSmileys() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int x = 5;
                int y = 10;
                String path = System.getProperty("user.home") + "\\smileys";
                File file = new File(path);
                System.out.println(file.exists());
                File[] f = file.listFiles();
                int j = 1;
                for (int i = 0; i < f.length; i++) {
                    try {
                        JLabel jLabel = new JLabel();
                        jpchatsender.add(jLabel);
                        Image img = ImageIO.read(f[i]).getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                        String imgname = f[i].getName();
                        jLabel.setBounds(x, y, 25, 25);
                        jLabel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                try {
                                    dos.writeUTF("send message");
                                    dos.writeUTF(loggedinemail);
                                    dos.writeUTF(friendemail);
                                    dos.writeUTF(imgname);
                                    dos.writeUTF("smiley");
                                    dos.flush();

                                    setImage(img);
                                } catch (IOException ex) {
                                    Logger.getLogger(chatwindow.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                            private void setImage(Image img) {
                                JLabel jLabel = new JLabel(new ImageIcon(img));
                                jLabel.setBounds(jptext.getWidth() - 28, userhome.y, 25, 25);
//                                int height1 = jpchatsender.getHeight();
//                                lastheight = lastheight + 25;
//                                if (chatwindow.this.y >= height1) {
//                                    int xx = chatwindow.this.y - height1 + 25;
//                                    Dimension preferredSize = jpchatsender.getPreferredSize();
//                                    preferredSize.height = preferredSize.height + xx;
//                                    jpchatsender.setPreferredSize(preferredSize);
//                                    jScrollPane1.getViewport().setViewPosition(new Point(0, preferredSize.height));
//                                    jpchatsender.revalidate();
//                                } else {
//                                    Dimension preferredSize = jpchatsender.getPreferredSize();
//                                    preferredSize.height = lastheight + 40;
//                                    jpchatsender.setPreferredSize(preferredSize);
//                                    jScrollPane1.getViewport().setViewPosition(new Point(0, preferredSize.height));
//                                    jpchatsender.revalidate();
//                                }
                                jLabel.setBackground(Color.red);
                                jLabel.setHorizontalAlignment(JLabel.RIGHT);
                                jptext.add(jLabel);
                                jptext.repaint();
                                userhome.y = userhome.y + 27;
                            }
                        });
                        jLabel.setIcon(new ImageIcon(img));
                        x += 30;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if (j % 5 == 0) {
                        y = y + 30;
                        x = 5;
                    }
                    j++;
                }
            }
        }).start();
    }

}
