/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Madhan Kumar
 */
public class createuser extends javax.swing.JFrame {
private Connection connection;
    /**
     * Creates new form createuser
     */
    public createuser() {
        initComponents();
        back.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        }
});
        generatebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String categoryValue = jTextField10.getText();

        String name = tname.getText();
        String mobile = tmob.getText();
        String lastFiveDigits = mobile.substring(mobile.length() - 5);
        String id = name + lastFiveDigits;
        idgenerate.setText(id);
        
       }});
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String categoryValue = jTextField10.getText();
        

        String name = tname.getText();
        String mobile = tmob.getText();
        String lastFiveDigits = mobile.substring(mobile.length() - 5);
        String id = name + lastFiveDigits;
        
        char[] passwordChars = jPasswordField1.getPassword();
        String password = new String(passwordChars);
        String address = taddress.getText();
        String email = temail.getText();
        String fatherName = tfname.getText();
        String motherName = tmname.getText();
        String nationality = tnation.getText();
        String religion = treligion.getText();
        String gender = tgender.getText();
        if (gender.equalsIgnoreCase("male")) {
            gender = "M";
        } else if (gender.equalsIgnoreCase("female")) {
            gender = "F";
        }
        // Database insertion code
        String url = "jdbc:mysql://localhost:3306/login";
        String username = "root";
        String dpassword = "1234";
        String sql = "INSERT INTO createuser (id, password, name, mobile, address, email, father_name, mother_name, nationality, religion, is_candidate,gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, dpassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, mobile);
            statement.setString(5, address);
            statement.setString(6, email);
            statement.setString(7, fatherName);
            statement.setString(8, motherName);
            statement.setString(9, nationality);
            statement.setString(10, religion);
            
            // Set the 'is_candidate' value based on the combo box selection
            if (categoryValue.equalsIgnoreCase("CANDIDATE")) {
                statement.setString(11, "Y");
            } else {
                statement.setString(11, "N");
            }
            statement.setString(12, gender);
            statement.executeUpdate();
        JOptionPane.showMessageDialog(null ,"User created successfully","Success",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLIntegrityConstraintViolationException e) {
            // Handle duplicate entry error
            String errorMessage = "User with the same ID already exists in the database. Please enter a different ID manually.";
            JOptionPane.showMessageDialog(null, errorMessage, "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            // Handle other database errors
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        
        }
        String sql2="INSERT INTO user(username,password,candidate) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, dpassword);
             PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, id);
            statement.setString(2, password);
                if (categoryValue.equalsIgnoreCase("CANDIDATE")) {
                statement.setString(3, "Y");
            } else {
                statement.setString(3, "N");
            }

            statement.executeUpdate();
        
        } catch (SQLIntegrityConstraintViolationException e) {
            // Handle duplicate entry error
            String errorMessage = "User with the same ID already exists in the database. Please enter a different ID manually.";
            JOptionPane.showMessageDialog(null, errorMessage, "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            // Handle other database errors
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
        
    }
});

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mob = new javax.swing.JLabel();
        fname = new javax.swing.JLabel();
        mname = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        nation = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        religion = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        category = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        idgenerate = new javax.swing.JTextField();
        tgender = new javax.swing.JTextField();
        treligion = new javax.swing.JTextField();
        tnation = new javax.swing.JTextField();
        temail = new javax.swing.JTextField();
        tmob = new javax.swing.JTextField();
        taddress = new javax.swing.JTextField();
        tmname = new javax.swing.JTextField();
        tfname = new javax.swing.JTextField();
        tname = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        generatebutton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mob.setText("MOBILE");
        getContentPane().add(mob, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 90, -1));

        fname.setText("FATHER NAME");
        getContentPane().add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 90, -1));

        mname.setText("MOTHER NAME");
        getContentPane().add(mname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 90, -1));

        address.setText("ADDRESS");
        getContentPane().add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 90, -1));

        nation.setText("NATIONALITY");
        getContentPane().add(nation, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 90, -1));

        email.setText("EMAIL-ID");
        getContentPane().add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 90, -1));

        religion.setText("RELIGION");
        getContentPane().add(religion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 90, -1));

        name.setText("NAME");
        getContentPane().add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 37, -1));

        jLabel10.setText("ID GENEARTED");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, -1, 20));

        category.setText("CATEGORY");
        getContentPane().add(category, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel1.setText("PASSWORD");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, -1, -1));

        back.setText("GO BACK");
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 90, -1));

        jLabel2.setText("GENDER");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 210, -1));
        getContentPane().add(idgenerate, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 210, 50));
        getContentPane().add(tgender, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 190, -1));
        getContentPane().add(treligion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 110, -1));
        getContentPane().add(tnation, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 110, -1));
        getContentPane().add(temail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 190, -1));
        getContentPane().add(tmob, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 190, -1));
        getContentPane().add(taddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 290, 70));
        getContentPane().add(tmname, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 190, -1));
        getContentPane().add(tfname, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 190, -1));
        getContentPane().add(tname, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 190, -1));

        jTextField10.setText("VOTER/CANDIDATE");
        getContentPane().add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 190, 30));

        generatebutton.setText("GENERATE");
        getContentPane().add(generatebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, -1, -1));

        createButton.setText("CREATE");
        getContentPane().add(createButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/bg.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

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
            java.util.logging.Logger.getLogger(createuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            createuser createUser = new createuser();
            createUser.setVisible(true); // Add this line to establish the database connection
        }
    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address;
    private javax.swing.JButton back;
    private javax.swing.JLabel category;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel email;
    private javax.swing.JLabel fname;
    private javax.swing.JButton generatebutton;
    private javax.swing.JTextField idgenerate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JLabel mname;
    private javax.swing.JLabel mob;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nation;
    private javax.swing.JLabel religion;
    private javax.swing.JTextField taddress;
    private javax.swing.JTextField temail;
    private javax.swing.JTextField tfname;
    private javax.swing.JTextField tgender;
    private javax.swing.JTextField tmname;
    private javax.swing.JTextField tmob;
    private javax.swing.JTextField tname;
    private javax.swing.JTextField tnation;
    private javax.swing.JTextField treligion;
    // End of variables declaration//GEN-END:variables
}
