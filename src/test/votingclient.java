/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package test;
import java.sql.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;
/**
 *
 * @author Kms somesh
 */
public class votingclient extends javax.swing.JFrame {

    public votingclient() {
       initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Jusername = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        viewcandidate = new javax.swing.JButton();
        voteButton = new javax.swing.JButton();
        candidatelist = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Jusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 130, 30));
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 272, 140, 30));

        jLabel4.setText("USERNAME");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, -1, -1));

        viewcandidate.setText("VIEW");
        viewcandidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewcandidateActionPerformed(evt);
            }
        });
        getContentPane().add(viewcandidate, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, -1, -1));

        voteButton.setText("VOTE");
        voteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(voteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, -1, -1));
        getContentPane().add(candidatelist, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 290, 140));

        jLabel3.setText("ENTER THE CANDIDATE ID TO VOTE");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/vote.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/bg.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewcandidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewcandidateActionPerformed
       fetchUsernamesFromDatabase();
    }//GEN-LAST:event_viewcandidateActionPerformed

    private void voteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voteButtonActionPerformed
        String serverAddress = "127.0.0.1"; // Replace with the server's IP address
        int serverPort = 12345; // Replace with the server's port

        String username = Jusername.getText();
        String candidateName = jTextField3.getText();

        sendUserDataToServer(serverAddress, serverPort, username, candidateName);
        dispose();
    }//GEN-LAST:event_voteButtonActionPerformed
    private void sendUserDataToServer(String serverAddress, int serverPort, String username, String candidateName) {
        try {
            // Create a DatagramSocket to send UDP packets
            DatagramSocket socket = new DatagramSocket();

            // Convert the data to bytes
            String data = username + "\n" + candidateName;
            byte[] buffer = data.getBytes();
            
            // Create a DatagramPacket with the server's address and port
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverInetAddress, serverPort);

            // Send the packet to the server
            socket.send(packet);

            // Close the socket when done
            
            /*
            int checksum = calculateChecksum(username, candidateName);
            String checksumData = String.valueOf(checksum);
            byte[] checksumBuffer = checksumData.getBytes();
            DatagramPacket checksumPacket = new DatagramPacket(checksumBuffer, checksumBuffer.length, serverInetAddress, serverPort);
            socket.send(checksumPacket);*/
            socket.close();
            System.out.println("Username and candidate sent successfully to the server.\nChecksum value:");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private int calculateChecksum(String username, String candidateName) {
    // Concatenate the username and candidate name
    String data = username + candidateName;

    // Calculate the checksum as the sum of ASCII values of characters in the data
    int checksum = 0;
    for (int i = 0; i < data.length(); i++) {
        checksum += (int) data.charAt(i);
    }

    return checksum;
}

    private void fetchUsernamesFromDatabase() {
    String url = "jdbc:mysql://localhost:3306/login";
    String dusername = "root";
    String password = "1234";
    String query = "SELECT username FROM user WHERE candidate='Y'";

    try (Connection connection = DriverManager.getConnection(url, dusername, password);
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {

        StringBuilder usernames = new StringBuilder();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            usernames.append(username).append("\n\n");
        }

        candidatelist.setText(usernames.toString());

    } catch (SQLException e) {
        e.printStackTrace();
    }
}




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
            java.util.logging.Logger.getLogger(votingclient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(votingclient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(votingclient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(votingclient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new votingclient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Jusername;
    private javax.swing.JTextField candidatelist;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton viewcandidate;
    private javax.swing.JButton voteButton;
    // End of variables declaration//GEN-END:variables
}