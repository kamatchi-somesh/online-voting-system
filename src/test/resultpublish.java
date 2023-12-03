/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package test;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class resultpublish extends javax.swing.JFrame {

    public resultpublish() {
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

        back = new javax.swing.JButton();
        unpublish = new javax.swing.JButton();
        publishresult = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back.setText("GO BACK");
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        unpublish.setText("UNPUBLISH");
        unpublish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unpublishActionPerformed(evt);
            }
        });
        getContentPane().add(unpublish, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, -1, -1));

        publishresult.setText("PUBLISH");
        publishresult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publishresultActionPerformed(evt);
            }
        });
        getContentPane().add(publishresult, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/voteresult.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/bg.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void publishresultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publishresultActionPerformed
        try {                                              
            // TODO add your handling code here:try {
            // Replace the connection details with your own
            String url = "jdbc:mysql://localhost:3306/login";
            String username = "root";
            String password = "1234";
            
            // Create a connection and statement, and execute the SQL code
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(resultpublish.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "INSERT INTO votedetails (candidate, votecount) " +
                    "SELECT vote, COUNT(*) AS votecount " +
                    "FROM user " +
                    "WHERE vote IS NOT NULL " +
                    "GROUP BY vote " +
                    "ON DUPLICATE KEY UPDATE votecount = VALUES(votecount)";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
                // Close the statement and connection
            } catch (SQLException ex) {
                Logger.getLogger(resultpublish.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection.close();
            
            JOptionPane.showMessageDialog(this, "Vote details published successfully.");
        } catch (SQLException ex) {
            Logger.getLogger(resultpublish.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    }//GEN-LAST:event_publishresultActionPerformed

    private void unpublishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unpublishActionPerformed
        try {
        // Create a connection to the database
        String url = "jdbc:mysql://localhost:3306/login";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        
        // Create the SQL query to delete all rows from the votedetails table
        String deleteQuery = "DELETE FROM votedetails";
        
        // Create a statement and execute the query
        Statement statement = connection.createStatement();
        int rowsDeleted = statement.executeUpdate(deleteQuery);
        
        // Close the statement and connection
        statement.close();
        connection.close();
        JOptionPane.showMessageDialog(this, "Vote details unpublished successfully.");
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    }//GEN-LAST:event_unpublishActionPerformed

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
            java.util.logging.Logger.getLogger(resultpublish.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(resultpublish.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(resultpublish.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(resultpublish.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new resultpublish().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton publishresult;
    private javax.swing.JButton unpublish;
    // End of variables declaration//GEN-END:variables

}