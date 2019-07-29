package chatwithinterest;

import java.awt.Image;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class PostStoryInternalFrame extends javax.swing.JInternalFrame {

    String loggedinemail;
    DataOutputStream dos;
    int x, y;
    JFileChooser jfc;
    File f;
    Socket sock;
    DataInputStream dis;

    public PostStoryInternalFrame(String loggedinemail, DataOutputStream dos, int x, int y) {
        initComponents();
        this.loggedinemail = loggedinemail;
        this.dos = dos;
        this.x = x;
        this.y = y;
        lb_storybg.setSize(x, y);
        setTitle(loggedinemail);
        try {
            Image scaledInstance = ImageIO.read(new File(System.getProperty("user.home") + "/images/blue.jpg"))
                    .getScaledInstance(lb_storybg.getWidth(), lb_storybg.getHeight(), Image.SCALE_SMOOTH);
            lb_storybg.setIcon(new ImageIcon(scaledInstance));
        } catch (Exception e) {
        }

        jfc = new JFileChooser();

        buttonGroup1.add(rbtext);
        buttonGroup1.add(rbimage);
        btBrowse.setVisible(false);
        taStatus.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btBrowse = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taStatus = new javax.swing.JTextArea();
        rbimage = new javax.swing.JRadioButton();
        rbtext = new javax.swing.JRadioButton();
        bt_post = new javax.swing.JButton();
        lb_imagepreview = new javax.swing.JLabel();
        lb_storybg = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Post Story");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(234, 0, 175, 42);

        btBrowse.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btBrowse.setText("Browse");
        btBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBrowseActionPerformed(evt);
            }
        });
        getContentPane().add(btBrowse);
        btBrowse.setBounds(80, 110, 230, 36);

        taStatus.setColumns(20);
        taStatus.setRows(5);
        jScrollPane1.setViewportView(taStatus);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(70, 260, 250, 90);

        rbimage.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        rbimage.setText("Post Image");
        rbimage.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbimageItemStateChanged(evt);
            }
        });
        getContentPane().add(rbimage);
        rbimage.setBounds(80, 50, 120, 30);

        rbtext.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        rbtext.setText("Post Text as Status");
        rbtext.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtextItemStateChanged(evt);
            }
        });
        getContentPane().add(rbtext);
        rbtext.setBounds(80, 210, 180, 31);

        bt_post.setText("Post");
        bt_post.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_postActionPerformed(evt);
            }
        });
        getContentPane().add(bt_post);
        bt_post.setBounds(370, 380, 101, 34);

        lb_imagepreview.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(lb_imagepreview);
        lb_imagepreview.setBounds(430, 60, 310, 280);

        lb_storybg.setText("jLabel2");
        getContentPane().add(lb_storybg);
        lb_storybg.setBounds(0, 0, 820, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbimageItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbimageItemStateChanged
        if (evt.getSource() == rbimage) {
            btBrowse.setVisible(true);
            taStatus.setVisible(false);
        }
        try {
            Image scaledInstance = ImageIO.read(f)
                    .getScaledInstance(lb_imagepreview.getWidth(), lb_imagepreview.getHeight(), Image.SCALE_SMOOTH);
            lb_imagepreview.setIcon(new ImageIcon(scaledInstance));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_rbimageItemStateChanged

    private void rbtextItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtextItemStateChanged
        if (evt.getSource() == rbtext) {
            taStatus.setVisible(true);
            btBrowse.setVisible(false);
        }
    }//GEN-LAST:event_rbtextItemStateChanged

    private void btBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBrowseActionPerformed
        int r = jfc.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            f = jfc.getSelectedFile();
        }

    }//GEN-LAST:event_btBrowseActionPerformed

    private void bt_postActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_postActionPerformed
        if (rbimage.isSelected()) {
            try {
                dos.writeUTF("post image");
                dos.writeUTF(loggedinemail);
                dos.writeUTF("sending image");
                dos.writeUTF(f.getName());
                dos.writeLong(f.length());
                FileInputStream fis = new FileInputStream(f);

                long count = 0;
                long size = f.length();
                byte b[] = new byte[10000];
                while (true) {
                    int read = fis.read(b, 0, b.length);
                    dos.write(b, 0, read);
                    count = count + read;
                    System.out.println("Client " + count);
                    if (count == size) {
                        dos.flush();

                        break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            btBrowse.setVisible(false);

            //show message dialog
        } else {
            try {
                String status = taStatus.getText();
                dos.writeUTF("post status");
                dos.writeUTF(loggedinemail);
                dos.writeUTF(status);
                taStatus.setVisible(false);
                //show message dialog
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_bt_postActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBrowse;
    private javax.swing.JButton bt_post;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_imagepreview;
    private javax.swing.JLabel lb_storybg;
    private javax.swing.JRadioButton rbimage;
    private javax.swing.JRadioButton rbtext;
    private javax.swing.JTextArea taStatus;
    // End of variables declaration//GEN-END:variables
}
