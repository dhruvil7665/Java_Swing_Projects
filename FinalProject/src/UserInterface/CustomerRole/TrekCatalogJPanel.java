/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.CustomerRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.CustomerOrganization;
import Business.Organization.Organization;
import Business.Organization.TrekManagerOrganization;
import Business.Trek.Trek;
import Business.Trek.TrekSlotAndCost;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 *
 * @author dedhi
 */
public class TrekCatalogJPanel extends javax.swing.JPanel {

    /**
     * Creates new form TrekCatalogJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private CustomerOrganization customerOrganization;
    private TrekManagerOrganization trekManagerOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private String selectedTrekLocation;
    public TrekCatalogJPanel(JPanel userProcessContainer, UserAccount account, CustomerOrganization customerOrganization, Enterprise enterprise, EcoSystem business, String selectedTrekLocation) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.customerOrganization = customerOrganization;
        this.enterprise = enterprise;
        this.business = business;
        this.selectedTrekLocation = selectedTrekLocation;
        displayTrek(0,2000,new ArrayList<String>(Arrays.asList("Easy","East-Moderate","Moderate","Moderate-Difficult","Difficult")),new ArrayList<String>(Arrays.asList("Fall","Winter","Summer")));
    }
    
    public void displayTrek(float minCost, float maxCost, ArrayList<String> difficultyLevelList, ArrayList<String> trekTypeList){
        trekCatalogJPanel.removeAll();
        trekCatalogJPanel.repaint();
        JLabel label = null;
        int flag = 0;
        for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof TrekManagerOrganization){
                trekManagerOrganization = (TrekManagerOrganization) org;
                break;
            }
        }
        if(trekManagerOrganization != null){

            for(Trek trek: trekManagerOrganization.getTrekDirectory().getTrekArrayList()){
                for(TrekSlotAndCost trekSlotAndCost: trek.getTrekSlotAndCost()){
                    if(trekSlotAndCost.getTrekTotalCost() >= minCost && trekSlotAndCost.getTrekTotalCost() <= maxCost){
                        flag = 1;
                    }
                }
                for(String diffLevel: difficultyLevelList){
                    if(diffLevel.equals(trek.getTrekDifficultyLevel()) && flag == 1){
                        flag = 2;
                        break;
                    }
                }
                
                for(String trekType: trekTypeList){
                    if(trekType.equals(trek.getTrekWeather()) && flag == 2){
                        flag = 3;
                        break;
                    }
                }
                
                if(trek.getTrekLocation().equals(selectedTrekLocation) && flag == 3){
                    System.out.println("creation of image");
                    String trekImagePath = trek.getTrekImages().get(0);
                        label = new JLabel();
                        label.setName(trek.toString());
                        label.setHorizontalAlignment(JLabel.LEFT);
                        label.setVerticalAlignment(JLabel.TOP);
                        label.setIcon(new ImageIcon(
					new ImageIcon(trekImagePath).getImage().getScaledInstance(350, 300, Image.SCALE_DEFAULT)));
			trekCatalogJPanel.add(label);
                        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //trekLocationTableMouseClicked(evt);
                int flag = 0;
                String trekName = evt.getComponent().getName();
                System.out.println("trekName: "+trekName);
                for(Trek trek: trekManagerOrganization.getTrekDirectory().getTrekArrayList()){
                    if(trek.getTrekName().equals(trekName)){
                        if(trek.getTrekSlotAndCost().size() > 0){
                            CardLayout layout=(CardLayout)userProcessContainer.getLayout();
                            try {
                                userProcessContainer.add("TrekDetailsJPanel",new TrekDetailsJPanel(userProcessContainer, account, customerOrganization, enterprise, business, trek));
                            } catch (ParseException ex) {
                                Logger.getLogger(TrekCatalogJPanel.class.getName()).log(Level.SEVERE, null, ex);
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
                        trekCatalogJPanel.revalidate();
                        JLabel text = new JLabel(label.getName());
                        System.out.println(label);
                        text.setSize( label.getPreferredSize() );
                        text.setLocation(label.getLocation());
                        Font font = new Font("TimesRoman", Font.PLAIN, 18);
                        text.setFont(font);
                        text.setHorizontalAlignment(JLabel.CENTER);
                        text.setVerticalAlignment(JLabel.CENTER);
                        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
                        label.add(text);
                        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        trekCatalogJPanel.repaint();
                } 
            }
        }
       if(flag == 3){
        
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

        ScrollPane = new javax.swing.JScrollPane();
        trekCatalogJPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        minCostTxtField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        maxCostTxtField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        easyCheckBox = new javax.swing.JCheckBox();
        easyModerateCheckBox = new javax.swing.JCheckBox();
        moderateCheckBox = new javax.swing.JCheckBox();
        moderateDifficultCheckBox = new javax.swing.JCheckBox();
        difficultCheckBox = new javax.swing.JCheckBox();
        filterButton = new javax.swing.JButton();
        resetFilter = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        fallCheckBox = new javax.swing.JCheckBox();
        winterCheckBox = new javax.swing.JCheckBox();
        summerCheckBox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();

        setName("TrekCataogJPanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 600));

        trekCatalogJPanel.setBackground(new java.awt.Color(33, 108, 205));
        trekCatalogJPanel.setLayout(new java.awt.GridLayout(0, 3, 1, 1));
        ScrollPane.setViewportView(trekCatalogJPanel);

        jPanel1.setBackground(new java.awt.Color(33, 108, 205));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Filters");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cost");

        minCostTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        minCostTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minCostTxtFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("to");

        maxCostTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Difficulty Level");

        easyCheckBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        easyCheckBox.setText("Easy");
        easyCheckBox.setContentAreaFilled(false);

        easyModerateCheckBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        easyModerateCheckBox.setText("Easy - Moderate");
        easyModerateCheckBox.setContentAreaFilled(false);

        moderateCheckBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        moderateCheckBox.setText("Moderate");
        moderateCheckBox.setContentAreaFilled(false);

        moderateDifficultCheckBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        moderateDifficultCheckBox.setText("Moderate - Difficult");
        moderateDifficultCheckBox.setContentAreaFilled(false);

        difficultCheckBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        difficultCheckBox.setText("Difficult");
        difficultCheckBox.setContentAreaFilled(false);

        filterButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/CustomerRole/filterButton.png"))); // NOI18N
        filterButton.setContentAreaFilled(false);
        filterButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });

        resetFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/CustomerRole/resetFilterButton.png"))); // NOI18N
        resetFilter.setContentAreaFilled(false);
        resetFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetFilterActionPerformed(evt);
            }
        });

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/CustomerRole/backButton.png"))); // NOI18N
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Trek Type");

        fallCheckBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        fallCheckBox.setText("Fall");
        fallCheckBox.setContentAreaFilled(false);

        winterCheckBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        winterCheckBox.setText("Winter");
        winterCheckBox.setContentAreaFilled(false);
        winterCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                winterCheckBoxActionPerformed(evt);
            }
        });

        summerCheckBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        summerCheckBox.setText("Summer");
        summerCheckBox.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(difficultCheckBox)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(fallCheckBox)
                                        .addGap(18, 18, 18)
                                        .addComponent(winterCheckBox)
                                        .addGap(29, 29, 29)
                                        .addComponent(summerCheckBox))
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(minCostTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(maxCostTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(moderateCheckBox)
                                            .addComponent(easyCheckBox))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(easyModerateCheckBox)
                                            .addComponent(moderateDifficultCheckBox)))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(resetFilter)
                                        .addComponent(filterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(131, 564, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minCostTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(maxCostTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(easyCheckBox)
                    .addComponent(easyModerateCheckBox))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moderateCheckBox)
                    .addComponent(moderateDifficultCheckBox))
                .addGap(18, 18, 18)
                .addComponent(difficultCheckBox)
                .addGap(39, 39, 39)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fallCheckBox)
                    .addComponent(winterCheckBox)
                    .addComponent(summerCheckBox))
                .addGap(24, 24, 24)
                .addComponent(filterButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetFilter)
                .addGap(80, 80, 80)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPane)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void minCostTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minCostTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minCostTxtFieldActionPerformed

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        // TODO add your handling code here:
        float minCost = 0;
        float maxCost = 2000;
        int flag = 0;
        ArrayList<String> difficultyLevelList = new ArrayList<String>();
        ArrayList<String> trekTypeList = new ArrayList<String>();
        try{
        if(minCostTxtField.getText().isEmpty()){
            minCost = 0;
        }else{
            minCost = Float.parseFloat(minCostTxtField.getText());
        }
        if(maxCostTxtField.getText().isEmpty()){
            maxCost = 2000;
        } else{
            maxCost = Float.parseFloat(maxCostTxtField.getText());
        }
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter a valid Number","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(easyCheckBox.isSelected()){
            difficultyLevelList.add("Easy");
            flag = 1;
        }
        if(easyModerateCheckBox.isSelected()){
            difficultyLevelList.add("Easy-Moderate");
            flag = 1;
        }
        if(moderateCheckBox.isSelected()){
            difficultyLevelList.add("Moderate");
            flag = 1;
        }
        if(moderateDifficultCheckBox.isSelected()){
            difficultyLevelList.add("Moderate-Difficult");
            flag = 1;
        }
        if(difficultCheckBox.isSelected()){
            difficultyLevelList.add("Difficult");
            flag = 1;
        }
        if(flag == 0){
            difficultyLevelList.add("Easy");
            difficultyLevelList.add("Easy-Moderate");
            difficultyLevelList.add("Moderate");
            difficultyLevelList.add("Moderate-Difficult");
            difficultyLevelList.add("Difficult");
        }
        if(fallCheckBox.isSelected()){
            trekTypeList.add("Fall");
            flag = 2;
        }
        if(winterCheckBox.isSelected()){
            trekTypeList.add("Winter");
            flag = 2;
        }
        if(summerCheckBox.isSelected()){
            trekTypeList.add("Summer");
            flag = 2;
        }
        if(flag == 1 || flag == 0){
            trekTypeList.add("Fall");
            trekTypeList.add("Winter");
            trekTypeList.add("Summer");
        }
        System.out.println("filter called");
        displayTrek(minCost,maxCost,difficultyLevelList,trekTypeList);
    }//GEN-LAST:event_filterButtonActionPerformed

    private void resetFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetFilterActionPerformed
        // TODO add your handling code here:
        displayTrek(0,2000,new ArrayList<String>(Arrays.asList("Easy","East-Moderate","Moderate","Moderate-Difficult","Difficult")),new ArrayList<String>(Arrays.asList("Fall","Winter","Summer")));
        minCostTxtField.setText("");
        maxCostTxtField.setText("");
        easyCheckBox.setSelected(false);
        easyModerateCheckBox.setSelected(false);
        moderateCheckBox.setSelected(false);
        moderateDifficultCheckBox.setSelected(false);
        difficultCheckBox.setSelected(false);
        fallCheckBox.setSelected(false);
        winterCheckBox.setSelected(false);
        summerCheckBox.setSelected(false);
    }//GEN-LAST:event_resetFilterActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backButtonActionPerformed

    private void winterCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_winterCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_winterCheckBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JButton backButton;
    private javax.swing.JCheckBox difficultCheckBox;
    private javax.swing.JCheckBox easyCheckBox;
    private javax.swing.JCheckBox easyModerateCheckBox;
    private javax.swing.JCheckBox fallCheckBox;
    private javax.swing.JButton filterButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField maxCostTxtField;
    private javax.swing.JTextField minCostTxtField;
    private javax.swing.JCheckBox moderateCheckBox;
    private javax.swing.JCheckBox moderateDifficultCheckBox;
    private javax.swing.JButton resetFilter;
    private javax.swing.JCheckBox summerCheckBox;
    private javax.swing.JPanel trekCatalogJPanel;
    private javax.swing.JCheckBox winterCheckBox;
    // End of variables declaration//GEN-END:variables
}
