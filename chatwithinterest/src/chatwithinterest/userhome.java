package chatwithinterest;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.TableView;

public class userhome extends javax.swing.JFrame {

    String loggedInEmail;
    DataInputStream dis;
    DataOutputStream dos;
    static ArrayList<chatwindow> alchat;
    static int count = 0, y = 5;
    ArrayList<stories> alstories;
    ArrayList<stories> alstories_all = new ArrayList<>();
    Tablemodel tm;

    public userhome(String email, DataInputStream DIS, DataOutputStream DOS) {
        initComponents();
        pbstory.setMaximum(5);
        pbstory.setStringPainted(true);
        pbstory.setString("");
        dis = DIS;
        dos = DOS;
        getContentPane().setBackground(Color.white);
        loggedInEmail = email;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        alchat = new ArrayList<>();

        alstories = new ArrayList<>();
        tm = new Tablemodel();
        tbstories.setModel(tm);

        setSize(965, 580);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ifstories.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ifstories.setIconifiable(true);
        ifstories.setMaximizable(true);

        try {
            Image scaledInstance = ImageIO.read(new File(System.getProperty("user.home") + "/images/blue.jpg"))
                    .getScaledInstance(lbstorybg.getWidth(), lbstorybg.getHeight(), Image.SCALE_SMOOTH);
            lbstorybg.setIcon(new ImageIcon(scaledInstance));

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Image scaledInstance = ImageIO.read(new File(System.getProperty("user.home") + "/images/seven_2.jpg"))
                    .getScaledInstance(lb_jpanelbg.getWidth(), lb_jpanelbg.getHeight(), Image.SCALE_SMOOTH);
            lb_jpanelbg.setIcon(new ImageIcon(scaledInstance));

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Image scaledInstance = ImageIO.read(new File(System.getProperty("user.home") + "/images/seven.jpg"))
                    .getScaledInstance(lb_userhomebg.getWidth(), lb_userhomebg.getHeight(), Image.SCALE_SMOOTH);
            lb_userhomebg.setIcon(new ImageIcon(scaledInstance));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
        setTitle(email);
        new Thread(new client()).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dp = new javax.swing.JDesktopPane();
        ifstories = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbstories = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jp_stories = new javax.swing.JPanel();
        lbstories = new javax.swing.JLabel();
        lbstorycount = new javax.swing.JLabel();
        pbstory = new javax.swing.JProgressBar();
        lbstorybg = new javax.swing.JLabel();
        lb_userhomebg = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btcp = new javax.swing.JButton();
        btsearchfriends = new javax.swing.JButton();
        friendrequests = new javax.swing.JButton();
        onlinefrnds = new javax.swing.JButton();
        btPostStory = new javax.swing.JButton();
        lb_jpanelbg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        ifstories.setVisible(true);
        ifstories.getContentPane().setLayout(null);

        tbstories.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbstories.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "friends"
            }
        ));
        tbstories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbstoriesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbstories);

        ifstories.getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 230, 410);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("POSTED STORIES");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ifstories.getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 30, 230, 30);

        jp_stories.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jp_stories.setLayout(null);

        lbstories.setBackground(new java.awt.Color(153, 204, 255));
        lbstories.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbstories.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jp_stories.add(lbstories);
        lbstories.setBounds(0, 0, 430, 360);

        ifstories.getContentPane().add(jp_stories);
        jp_stories.setBounds(300, 100, 430, 360);

        lbstorycount.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbstorycount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ifstories.getContentPane().add(lbstorycount);
        lbstorycount.setBounds(300, 30, 60, 30);

        pbstory.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ifstories.getContentPane().add(pbstory);
        pbstory.setBounds(370, 30, 360, 30);

        lbstorybg.setText("jLabel2");
        ifstories.getContentPane().add(lbstorybg);
        lbstorybg.setBounds(0, 0, 750, 490);

        dp.add(ifstories);
        ifstories.setBounds(0, 0, 770, 520);

        lb_userhomebg.setText("jLabel1");
        dp.add(lb_userhomebg);
        lb_userhomebg.setBounds(10, -10, 780, 480);

        getContentPane().add(dp);
        dp.setBounds(168, 15, 770, 520);

        jPanel1.setLayout(null);

        btcp.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btcp.setText("Change Password");
        btcp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcpActionPerformed(evt);
            }
        });
        jPanel1.add(btcp);
        btcp.setBounds(10, 220, 130, 40);

        btsearchfriends.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btsearchfriends.setText("Search Friends");
        btsearchfriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsearchfriendsActionPerformed(evt);
            }
        });
        jPanel1.add(btsearchfriends);
        btsearchfriends.setBounds(10, 270, 130, 40);

        friendrequests.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        friendrequests.setText("Friend Requests");
        friendrequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendrequestsActionPerformed(evt);
            }
        });
        jPanel1.add(friendrequests);
        friendrequests.setBounds(10, 320, 130, 40);

        onlinefrnds.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        onlinefrnds.setText("Online Friends");
        onlinefrnds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onlinefrndsActionPerformed(evt);
            }
        });
        jPanel1.add(onlinefrnds);
        onlinefrnds.setBounds(10, 370, 130, 40);

        btPostStory.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btPostStory.setText("Post Story ");
        btPostStory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPostStoryActionPerformed(evt);
            }
        });
        jPanel1.add(btPostStory);
        btPostStory.setBounds(10, 420, 130, 40);

        lb_jpanelbg.setText("jLabel1");
        jPanel1.add(lb_jpanelbg);
        lb_jpanelbg.setBounds(0, 0, 150, 210);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 20, 150, 510);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btcpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcpActionPerformed
        int x = dp.getWidth();
        int y = dp.getHeight();
        ChangePassInternalFrame obj = new ChangePassInternalFrame(loggedInEmail, x, y);

        obj.setSize(dp.getWidth(), dp.getHeight());
        obj.setMaximizable(true);
        obj.setIconifiable(true);
        obj.setClosable(true);
        obj.setFocusable(true);
        dp.add(obj);
        obj.setVisible(true);

    }//GEN-LAST:event_btcpActionPerformed

    private void btsearchfriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsearchfriendsActionPerformed
        int x = dp.getWidth();
        int y = dp.getHeight();
        SearchFriendsInternalFrame obj = new SearchFriendsInternalFrame(loggedInEmail, x, y);
        obj.setSize(dp.getWidth(), dp.getHeight());
        obj.setMaximizable(true);
        obj.setIconifiable(true);
        obj.setClosable(true);
        obj.setFocusable(true);
        dp.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_btsearchfriendsActionPerformed

    private void friendrequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendrequestsActionPerformed
        int x = dp.getWidth();
        int y = dp.getHeight();
        FriendRequestsInternalFrame obj = new FriendRequestsInternalFrame(loggedInEmail, x, y);
        obj.setSize(dp.getWidth(), dp.getHeight());
        obj.setMaximizable(true);
        obj.setIconifiable(true);
        obj.setClosable(true);
        obj.setFocusable(true);
        dp.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_friendrequestsActionPerformed

    private void onlinefrndsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onlinefrndsActionPerformed
        int x = dp.getWidth();
        int y = dp.getHeight();
        OnlineFriendsInternalFrame obj = new OnlineFriendsInternalFrame(loggedInEmail, dos, x, y);
        obj.setSize(dp.getWidth(), dp.getHeight());
        obj.setMaximizable(true);
        obj.setIconifiable(true);
        obj.setClosable(true);
        obj.setFocusable(true);
        dp.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_onlinefrndsActionPerformed

    private void btPostStoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPostStoryActionPerformed
        int x = dp.getWidth();
        int y = dp.getHeight();
        PostStoryInternalFrame obj = new PostStoryInternalFrame(loggedInEmail, dos, x, y);
        obj.setSize(dp.getWidth(), dp.getHeight());
        obj.setMaximizable(true);
        obj.setIconifiable(true);
        obj.setClosable(true);
        obj.setFocusable(true);
        dp.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_btPostStoryActionPerformed

    int countPB = 0;

    class progressbar implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                pbstory.setValue(i + 1);
                pbstory.setString("");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
            countPB = 1;
        }

    }

    class storiesthread implements Runnable {

        DataOutputStream dos;
        DataInputStream dis;

        @Override
        public void run() {
            try {

                Socket s = new Socket("localhost", 4200);
                dos = new DataOutputStream(s.getOutputStream());
                dis = new DataInputStream(s.getInputStream());
                int index = tbstories.getSelectedRow(), count = 0, total = 0;
                String friendemail = alstories.get(index).fromemail;
                for (int i = 0; i < alstories_all.size(); i++) {
                    if (alstories_all.get(i).fromemail.equals(friendemail)) {
                        total++;
                    }
                }
                for (int i = 0; i < alstories_all.size(); i++) {
                    lbstories.setText("");
                    lbstories.setIcon(null);
                    lbstories.revalidate();
                    System.out.println(i);
                    if (alstories_all.get(i).fromemail.equals(friendemail)) {
                        String type = alstories_all.get(i).storytype;
                        count++;
                        lbstorycount.setText(count + "/" + total);
                        if (type.equals("text")) {

                            lbstories.setText(alstories_all.get(i).story);

                        } else {
                            dos.writeUTF("send image story");
                            dos.writeUTF(friendemail);
                            dos.writeUTF(alstories_all.get(i).story);
                            String msg = dis.readUTF();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            if (msg.equals("sending")) {
                                byte b[] = new byte[10000];
                                long countbytes = 0;
                                Long size = dis.readLong();
                                while (true) {
                                    int r = dis.read(b, 0, b.length);
                                    baos.write(b, 0, r);
                                    countbytes = countbytes + r;
                                    if (countbytes == size) {
                                        dos.writeUTF("ok");
                                        break;
                                    }
                                }
                            }
                            baos.flush();
                            ImageIcon icon = new ImageIcon(baos.toByteArray());
                            Image i1 = icon.getImage().getScaledInstance(lbstories.getWidth(), lbstories.getHeight(), Image.SCALE_SMOOTH);
                            lbstories.setIcon(new ImageIcon(i1));
                        }
                        lbstories.repaint();
                        if (countPB == 0) {
                            new Thread(new progressbar()).start();
                        }
                        while (countPB == 0) {
                            Thread.sleep(100);
                        }
                        countPB = 0;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    private void tbstoriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbstoriesMouseClicked
        new Thread(new storiesthread()).start();

    }//GEN-LAST:event_tbstoriesMouseClicked

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
            java.util.logging.Logger.getLogger(userhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new userhome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPostStory;
    private javax.swing.JButton btcp;
    private javax.swing.JButton btsearchfriends;
    public javax.swing.JDesktopPane dp;
    private javax.swing.JButton friendrequests;
    private javax.swing.JInternalFrame ifstories;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jp_stories;
    private javax.swing.JLabel lb_jpanelbg;
    private javax.swing.JLabel lb_userhomebg;
    private javax.swing.JLabel lbstories;
    private javax.swing.JLabel lbstorybg;
    private javax.swing.JLabel lbstorycount;
    private javax.swing.JButton onlinefrnds;
    private javax.swing.JProgressBar pbstory;
    private javax.swing.JTable tbstories;
    // End of variables declaration//GEN-END:variables

    ArrayList<oldchat> aloldchats = new ArrayList<>();

    class client implements Runnable {

        @Override
        public void run() {
            try {
                // to server- send stories
                dos.writeUTF("send stories");
                dos.writeUTF(loggedInEmail);
                String msg2 = dis.readUTF();

                String already = "";

                if (msg2.equals("sending friends")) {
                    while (true) {
                        String username = dis.readUTF();
                        if (username.equals("done")) {
                            break;
                        }
                        String sid = dis.readUTF();
                        String fromemail = dis.readUTF();
                        String storytype = dis.readUTF();
                        String story = dis.readUTF();
                        String datetime = dis.readUTF();
                        stories st = new stories(username, sid, fromemail, storytype, story, datetime);
                        alstories_all.add(st);
                        if (!already.contains(fromemail)) {
                            alstories.add(st);
                            tm.fireTableDataChanged();
                            already = already + fromemail + "~~";
                        }
                    }
                }

                while (true) {

//                    System.out.println("IN other while");
                    String msg = dis.readUTF();
                    System.out.println(msg);
                    if (msg == null) {
                        break;
                    }
                    if (msg.equals("receive msg")) {
                        String msgfromemail = dis.readUTF(); //abc
                        String chatmsg = dis.readUTF(); //hello
                        String side = dis.readUTF(); //left or right
                        boolean isWindowOpened = false;
                        chatwindow tempchat = null;
//                        System.out.println("msg from " + msgfromemail);
                        for (int i = 0; i < alchat.size(); i++) {
                            String toCW = alchat.get(i).friendemail; // def
                            String fromCW = alchat.get(i).loggedinemail; //abc,pqr

                            if (msgfromemail.equals(toCW) && fromCW.equals(loggedInEmail)) {
                                isWindowOpened = true;
                                tempchat = alchat.get(i);
                                break;
                            }
                        }
                        System.out.println("IWO " + isWindowOpened);
                        if (!isWindowOpened) {
                            tempchat = new chatwindow(loggedInEmail, msgfromemail, dos);
                            alchat.add(tempchat);
                        }

                        tempchat.setSize(800, 500);
                        tempchat.setLayout(null);
                        final chatwindow finalCW = tempchat;
                        System.out.println(side + " side please");
                        if (side.equalsIgnoreCase("buzz")) {
                            finalCW.requestFocus();
                            finalCW.setVisible(true);
                            finalCW.setLocationRelativeTo(null);
                            Rectangle bounds = finalCW.getBounds();

                            int x = finalCW.getX();
                            int y = finalCW.getY();
                            int width = finalCW.getWidth();
                            int height = finalCW.getHeight();

                            x = x - 50;
                            finalCW.setBounds(x, y, width, height);
                            Thread.sleep(250);

                            x = x + 100;
                            finalCW.setBounds(x, y, width, height);
                            Thread.sleep(250);

                            x = x - 100;
                            finalCW.setBounds(x, y, width, height);
                            Thread.sleep(250);

                            x = x + 100;
                            finalCW.setBounds(x, y, width, height);
                            Thread.sleep(250);

                            x = x - 100;
                            finalCW.setBounds(x, y, width, height);
                            Thread.sleep(250);

                            x = x + 50;
                            finalCW.setBounds(bounds);
                        } else if (side.equals("smiley")) {
                            String path = System.getProperty("user.home") + "\\smileys";
                            File file = new File(path);
                            Image img = ImageIO.read(new File(file, chatmsg)).getScaledInstance(25, 25, Image.SCALE_SMOOTH);

                            JLabel lb = new JLabel(new ImageIcon(img));
                            lb.setBounds(10, y, 25, 25);
                            lb.setBackground(Color.red);
                            lb.setHorizontalAlignment(SwingConstants.LEFT);
                            tempchat.jptext.add(lb);
                            tempchat.jptext.repaint();
                            y = y + 27;
                        } else {

                            JLabel lb = new JLabel();
                            lb.setBounds(5, y, tempchat.jptext.getWidth() - 50, 40);
                            lb.setText(chatmsg);
                            lb.setOpaque(true);
                            lb.setBackground(Color.green);
                            lb.setHorizontalAlignment(SwingConstants.LEFT);
                            tempchat.jptext.add(lb);

//                            if (side.equalsIgnoreCase("right")) {
//                                JLabel lb = new JLabel();
//                                lb.setBounds(5, y, tempchat.jptext.getWidth() - 50, 40);
//                                lb.setText(chatmsg);
//                                lb.setOpaque(true);
//                                lb.setBackground(Color.red);
//                                lb.setHorizontalAlignment(SwingConstants.RIGHT);
//                                tempchat.jptext.add(lb);
//                                count = 1;
//
//                            } else if (side.equalsIgnoreCase("left")) {
//                                JLabel lb = new JLabel();
//                                lb.setBounds(5, y, tempchat.jptext.getWidth() - 50, 40);
//                                lb.setText(chatmsg);
//                                lb.setOpaque(true);
//                                lb.setBackground(Color.green);
//                                lb.setHorizontalAlignment(SwingConstants.LEFT);
//                                tempchat.jptext.add(lb);
//                                count = 0;
//                            }
                            y = y + 45;
                            tempchat.setVisible(true);
                            tempchat.requestFocus();
                            tempchat.repaint();
                            tempchat.setLocationRelativeTo(null);
                        }
                        tempchat.jScrollPane2.getViewport().setViewPosition(new Point(0, y));
                    } else if (msg.equalsIgnoreCase("sending old chats")) {
                        String friendemail = null;
                        while (true) {
                            String msgfrom = dis.readUTF();
                            if (msgfrom.equals("done")) {
                                friendemail = dis.readUTF();
                                break;
                            }
                            String mid = dis.readUTF();
                            String msgto = dis.readUTF();
                            String message = dis.readUTF();
                            String msgtype = dis.readUTF();
                            String datetime = dis.readUTF();
                            oldchat ch = new oldchat(mid, msgfrom, msgto, message, msgtype, datetime);
                            aloldchats.add(ch);
                        }

//                        chatwindow ch = alchat.get(alchat.size() - 1);
                        //for loop upto alsize 
                        chatwindow cwold = null;
                        for (int i = 0; i < alchat.size(); i++) {
                            chatwindow chatw = alchat.get(i);
                            if (chatw.loggedinemail.equals(loggedInEmail) && chatw.friendemail.equals(friendemail)) {
                                cwold = chatw;
                                break;
                            }
                        }
                        if (cwold == null) {
                            System.out.println("Chat window not found");
                        } else {
                            for (int i = 0; i < aloldchats.size(); i++) {
                                String msgfrom = aloldchats.get(i).msgfrom;
                                String msgto = aloldchats.get(i).msgto;
                                String msgtype = aloldchats.get(i).msgtype;
                                String msglocal = aloldchats.get(i).message;

                                if (msgtype.equals("text")) {
                                    if (loggedInEmail.equalsIgnoreCase(msgfrom) && msgto.equals(friendemail)) {
                                        JLabel lb = new JLabel();
                                        lb.setBounds(5, userhome.y, cwold.jptext.getWidth() - 50, 40);
                                        lb.setText(msglocal);
                                        lb.setOpaque(true);
                                        lb.setBackground(Color.red);
                                        lb.setHorizontalAlignment(SwingConstants.RIGHT);
                                        cwold.jptext.add(lb);
                                    } else {
                                        JLabel lb = new JLabel();
                                        lb.setBounds(5, y, cwold.jptext.getWidth() - 50, 40);
                                        lb.setText(msglocal);
                                        lb.setOpaque(true);
                                        lb.setBackground(Color.green);
                                        lb.setHorizontalAlignment(SwingConstants.LEFT);
                                        cwold.jptext.add(lb);
                                    }
                                    y = y + 45;
                                    cwold.jptext.repaint();
                                    cwold.revalidate();
                                } else if (msgtype.equalsIgnoreCase("smiley")) {
                                    if (loggedInEmail.equalsIgnoreCase(msgfrom) && msgto.equals(friendemail)) {
                                        String path = System.getProperty("user.home") + "\\smileys";
                                        File file = new File(path);
                                        Image img = ImageIO.read(new File(file, msglocal)).getScaledInstance(25, 25, Image.SCALE_SMOOTH);

                                        JLabel jLabel = new JLabel(new ImageIcon(img));
                                        jLabel.setBounds(cwold.jptext.getWidth() - 28, userhome.y, 25, 25);
                                        jLabel.setBackground(Color.red);
                                        jLabel.setHorizontalAlignment(JLabel.RIGHT);
                                        cwold.jptext.add(jLabel);
                                        cwold.jptext.repaint();

                                    } else {
                                        String path = System.getProperty("user.home") + "\\smileys";
                                        File file = new File(path);
                                        Image img = ImageIO.read(new File(file, msglocal)).getScaledInstance(25, 25, Image.SCALE_SMOOTH);

                                        JLabel lb = new JLabel(new ImageIcon(img));
                                        lb.setBounds(10, y, 25, 25);
                                        lb.setBackground(Color.red);
                                        lb.setHorizontalAlignment(SwingConstants.LEFT);
                                        cwold.jptext.add(lb);
                                        cwold.jptext.repaint();
                                    }
                                    y = y + 27;
                                    cwold.jptext.repaint();
                                    cwold.revalidate();
                                }

                            }
                            cwold.jScrollPane2.getViewport().setViewPosition(new Point(0, y - 100));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    class Tablemodel extends AbstractTableModel {

        String i = "friends";

        @Override
        public int getRowCount() {
            return alstories.size();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            return alstories.get(rowIndex).username + "\t\t\t posted on: " + alstories.get(rowIndex).datetime;

        }

        @Override
        public String getColumnName(int columnindex) {
            return i;
        }
    }

}
