/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.TrekFinanceManagerRole;

import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Trek.Trek;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AddTrekCostWorkRequest;
import Business.Organization.TrekLeaderOrganization;
import Business.Trek.TrekSlotAndCost;
import Business.WorkQueue.AssignTrekLeaderWorkRequest;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author dedhi
 */
public class CostPlanningJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CostPlanningJPanel
     */
    private Trek trek;
    private AddTrekCostWorkRequest request;
    private UserAccount account;
    private Enterprise enterprise;
    private JPanel userProcessContainer;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public CostPlanningJPanel(JPanel userProcessContainer, AddTrekCostWorkRequest request, UserAccount account, Enterprise enterprise) {
        this.request = request;
        this.trek = request.getTrek();
        this.account = account;
        this.enterprise = enterprise;
        this.userProcessContainer = userProcessContainer;
        initComponents();
        trekNameTxtField.setText(trek.getTrekName());
        trekLocTxtField.setText(trek.getTrekLocation());
        difficultyLevelComboBox.setSelectedItem(trek);
        durationSpinner.setValue(trek.getTrekDuration());
        itineraryTextArea.setText(trek.getTrekItinerary());
        pickUpPointTxtField.setText(trek.getTrekPickUpPoint());
        baseCampTxtField.setText(trek.getTrekBaseCamp());
        slotLabel.setText(dateFormat.format(request.getTrekSlotAndCost().getTrekStartDate()));
        totalTrekkersSpinner.setValue(request.getTrekSlotAndCost().getTotalSeats());
        trekTotalCostTxtField.setText("0");
        trekTaxTxtField.setText("0");
        trekTravelCostTxtField.setText("0");
        trekAccCostTxtField.setText("0");
        trekMealCostTxtField.setText("0");
        trekMedicalEmergTxtField.setText("0");
        

        trekTravelCostTxtField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
               
                addCost();
            }
            public void removeUpdate(DocumentEvent e) {
              
               addCost();
            }
            public void insertUpdate(DocumentEvent e) {
               
                addCost();
            }
        });
        
        trekAccCostTxtField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
               
                addCost();
            }
            public void removeUpdate(DocumentEvent e) {
              
               addCost();
            }
            public void insertUpdate(DocumentEvent e) {
               
                addCost();
            }
        });
        
        trekMealCostTxtField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
               
                addCost();
            }
            public void removeUpdate(DocumentEvent e) {
              
               addCost();
            }
            public void insertUpdate(DocumentEvent e) {
               
                addCost();
            }
        });
        
        trekMedicalEmergTxtField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
               
                addCost();
            }
            public void removeUpdate(DocumentEvent e) {
              
               addCost();
            }
            public void insertUpdate(DocumentEvent e) {
               
                addCost();
            }
            
            
            
        });
    }
    
    public void addCost(){
                 try{
                     float travelCost;
                     float accCost;
                     float mealCost;
                     float medicalEmergencyCost;
                     float tax;
                     float total;
                     
                     if(trekTravelCostTxtField.getText().isEmpty()){
                         travelCost = 0;
                     } else{
                         travelCost = Float.parseFloat(trekTravelCostTxtField.getText());
                     }
                    
                     if(trekAccCostTxtField.getText().isEmpty()){
                         accCost = 0;
                     } else{
                         accCost = Float.parseFloat(trekAccCostTxtField.getText());
                     }
                    
                     if(trekMealCostTxtField.getText().isEmpty()){
                         mealCost = 0;
                     } else{
                         mealCost = Float.parseFloat(trekMealCostTxtField.getText());
                     }
                    
                     if(trekMedicalEmergTxtField.getText().isEmpty()){
                         medicalEmergencyCost = 0;
                     } else{
                         medicalEmergencyCost = Float.parseFloat(trekMedicalEmergTxtField.getText());
                     }
                     tax = (float) ((travelCost+accCost+mealCost+medicalEmergencyCost)*0.065);
                     total = travelCost+accCost+mealCost+medicalEmergencyCost+tax;
                     trekTaxTxtField.setText(String.valueOf(tax));
                     trekTotalCostTxtField.setText(String.valueOf(total));
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Please enter a valid Number","Warning",JOptionPane.WARNING_MESSAGE);
                    return;
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

        addTrekJPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        trekNameTxtField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        trekLocTxtField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        durationSpinner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itineraryTextArea = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        pickUpPointTxtField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        baseCampTxtField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        totalTrekkersSpinner = new javax.swing.JSpinner();
        difficultyLevelComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        submitCostButton = new javax.swing.JButton();
        trekTravelCostTxtField = new javax.swing.JTextField();
        trekAccCostTxtField = new javax.swing.JTextField();
        trekMealCostTxtField = new javax.swing.JTextField();
        trekMedicalEmergTxtField = new javax.swing.JTextField();
        trekTaxTxtField = new javax.swing.JTextField();
        trekTotalCostTxtField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        slotLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        addTrekJPanel.setBackground(new java.awt.Color(33, 108, 205));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Trek Name");

        trekNameTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        trekNameTxtField.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Trek Location");

        trekLocTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        trekLocTxtField.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Difficulty Level");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Duration");

        durationSpinner.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        durationSpinner.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Trek Itinerary");

        itineraryTextArea.setColumns(20);
        itineraryTextArea.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        itineraryTextArea.setRows(5);
        itineraryTextArea.setEnabled(false);
        jScrollPane2.setViewportView(itineraryTextArea);

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Pick Up Point");

        pickUpPointTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        pickUpPointTxtField.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Base Camp");

        baseCampTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        baseCampTxtField.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Total Number of Trekkers");

        totalTrekkersSpinner.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        totalTrekkersSpinner.setEnabled(false);

        difficultyLevelComboBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        difficultyLevelComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Easy", "Easy-Moderate", "Moderate", "Moderate-Difficult", "Difficult" }));
        difficultyLevelComboBox.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Travel Cost/person in $");

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Accomodation Cost/person in $");

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Meal Cost/person in $");

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tax 6.5%");

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Total Cost in $");

        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Medical Emergency/person in $");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cost Structure");

        submitCostButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekFinanceManagerRole/submitButton.png"))); // NOI18N
        submitCostButton.setContentAreaFilled(false);
        submitCostButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitCostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCostButtonActionPerformed(evt);
            }
        });

        trekTravelCostTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        trekAccCostTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        trekMealCostTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        trekMedicalEmergTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        trekTaxTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        trekTaxTxtField.setEnabled(false);

        trekTotalCostTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        trekTotalCostTxtField.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Trek Details");

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Slot");

        slotLabel.setBackground(new java.awt.Color(255, 255, 255));
        slotLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        slotLabel.setOpaque(true);

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekFinanceManagerRole/backButton.png"))); // NOI18N
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Add Cost");

        javax.swing.GroupLayout addTrekJPanelLayout = new javax.swing.GroupLayout(addTrekJPanel);
        addTrekJPanel.setLayout(addTrekJPanelLayout);
        addTrekJPanelLayout.setHorizontalGroup(
            addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2)
            .addGroup(addTrekJPanelLayout.createSequentialGroup()
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addTrekJPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(addTrekJPanelLayout.createSequentialGroup()
                                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(25, 25, 25)
                                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(trekNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(trekLocTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(durationSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pickUpPointTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(baseCampTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalTrekkersSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(difficultyLevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(slotLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(addTrekJPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addTrekJPanelLayout.createSequentialGroup()
                                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(trekTravelCostTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(trekAccCostTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(trekMealCostTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(trekMedicalEmergTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(addTrekJPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(trekTaxTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addTrekJPanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(trekTotalCostTxtField)))
                        .addGap(18, 18, 18)
                        .addComponent(submitCostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addTrekJPanelLayout.createSequentialGroup()
                        .addGap(461, 461, 461)
                        .addComponent(jLabel12)))
                .addGap(0, 38, Short.MAX_VALUE))
        );
        addTrekJPanelLayout.setVerticalGroup(
            addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTrekJPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addTrekJPanelLayout.createSequentialGroup()
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(trekNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(trekLocTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(difficultyLevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(durationSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(pickUpPointTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(baseCampTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(totalTrekkersSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(slotLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(trekTravelCostTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17)
                                .addComponent(trekTaxTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(trekAccCostTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)
                                .addComponent(trekTotalCostTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(submitCostButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(trekMealCostTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(trekMedicalEmergTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(backButton)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addTrekJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addTrekJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitCostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCostButtonActionPerformed
        // TODO add your handling code here:
        try{
            if(Float.parseFloat(trekTotalCostTxtField.getText()) <= 0){
                JOptionPane.showMessageDialog(null, "Please enter valid trek cost");
                return;
            }
            request.setStatus("Completed");
            TrekSlotAndCost trekSlotAndCost = request.getTrekSlotAndCost();
            AssignTrekLeaderWorkRequest trekLeaderRequest = new AssignTrekLeaderWorkRequest();
            trekLeaderRequest.setTrekSlotAndCost(trekSlotAndCost);
            trekLeaderRequest.getTrekSlotAndCost().setTrekTravelCost(Float.parseFloat(trekTravelCostTxtField.getText()));
            trekLeaderRequest.getTrekSlotAndCost().setTrekAccomodationCost(Float.parseFloat(trekAccCostTxtField.getText()));
            trekLeaderRequest.getTrekSlotAndCost().setTrekMealCost(Float.parseFloat(trekMealCostTxtField.getText()));
            trekLeaderRequest.getTrekSlotAndCost().setTrekMedicalEmergency(Float.parseFloat(trekMedicalEmergTxtField.getText()));
            trekLeaderRequest.getTrekSlotAndCost().setTrekTax(Float.parseFloat(trekTaxTxtField.getText()));
            trekLeaderRequest.getTrekSlotAndCost().setTrekTotalCost(Float.parseFloat(trekTotalCostTxtField.getText()));
            trekLeaderRequest.getTrekSlotAndCost().setTrekStartDate(request.getTrekSlotAndCost().getTrekStartDate());
            trekLeaderRequest.setTrek(trek);
            trekLeaderRequest.setSender(account);
            trekLeaderRequest.setStatus("Pending for a Trek Leader");
            TrekLeaderOrganization trekLeaderOrg = null;
            for(Organization org : enterprise.getOrganizationDirectory().getOrganizationList()){
                if(org instanceof TrekLeaderOrganization){

                    trekLeaderOrg = (TrekLeaderOrganization) org;
                    break;
                }
            }
            if(trekLeaderOrg != null){
                trekLeaderOrg.getWorkQueue().getWorkRequestList().add(trekLeaderRequest);
            }
            JOptionPane.showMessageDialog(null, "Cost added to the trek "+trek.getTrekName()+" successfully");
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter a valid Number","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_submitCostButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        Component[] comps = this.userProcessContainer.getComponents();
        for(Component comp: comps){
            if(comp instanceof TrekFinanceManagerWorkAreaJPanel){
                TrekFinanceManagerWorkAreaJPanel financeManagerJPanel = (TrekFinanceManagerWorkAreaJPanel) comp;
                try {
                    financeManagerJPanel.populateTaskAllocatedTable();
                    financeManagerJPanel.populateAssignTaskTable();
                    financeManagerJPanel.populateCompletedTaskTable();
                    financeManagerJPanel.populateAddCostWorkRequestTable();
                } catch (ParseException ex) {
                    Logger.getLogger(CostPlanningJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addTrekJPanel;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField baseCampTxtField;
    private javax.swing.JComboBox difficultyLevelComboBox;
    private javax.swing.JSpinner durationSpinner;
    private javax.swing.JTextArea itineraryTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField pickUpPointTxtField;
    private javax.swing.JLabel slotLabel;
    private javax.swing.JButton submitCostButton;
    private javax.swing.JSpinner totalTrekkersSpinner;
    private javax.swing.JTextField trekAccCostTxtField;
    private javax.swing.JTextField trekLocTxtField;
    private javax.swing.JTextField trekMealCostTxtField;
    private javax.swing.JTextField trekMedicalEmergTxtField;
    private javax.swing.JTextField trekNameTxtField;
    private javax.swing.JTextField trekTaxTxtField;
    private javax.swing.JTextField trekTotalCostTxtField;
    private javax.swing.JTextField trekTravelCostTxtField;
    // End of variables declaration//GEN-END:variables
}
