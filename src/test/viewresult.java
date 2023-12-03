/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Kms somesh
 */
public class viewresult extends javax.swing.JFrame {

    public viewresult() {
        initComponents();
        back.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        }
});
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        leadingcandidate = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        candidatelist = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("LEADING CANDIDATE");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, -1, -1));
        getContentPane().add(leadingcandidate, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 160, 30));

        jButton1.setText("VIEW");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, -1, -1));

        jLabel4.setText("VOTE RESULT COUNT");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, -1));

        back.setText("GO BACK");
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 90, -1));
        getContentPane().add(candidatelist, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 230, 160));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/voteresult.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/bg.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fetchUsernamesFromDatabase();
    }//GEN-LAST:event_jButton1ActionPerformed
    private String leadingCandidate = "";
    private void fetchUsernamesFromDatabase() {
    String url = "jdbc:mysql://localhost:3306/login";
    String dusername = "root";
    String password = "1234";
    String query = "SELECT candidate, votecount FROM votedetails";

    try (Connection connection = DriverManager.getConnection(url, dusername, password);
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {

        int maxVoteCount = 0;

        StringBuilder candidateInfo = new StringBuilder();
        while (resultSet.next()) {
            String candidate = resultSet.getString("candidate");
            int voteCount = resultSet.getInt("votecount");
            candidateInfo.append("Candidate: ").append(candidate).append(", Vote Count: ").append(voteCount).append("<br>");

            if (voteCount > maxVoteCount) {
                maxVoteCount = voteCount;
                leadingCandidate = candidate;
            }
        }

        String formattedCandidateInfo = "<html>" + candidateInfo.toString().replaceAll("\n", "<br>") + "</html>";
        candidatelist.setText(formattedCandidateInfo);
        leadingcandidate.setText(leadingCandidate);

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
            java.util.logging.Logger.getLogger(viewresult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewresult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewresult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewresult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewresult().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel candidatelist;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel leadingcandidate;
    // End of variables declaration//GEN-END:variables
}
