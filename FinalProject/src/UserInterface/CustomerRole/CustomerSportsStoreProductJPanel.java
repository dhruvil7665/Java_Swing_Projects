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
import Business.Organization.TrekManagerOrganization;
import Business.Products.Product;
import Business.Trek.Trek;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author shahd
 */
public class CustomerSportsStoreProductJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CustomerSportsStoreProductJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private CustomerOrganization customerOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private String category;
    private String subCat;
    private SportsStoreManagerOrganization sportsStoreManagerOrganization;
   

    CustomerSportsStoreProductJPanel(JPanel userProcessContainer, UserAccount account, CustomerOrganization customerOrganization, Enterprise enterprise, EcoSystem business, String Category, String subCat) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.customerOrganization = customerOrganization;
        this.enterprise = enterprise;
        this.business = business;
        this.category=Category;
        this.subCat=subCat;
       
        System.out.println("prod sub"+enterprise);
        displayProducts();
        
    }
    
    
    
    public void displayProducts(){
        JLabel label = null;
        JLabel caption = null;
        int flag = 0;
       
        for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof SportsStoreManagerOrganization){
                sportsStoreManagerOrganization = (SportsStoreManagerOrganization) org;
                break;
            }
        }
        
        if(sportsStoreManagerOrganization != null){
            System.out.println(category+"cat"+"subCat"+subCat);
            for(Product prod: sportsStoreManagerOrganization.getProdDir().getProductList()){
                System.out.println(prod+"prod");
                System.out.println(prod.getImagePath());
                System.out.println("prod category: "+prod.getCategory());
                System.out.println("prod subcategory: "+prod.getSubCategory());
                if(prod.getCategory().equalsIgnoreCase(category)&& prod.getSubCategory().equalsIgnoreCase(subCat)){
                    System.out.println("inside Image IF");
                    String prodImagePath = prod.getImagePath();
                        label = new JLabel();
                        label.setSize(350,300);
                        label.setName(prod.getName());
                        label.setHorizontalAlignment(JLabel.LEFT);
                        label.setVerticalAlignment(JLabel.TOP);
                        label.setIcon(new ImageIcon(
		        new ImageIcon(prodImagePath).getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT)));
                      
			productJPanel.add(label);
                        label.addMouseListener(new java.awt.event.MouseAdapter(){
         public void mouseClicked(java.awt.event.MouseEvent evt) {
                //trekLocationTableMouseClicked(evt);
                System.out.println("mouseClicked");
                int flag = 0;
                String prodName = evt.getComponent().getName();
                System.out.println(prodName);
                for(Product prod: sportsStoreManagerOrganization.getProdDir().getProductList()){
                    if(prod.getName().equals(prodName)){
                        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
                        userProcessContainer.add("prodDetailsJPanel",new ProductDetailsJPanel(userProcessContainer, account, customerOrganization, enterprise, business, prod));
                        layout.next(userProcessContainer);
                        flag = 1;
                        break;
                    } else{
                        flag = 0;
                    }
                }
                if(flag == 0){
                    JOptionPane.showMessageDialog(null, "Product Coming Soon!");
                }
            }
});
                        flag = 1;
                        productJPanel.revalidate();
                        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
                        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        productJPanel.repaint();
                        flag = 1;
                } 
            }
        }

        if(flag == 1){

        
    }
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
        productJPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        backButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(33, 108, 205));

        productJPanel.setBackground(new java.awt.Color(33, 108, 205));
        productJPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 108, 205)));
        productJPanel.setLayout(new java.awt.GridLayout(0, 3, 5, 5));
        jScrollPane1.setViewportView(productJPanel);

        jPanel2.setBackground(new java.awt.Color(33, 108, 205));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 108, 205)));

        backButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/CustomerRole/backButton.png"))); // NOI18N
        backButton1.setContentAreaFilled(false);
        backButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(backButton1)
                .addGap(700, 700, 700))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(backButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButton1ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel productJPanel;
    // End of variables declaration//GEN-END:variables
}
