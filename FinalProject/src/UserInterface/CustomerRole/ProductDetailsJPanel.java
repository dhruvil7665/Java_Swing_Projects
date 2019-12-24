/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.CustomerRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.TrekCompanyEnterprise;
import Business.Network.Network;
import Business.Organization.CustomerOrganization;
import Business.Organization.Organization;
import Business.Organization.SportsStoreFinanceOrganization;
import Business.Organization.SportsStoreManagerOrganization;
import Business.Organization.TrekManagerOrganization;
import Business.Products.CustomerItems;
import Business.Products.Product;
import Business.Trek.Trek;
import Business.Trek.TrekSlotAndCost;
import Business.UserAccount.UserAccount;
import UserInterface.SystemAdminWorkArea.SystemAdminWorkAreaJPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.BorderStrokeStyle;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author shahd
 */
public class ProductDetailsJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ProductDetailsJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private CustomerOrganization customerOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private String category;
    private String subCat;
    private Product prod;
    private SportsStoreManagerOrganization sportsStoreManagerOrganization;
    private TrekManagerOrganization trekManagerOrganization;
    TrekManagerOrganization tmo = null;
    public ProductDetailsJPanel(JPanel userProcessContainer, UserAccount account, CustomerOrganization customerOrganization, Enterprise enterprise, EcoSystem business,Product prod) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.customerOrganization = customerOrganization;
        this.enterprise = enterprise;
        this.business = business;
        this.prod=prod;
        
        nameDisplayLabel.setText(prod.getName());
        brandDisplayLabel.setText(prod.getBrandName());
        costDisplayLabel.setText(String.valueOf(prod.getSellingPrice()));
        descText.setText(prod.getDescription());
        weatherDisplayLabel.setText(prod.getSuitableWeather());
        
        displayTrek();
    }
    
    public void displayProducts(){
        JLabel label = null;
        JLabel caption = null;
        for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof TrekManagerOrganization){
                trekManagerOrganization = (TrekManagerOrganization) org;
                break;
            }
        }
        if(trekManagerOrganization != null){
            System.out.println(category+"cat"+"subCat"+subCat);
            for(Trek trek: trekManagerOrganization.getTrekDirectory().getTrekArrayList()){
               
                if(trek.getTrekWeather().equals(prod.getSuitableWeather())){
                    System.out.println("inside Image IF");
                    String trekImagePath = trek.getTrekImages().get(0);
                        label = new JLabel();
                        label.setSize(350,300);
                        label.setName(trek.getTrekName());
                        label.setHorizontalAlignment(JLabel.LEFT);
                        label.setVerticalAlignment(JLabel.TOP);
                        label.setIcon(new ImageIcon(
		        new ImageIcon(trekImagePath).getImage().getScaledInstance(350, 100, Image.SCALE_SMOOTH)));
                      
			treksPanel.add(label);
                        treksPanel.revalidate();
//                        JLabel text = new JLabel(label.getName());
//                        //System.out.println(label);
//                        text.setSize( label.getPreferredSize() );
//                        text.setLocation(label.getLocation());
//                        Font font = new Font("TimesRoman", Font.PLAIN, 18);
//                        text.setFont(font);
//                        text.setHorizontalAlignment(JLabel.CENTER);
//                        text.setVerticalAlignment(JLabel.CENTER);
                        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
//                        caption.add(text);
                        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        
                        
                        treksPanel.repaint();
                } 
            }
        }
        
         label.addMouseListener(new java.awt.event.MouseAdapter(){
         public void mouseClicked(java.awt.event.MouseEvent evt) {
                //trekLocationTableMouseClicked(evt);
                System.out.println("mouseClicked");
                int flag = 0;
                String trekName = evt.getComponent().getName();
                System.out.println(trekName);
                for(Trek trek: trekManagerOrganization.getTrekDirectory().getTrekArrayList()){
                    if(trek.getTrekName().equals(trekName)){
                       CardLayout layout=(CardLayout)userProcessContainer.getLayout();
                        try {
                            userProcessContainer.add("TrekDetailsJPanel",new UserInterface.CustomerRole.TrekDetailsJPanel(userProcessContainer, account, customerOrganization, enterprise, business, trek));
                        } catch (ParseException ex) {
                            Logger.getLogger(ProductDetailsJPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
        
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        productNameLabel = new javax.swing.JLabel();
        nameDisplayLabel = new javax.swing.JLabel();
        brandLabel = new javax.swing.JLabel();
        brandDisplayLabel = new javax.swing.JLabel();
        costLabel = new javax.swing.JLabel();
        costDisplayLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descText = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        weatherDisplayLabel = new javax.swing.JLabel();
        addToCartButton = new javax.swing.JButton();
        qtySpinner = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recommendedTreks = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        treksPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(33, 108, 205));

        jPanel1.setBackground(new java.awt.Color(33, 108, 205));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        productNameLabel.setBackground(new java.awt.Color(102, 204, 255));
        productNameLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        productNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        productNameLabel.setText("Product Name:");

        nameDisplayLabel.setBackground(new java.awt.Color(153, 204, 255));
        nameDisplayLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        nameDisplayLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nameDisplayLabel.setOpaque(true);

        brandLabel.setBackground(new java.awt.Color(102, 204, 255));
        brandLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        brandLabel.setForeground(new java.awt.Color(255, 255, 255));
        brandLabel.setText("Brand:");

        brandDisplayLabel.setBackground(new java.awt.Color(153, 204, 255));
        brandDisplayLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        brandDisplayLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        brandDisplayLabel.setOpaque(true);

        costLabel.setBackground(new java.awt.Color(102, 204, 255));
        costLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        costLabel.setForeground(new java.awt.Color(255, 255, 255));
        costLabel.setText("Total Cost");

        costDisplayLabel.setBackground(new java.awt.Color(153, 204, 255));
        costDisplayLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        costDisplayLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        costDisplayLabel.setOpaque(true);

        jLabel8.setBackground(new java.awt.Color(102, 204, 255));
        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Description:");

        descText.setEditable(false);
        descText.setBackground(new java.awt.Color(153, 204, 255));
        descText.setColumns(20);
        descText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        descText.setRows(5);
        descText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(descText);

        jLabel2.setBackground(new java.awt.Color(102, 204, 255));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Suitable Weather");

        weatherDisplayLabel.setBackground(new java.awt.Color(153, 204, 255));
        weatherDisplayLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        weatherDisplayLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        weatherDisplayLabel.setOpaque(true);

        addToCartButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addToCartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/CustomerRole/addToCart.png"))); // NOI18N
        addToCartButton.setContentAreaFilled(false);
        addToCartButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addToCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartButtonActionPerformed(evt);
            }
        });

        qtySpinner.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel3.setBackground(new java.awt.Color(51, 204, 255));
        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Select Quantity");

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/CustomerRole/backButton.png"))); // NOI18N
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Product Details");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addToCartButton)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(qtySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(costLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(brandLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                            .addComponent(costDisplayLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(brandDisplayLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameDisplayLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(weatherDisplayLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(backButton)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(236, 236, 236))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(brandLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(brandDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(costLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(weatherDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(addToCartButton)
                .addGap(18, 18, 18)
                .addComponent(backButton))
        );

        jPanel2.setBackground(new java.awt.Color(33, 108, 205));

        jScrollPane1.setBorder(null);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(628, 677));

        recommendedTreks.setBackground(new java.awt.Color(33, 108, 205));
        recommendedTreks.setPreferredSize(new java.awt.Dimension(628, 677));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Recommended Treks for your gears");

        treksPanel.setBackground(new java.awt.Color(33, 108, 205));
        treksPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102)));
        treksPanel.setLayout(new java.awt.GridLayout(0, 2, 5, 5));

        javax.swing.GroupLayout recommendedTreksLayout = new javax.swing.GroupLayout(recommendedTreks);
        recommendedTreks.setLayout(recommendedTreksLayout);
        recommendedTreksLayout.setHorizontalGroup(
            recommendedTreksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recommendedTreksLayout.createSequentialGroup()
                .addGroup(recommendedTreksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(recommendedTreksLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel1))
                    .addComponent(treksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        recommendedTreksLayout.setVerticalGroup(
            recommendedTreksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recommendedTreksLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(treksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(recommendedTreks);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addToCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartButtonActionPerformed
        // TODO add your handling code here:
        
        Organization org = null;
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()){
            if (organization instanceof CustomerOrganization){
                org = organization;
                break;
            }
        }
        if (org!=null){
            
        }
        int qty = Integer.parseInt(qtySpinner.getValue().toString());
        if(qty<=0){
            JOptionPane.showMessageDialog(null,"Please Select Atleast 1 Quanity","Warning",JOptionPane.WARNING_MESSAGE);
            
        }else{
        CustomerItems item = new CustomerItems();
        item.setCustAccount(account);
        item.setProduct(prod);
        item.setQuantity(Integer.parseInt(qtySpinner.getValue().toString()));
        System.out.println("cart"+customerOrganization.getCartDir());
        System.out.println("list"+customerOrganization.getCartDir().getCartList());
        customerOrganization.getCartDir().getCartList().add(item);
        JOptionPane.showMessageDialog(null,"Product added to Cart","Information",JOptionPane.INFORMATION_MESSAGE);
        
        }
    }//GEN-LAST:event_addToCartButtonActionPerformed


    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backButtonActionPerformed

   

    public void displayTrek(){

        treksPanel.removeAll();
        treksPanel.repaint();
        JLabel label = null;
        int flag = 0;

        Network network = null;
        TrekCompanyEnterprise trekCompanyEnterprise = null;
        
        
        for(Network n: business.getNetworkList()){
            for(Enterprise e: n.getEnterpriseDirectory().getEnterpriseList()){
                if(e.getName().equals(enterprise.getName())){
                    network = n;
                    break;
                }
            }
        }
        if(network!=null){
            for(Enterprise e: network.getEnterpriseDirectory().getEnterpriseList()){
                if(e instanceof TrekCompanyEnterprise){
                    trekCompanyEnterprise = (TrekCompanyEnterprise) e;
                    break;
                }
            }
            if(trekCompanyEnterprise != null){
                for(Organization o: trekCompanyEnterprise.getOrganizationDirectory().getOrganizationList()){
                    if(o instanceof TrekManagerOrganization){
                        tmo = (TrekManagerOrganization) o;
                        break;
                    }
                }
                if(tmo != null){
                    for(Trek t: tmo.getTrekDirectory().getTrekArrayList()){
                        if(t.getTrekWeather().equals(prod.getSuitableWeather())){

                                String trekImagePath = t.getTrekImages().get(0);
                                label = new JLabel();
                                label.setName(t.toString());
                                label.setHorizontalAlignment(JLabel.LEFT);
                                label.setVerticalAlignment(JLabel.TOP);
                                label.setSize(350,300);
                               // label.setBorder(BorderFactory.createLineBorder(Color.black));
                                label.setIcon(new ImageIcon(
					new ImageIcon(trekImagePath).getImage().getScaledInstance(370,450, Image.SCALE_DEFAULT)));
                                treksPanel.add(label);
                                treksPanel.revalidate();
                                JLabel text = new JLabel(label.getName());
                                System.out.println(label);
                                text.setSize( label.getPreferredSize() );
                                text.setLocation(label.getLocation());
                                Font font = new Font("Comic Sans", Font.PLAIN, 18);
                                text.setFont(font);
                                text.setHorizontalAlignment(JLabel.CENTER);
                                text.setVerticalAlignment(JLabel.CENTER);
                                javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
                                label.add(text);
                                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                treksPanel.repaint();
                                flag = 1;
                            }

                        
                    }
                }
            }
        }
    
    

    
    if(flag == 1){
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //trekLocationTableMouseClicked(evt);
                int flag = 0;
                String trekName = evt.getComponent().getName();
                System.out.println("trekManagerOrg: "+tmo);
                for(Trek trek: tmo.getTrekDirectory().getTrekArrayList()){
                    if(trek.getTrekName().equals(trekName)){
                        if(trek.getTrekSlotAndCost().size() > 0){
                            CardLayout layout=(CardLayout)userProcessContainer.getLayout();
                            try {
                                userProcessContainer.add("TrekDetailsJPanel",new TrekDetailsJPanel(userProcessContainer, account, customerOrganization, enterprise, business, trek));
                            } catch (ParseException ex) {
                                Logger.getLogger(ProductDetailsJPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            layout.next(userProcessContainer);
                            flag = 1;
                            break;
                        }
                    } else{
                        flag = 0;
                    }
                }
                if(flag == 0){
                    JOptionPane.showMessageDialog(null, "Trek Slots Coming Soon!");
                }
            }
        });
       }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCartButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel brandDisplayLabel;
    private javax.swing.JLabel brandLabel;
    private javax.swing.JLabel costDisplayLabel;
    private javax.swing.JLabel costLabel;
    private javax.swing.JTextArea descText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nameDisplayLabel;
    private javax.swing.JLabel productNameLabel;
    private javax.swing.JSpinner qtySpinner;
    private javax.swing.JPanel recommendedTreks;
    private javax.swing.JPanel treksPanel;
    private javax.swing.JLabel weatherDisplayLabel;
    // End of variables declaration//GEN-END:variables
}
