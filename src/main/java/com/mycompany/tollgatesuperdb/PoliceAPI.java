/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.tollgatesuperdb;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
/**
 *
 * @author Isheanesu Musengi
 */
public class PoliceAPI extends javax.swing.JFrame {

    /**
     * Creates new form PoliceAPI
     */
    private static ResultSetTableModel tableModel;
    //DATABASE url,username and apassword
    private static final String DATABASE_URL = 
            "jdbc:mysql://localhost:3306/tollgate_superdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Isheanesu&^@8";
    
    //we have multiple tables so this is just for vehicle
    private static final String DEFAULT_QUERY = "SELECT * FROM vehicle";
    public PoliceAPI() {
        
        try{
        tableModel = new ResultSetTableModel(DATABASE_URL,
                USERNAME, PASSWORD, DEFAULT_QUERY);
        }catch(SQLException e){
        
        }
        final TableRowSorter<TableModel> sorter =
            new TableRowSorter<TableModel>(tableModel);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable(tableModel);
        mainLabel = new javax.swing.JLabel();
        searchInput = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        searchCriteria = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resultTable.setModel(tableModel);
        jScrollPane1.setViewportView(resultTable);

        mainLabel.setFont(new java.awt.Font("Old English Text MT", 1, 24)); // NOI18N
        mainLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainLabel.setText("tollgate_superDb");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        searchCriteria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vehicle Plate", "RFID Code", "Owner ID", "Chassis No" }));
        searchCriteria.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        searchCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCriteriaActionPerformed(evt);
            }
        });

        jLabel1.setText("Search Using:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 211, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(searchCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(searchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(mainLabel)
                        .addGap(260, 260, 260)))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(mainLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(searchCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCriteriaActionPerformed
        // TODO add your handling code here:
        //String selectedOption = (String) comboBox.getSelectedItem();
    }//GEN-LAST:event_searchCriteriaActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String searchUsing = (String) searchCriteria.getSelectedItem();
        String textFieldData = searchInput.getText();
        String genQuery =null;
        if(searchUsing.equals("Vehicle Plate")){
               genQuery = preQuerisTest.searchByVehiclePlate(textFieldData);
            }else if(searchUsing.equals("RFID Code")){
                    genQuery = preQuerisTest.searchByRfidCode(textFieldData);
                }else if(searchUsing.equals("Owner ID")){
                        genQuery = preQuerisTest.searchByOwnerId(textFieldData);
                    }else if(searchUsing.equals("Chassis No")){
                            genQuery = preQuerisTest.searchByChassisNo(textFieldData);
                        }
        try{
            tableModel.setQuery(genQuery);
        }catch(SQLException sqlException){
            JOptionPane .showMessageDialog(null,
                                   sqlException.getMessage(),
                                   "Database Error",
                                   JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnSearchActionPerformed

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
                if ("FlatDarkLaf".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PoliceAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PoliceAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PoliceAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PoliceAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
            
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PoliceAPI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JTable resultTable;
    private javax.swing.JComboBox<String> searchCriteria;
    private javax.swing.JTextField searchInput;
    // End of variables declaration//GEN-END:variables
}
