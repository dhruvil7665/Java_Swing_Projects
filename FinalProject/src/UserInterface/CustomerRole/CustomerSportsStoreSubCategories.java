/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.CustomerRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.CustomerOrganization;
import Business.Organization.Organization;
import Business.Organization.SportsStoreManagerOrganization;
import Business.Products.Product;
import Business.UserAccount.UserAccount;
import UserInterface.SystemAdminWorkArea.SystemAdminWorkAreaJPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author shahd
 */
public class CustomerSportsStoreSubCategories extends javax.swing.JPanel {

    /**
     * Creates new form CustomerSportsStoreSubCategories
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private CustomerOrganization customerOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private String Category;
    
    private SportsStoreManagerOrganization sportsStoreManagerOrganization;
    public CustomerSportsStoreSubCategories(JPanel userProcessContainer, UserAccount account, CustomerOrganization customerOrganization, Enterprise enterprise, EcoSystem business,String Category) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.customerOrganization = customerOrganization;
        this.enterprise = enterprise;
        this.business = business;
        this.Category=Category;
        
        
        categoryLabel.setText("Category: "+Category);
        displaySubCat();
    }

    
    
    
    public void displaySubCat(){
    
    
        String shoesUrl = "C:/Users/shahd/Documents/AED-bitBucket/gladiators/FinalProject/FinalProject/assets/SportsCategoryPics/shoes.jpg";
        String accessoriesUrl = "C:/Users/shahd/Documents/AED-bitBucket/gladiators/FinalProject/FinalProject/assets/SportsCategoryPics/accessories.PNG";
        String apparrelUrl = "C:/Users/shahd/Documents/AED-bitBucket/gladiators/FinalProject/FinalProject/assets/SportsCategoryPics/apparel.PNG";
        String equipmentsUrl = "C:/Users/shahd/Documents/AED-bitBucket/gladiators/FinalProject/FinalProject/assets/SportsCategoryPics/equipmentImage.PNG";
                
  
        
        //shoes Label
         ImageIcon icon= new ImageIcon(shoesUrl);
        Image icon2=icon.getImage();
        shoesLabel.setSize(750,350);
        Image icon3=icon2.getScaledInstance(shoesLabel.getWidth(),shoesLabel.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon finalIcon = new ImageIcon(icon3);
        shoesLabel.setIcon(finalIcon);
        
        //apparel label
        icon= new ImageIcon(apparrelUrl);
        icon2=icon.getImage();
        apparelLabel.setSize(750,350);
        icon3=icon2.getScaledInstance(apparelLabel.getWidth(),apparelLabel.getHeight(),Image.SCALE_SMOOTH);
        finalIcon = new ImageIcon(icon3);
        apparelLabel.setIcon(finalIcon);
        
        //accessories Label
        
        icon= new ImageIcon(accessoriesUrl);
        icon2=icon.getImage();
        accessoriesLabel.setSize(750,350);
        icon3=icon2.getScaledInstance(accessoriesLabel.getWidth(),accessoriesLabel.getHeight(),Image.SCALE_SMOOTH);
        finalIcon = new ImageIcon(icon3);
        accessoriesLabel.setIcon(finalIcon);
        
        
        icon= new ImageIcon(equipmentsUrl);
        icon2=icon.getImage();
        equipmentLabel.setSize(750,350);
        icon3=icon2.getScaledInstance(equipmentLabel.getWidth(),equipmentLabel.getHeight(),Image.SCALE_SMOOTH);
        finalIcon = new ImageIcon(icon3);
        equipmentLabel.setIcon(finalIcon);
    
    
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        shoesLabel = new javax.swing.JLabel();
        apparelLabel = new javax.swing.JLabel();
        equipmentLabel = new javax.swing.JLabel();
        accessoriesLabel = new javax.swing.JLabel();
        backButton1 = new javax.swing.JButton();
        categoryLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(33, 108, 205));

        shoesLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        shoesLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        shoesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shoesLabelMouseClicked(evt);
            }
        });

        apparelLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        apparelLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        apparelLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                apparelLabelMouseClicked(evt);
            }
        });

        equipmentLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        equipmentLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        equipmentLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                equipmentLabelMouseClicked(evt);
            }
        });

        accessoriesLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        accessoriesLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        accessoriesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accessoriesLabelMouseClicked(evt);
            }
        });

        backButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/CustomerRole/backButton.png"))); // NOI18N
        backButton1.setContentAreaFilled(false);
        backButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton1ActionPerformed(evt);
            }
        });

        categoryLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        categoryLabel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(equipmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(shoesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(apparelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(accessoriesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(categoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(129, 129, 129))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(categoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(apparelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shoesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(accessoriesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equipmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(backButton1)
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void shoesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoesLabelMouseClicked
        // TODO add your handling code here:
        System.out.println("enterprise"+enterprise);
        
         for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof SportsStoreManagerOrganization){
                sportsStoreManagerOrganization = (SportsStoreManagerOrganization) org;
                break;
            }
        
        }
         int flag=0;
          System.out.println("something123");
          System.out.println(sportsStoreManagerOrganization+"ss");
          if(sportsStoreManagerOrganization != null){
               System.out.println("something");
          for(Product prod: sportsStoreManagerOrganization.getProdDir().getProductList()){
              System.out.println("something"+prod);
          if(prod.getCategory().equalsIgnoreCase(Category)&& prod.getSubCategory().equalsIgnoreCase("Shoes")){
                flag=1;
          }
          
          }
          }
          if(flag==1){
        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
        userProcessContainer.add("CustomerSportsStoreProductsJPanel",new UserInterface.CustomerRole.CustomerSportsStoreProductJPanel(userProcessContainer, account, customerOrganization, enterprise, business,Category,"Shoes"));
        layout.next(userProcessContainer);
          }else{
              JOptionPane.showMessageDialog(null,"Products Arriving Soon","Information",JOptionPane.INFORMATION_MESSAGE);
              return;
          }
    }//GEN-LAST:event_shoesLabelMouseClicked

    private void apparelLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_apparelLabelMouseClicked
        // TODO add your handling code here:
         for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof SportsStoreManagerOrganization){
                sportsStoreManagerOrganization = (SportsStoreManagerOrganization) org;
                break;
            }
        }
         int flag=0;
          if(sportsStoreManagerOrganization != null){
          for(Product prod: sportsStoreManagerOrganization.getProdDir().getProductList()){
          if(prod.getCategory().equalsIgnoreCase(Category)&& prod.getSubCategory().equalsIgnoreCase("Apparel")){
                flag=1;
          }
          
          }
          }
          if(flag==1){
        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
        userProcessContainer.add("CustomerSportsStoreProductsJPanel",new UserInterface.CustomerRole.CustomerSportsStoreProductJPanel(userProcessContainer, account, customerOrganization, enterprise, business,Category,"Apparel"));
        layout.next(userProcessContainer);
          }else{
              JOptionPane.showMessageDialog(null,"Products Arriving Soon","Information",JOptionPane.INFORMATION_MESSAGE);
              return;
          }
      
    }//GEN-LAST:event_apparelLabelMouseClicked

    private void equipmentLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_equipmentLabelMouseClicked
        // TODO add your handling code here:
         for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof SportsStoreManagerOrganization){
                sportsStoreManagerOrganization = (SportsStoreManagerOrganization) org;
                break;
            }
        }
         int flag=0;
          if(sportsStoreManagerOrganization != null){
          for(Product prod: sportsStoreManagerOrganization.getProdDir().getProductList()){
          if(prod.getCategory().equalsIgnoreCase(Category)&& prod.getSubCategory().equalsIgnoreCase("Equipments")){
                flag=1;
          }
          
          }
          }
          if(flag==1){
        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
        userProcessContainer.add("CustomerSportsStoreProductsJPanel",new UserInterface.CustomerRole.CustomerSportsStoreProductJPanel(userProcessContainer, account, customerOrganization, enterprise, business,Category,"Equipments"));
        layout.next(userProcessContainer);
          }else{
              JOptionPane.showMessageDialog(null,"Products Arriving Soon","Information",JOptionPane.INFORMATION_MESSAGE);
               return;
          }
        
    }//GEN-LAST:event_equipmentLabelMouseClicked

    private void accessoriesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accessoriesLabelMouseClicked
        // TODO add your handling code here:
         for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof SportsStoreManagerOrganization){
                sportsStoreManagerOrganization = (SportsStoreManagerOrganization) org;
                break;
            }
        }
         int flag=0;
          if(sportsStoreManagerOrganization != null){
          for(Product prod: sportsStoreManagerOrganization.getProdDir().getProductList()){
          if(prod.getCategory().equalsIgnoreCase(Category)&& prod.getSubCategory().equalsIgnoreCase("Accessories")){
                flag=1;
          }
          
          }
          }
          if(flag==1){
        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
        userProcessContainer.add("CustomerSportsStoreProductsJPanel",new UserInterface.CustomerRole.CustomerSportsStoreProductJPanel(userProcessContainer, account, customerOrganization, enterprise, business,Category,"Accessories"));
        layout.next(userProcessContainer);
          }else{
              JOptionPane.showMessageDialog(null,"Products Arriving Soon","Information",JOptionPane.INFORMATION_MESSAGE);
              return;
          }
       
    }//GEN-LAST:event_accessoriesLabelMouseClicked

    private void backButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButton1ActionPerformed
        // TODO add your handling code here:
         userProcessContainer.remove(this);
         Component[] componentArray = userProcessContainer.getComponents();
        Component component = componentArray[componentArray.length - 1];
        CustomerSportsStoreWorkAreaJPanel workArea = (CustomerSportsStoreWorkAreaJPanel) component;
        workArea.populateOrderTable();
        workArea.populateProductTable();

        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accessoriesLabel;
    private javax.swing.JLabel apparelLabel;
    private javax.swing.JButton backButton1;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JLabel equipmentLabel;
    private javax.swing.JLabel shoesLabel;
    // End of variables declaration//GEN-END:variables
}
