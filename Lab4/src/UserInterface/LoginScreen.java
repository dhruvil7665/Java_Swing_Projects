/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Business.Abstract.User;
import Business.Users.Customer;
import Business.Users.Supplier;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author AEDSpring2019
 */
public class LoginScreen extends javax.swing.JPanel {

    /**
     * Creates new form LoginScreen
     */
    List<User> list;
    JPanel panelRight;
    public LoginScreen(JPanel panelRight, List<User> list) {
        initComponents();
        this.list = list;
        this.panelRight = panelRight;
        initialize();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPword = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        comboUser = new javax.swing.JComboBox<>();
        txtTitle = new javax.swing.JLabel();

        btnSubmit.setText("Login");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        comboUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUserActionPerformed(evt);
            }
        });

        txtTitle.setText("Supplier Login Screen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnSubmit))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPword)
                                .addComponent(comboUser, 0, 166, Short.MAX_VALUE))
                            .addComponent(txtTitle))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtTitle)
                .addGap(18, 18, 18)
                .addComponent(comboUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSubmit)
                .addContainerGap(131, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        User userString = (User) comboUser.getSelectedItem();
        if(userString.getRole() == "CUSTOMER"){
            Customer c = (Customer) userString;
            if(c.verify(txtPword.getText())){
            CardLayout layout = (CardLayout)panelRight.getLayout();
            panelRight.add(new SuccessScreen(userString));
            layout.next(panelRight);
        } else{
                JOptionPane.showMessageDialog(null, "Password entered is incorrect!");
            }
        }
        else if(userString.getRole() == "SUPPLIER"){
            Supplier s = (Supplier) userString;
            if(s.verify(txtPword.getText())){
                CardLayout layout = (CardLayout)panelRight.getLayout();
                panelRight.add(new SuccessScreen(userString));
                layout.next(panelRight);
            } else{
                JOptionPane.showMessageDialog(null, "Password entered is incorrect!");
            }
        } 
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void comboUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUserActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_comboUserActionPerformed

    
 private void initialize(){
        //text should either be "Supplier Login Screen" OR "Customer Login Screen"
        //Based on the selection
        if(!list.isEmpty()){
            if(list.get(0).getRole() == "CUSTOMER"){
                txtTitle.setText("Customer Login Screen");
                comboUser.removeAllItems();
                for(User u : list){
                    Customer c = (Customer) u;
                    comboUser.addItem(c);
                }
            }
            else if(list.get(0).getRole() == "SUPPLIER"){
                txtTitle.setText("Supplier Login Screen");
                comboUser.removeAllItems();
                for(User u : list){
                    Supplier s = (Supplier) u;
                    comboUser.addItem(s);
                }
            }
        }
        else{
            txtTitle.setText("Login Screen");
            comboUser.removeAllItems();
        }
        
        
        //only customer or suppliers should be listed based on the selection
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<Object> comboUser;
    private javax.swing.JTextField txtPword;
    private javax.swing.JLabel txtTitle;
    // End of variables declaration//GEN-END:variables
}
