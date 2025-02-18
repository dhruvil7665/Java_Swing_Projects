/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;


import Business.Car;
import Business.CarCatalog;
import java.awt.CardLayout;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author shahd
 */
public class UploadJPanel extends javax.swing.JPanel {

    /**
     * Creates new form UploadJPanel
     */
   private JPanel rightPanel;
   private CarCatalog carCatalog;
   public UploadJPanel(JPanel rightPanel, CarCatalog carCatalog) {
         initComponents();
         this.rightPanel=rightPanel;
         this.carCatalog=carCatalog;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        uploadLabel = new javax.swing.JLabel();
        availableLabel = new javax.swing.JLabel();
        brandNameLabel = new javax.swing.JLabel();
        brandNameText = new javax.swing.JTextField();
        manufLabel = new javax.swing.JLabel();
        manufTextField = new javax.swing.JTextField();
        minimumSeatsLabel = new javax.swing.JLabel();
        maxSeatsLabel = new javax.swing.JLabel();
        minimumSeatsTextField = new javax.swing.JTextField();
        maxFieldTxtField = new javax.swing.JTextField();
        modelNumLabel = new javax.swing.JLabel();
        modelNumTxtField = new javax.swing.JTextField();
        availCityLabel = new javax.swing.JLabel();
        carTyoeLabel = new javax.swing.JLabel();
        maintainLabel = new javax.swing.JLabel();
        cityCheckBox = new javax.swing.JComboBox<>();
        carTypeCheckBox = new javax.swing.JComboBox<>();
        createButton = new javax.swing.JButton();
        manufactureLabel = new javax.swing.JLabel();
        manufacturetxtField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        serialNumberLabel = new javax.swing.JLabel();
        serialNumberText = new javax.swing.JTextField();
        yesCheck = new javax.swing.JCheckBox();
        expireCheck = new javax.swing.JCheckBox();

        uploadLabel.setText("Upload Your Car and Respective Details");

        availableLabel.setText("Is the Car Available");

        brandNameLabel.setText("Brand_Name of the Car");

        manufLabel.setText("Manufacture Year");

        minimumSeatsLabel.setText("Mininum Seats");

        maxSeatsLabel.setText("Maximum Seats");

        modelNumLabel.setText("Model Number");

        availCityLabel.setText("Available City");

        carTyoeLabel.setText("Car_Type");

        maintainLabel.setText("Maintaince Certificate ");

        cityCheckBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boston", "NewYork", "Chicago", "Seatlle", "Austin" }));

        carTypeCheckBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sedan", "SUV", "Hatchback", "SportsCar" }));

        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        manufactureLabel.setText("Manufacturer");

        backButton.setText("<<BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        serialNumberLabel.setText("Serial Number");

        yesCheck.setText("Yes");

        expireCheck.setText("Not Expired");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(uploadLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maxSeatsLabel)
                            .addComponent(modelNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(availableLabel)
                            .addComponent(brandNameLabel)
                            .addComponent(manufLabel)
                            .addComponent(minimumSeatsLabel)
                            .addComponent(availCityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carTyoeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(manufactureLabel)
                            .addComponent(serialNumberLabel))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modelNumTxtField)
                            .addComponent(maxFieldTxtField)
                            .addComponent(minimumSeatsTextField)
                            .addComponent(cityCheckBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(manufTextField)
                            .addComponent(carTypeCheckBox, 0, 310, Short.MAX_VALUE)
                            .addComponent(manufacturetxtField)
                            .addComponent(serialNumberText)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(expireCheck)
                                    .addComponent(yesCheck))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(brandNameText))))
                .addGap(356, 356, 356))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maintainLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(uploadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(availableLabel)
                    .addComponent(yesCheck))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(brandNameLabel)
                    .addComponent(brandNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(manufLabel)
                    .addComponent(manufTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(minimumSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(minimumSeatsLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maxSeatsLabel)
                    .addComponent(maxFieldTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modelNumLabel)
                    .addComponent(modelNumTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(availCityLabel)
                    .addComponent(cityCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carTyoeLabel)
                    .addComponent(carTypeCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maintainLabel)
                    .addComponent(expireCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manufactureLabel)
                    .addComponent(manufacturetxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serialNumberText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serialNumberLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton)
                    .addComponent(backButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        // TODO add your handling code here:
    
           Car car = carCatalog.addCar();
          
           
           //validations for required fields
           String name = brandNameText.getText();
            if(name==null || name.equals("")){
            JOptionPane.showMessageDialog(null, "Brand name cannot be empty!");
            return;
            }
           
            String model = modelNumTxtField.getText();
            if(model==null || name.equals("")){
            JOptionPane.showMessageDialog(null, "Model Number cannot be empty!");
            return;
            }
            
            String manuf = manufacturetxtField.getText();
            if(manuf==null || name.equals("")){
            JOptionPane.showMessageDialog(null, "Model Number cannot be empty!");
            return;
            }
           
           
           car.setBrand(brandNameText.getText());
           //you cannot parse null value so enter data for code to run or keep a try catch block
           //Validation for Integer fields
           try{
           car.setManufactured_year(Integer.parseInt(manufTextField.getText()));
           car.setManufacture(manufacturetxtField.getText());
           car.setSerial_num(Integer.parseInt(serialNumberText.getText()));
           car.setMin_seats(Integer.parseInt(minimumSeatsTextField.getText()));
           }catch(NumberFormatException e){
               JOptionPane.showMessageDialog(null,"Number Field cannot be null","ERROR",JOptionPane.ERROR_MESSAGE);
           }
           
           if(!Pattern.matches("^\\d+$", manufTextField.getText())){
               JOptionPane.showMessageDialog(null, "Manufacture year cannot be string");
           }else if(!Pattern.matches("^\\d+$", minimumSeatsTextField.getText())){
           JOptionPane.showMessageDialog(null, "Minimum seats year cannot be string");
           
           }else if(!Pattern.matches("^\\d+$", maxFieldTxtField.getText())){
           
               JOptionPane.showMessageDialog(null, "maximum seats cannot be string");
           }
           else{
           car.setMax_seats(Integer.parseInt(maxFieldTxtField.getText()));
           car.setModel_num(modelNumTxtField.getText());
           
           car.setAvailble_city((String)cityCheckBox.getSelectedItem());
           car.setCar_Type((String)carTypeCheckBox.getSelectedItem());
           
           if(yesCheck.isSelected()){
           car.setAvailable(true);
           }
           else {
           car.setAvailable(false);
           }
          
           if(expireCheck.isSelected()){
           car.setMaintenance_certificate(true);
           }
           else{
           car.setMaintenance_certificate(false);
           }
           
           
           JOptionPane.showMessageDialog(null,"success","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
           
              brandNameText.setText("");
              yesCheck.setSelected(false);
      
              manufTextField.setText("");
              minimumSeatsTextField.setText("");
              maxFieldTxtField.setText("");
              modelNumTxtField.setText("");
              cityCheckBox.setSelectedItem(car.getAvailble_city());
             carTypeCheckBox.setSelectedItem(car.getCar_Type());
             manufacturetxtField.setText("");
             serialNumberText.setText("");
      
             expireCheck.setSelected(false);
           
           
           
           }
     
        
    }//GEN-LAST:event_createButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
           this.rightPanel.remove(this);
           CardLayout layout = (CardLayout)this.rightPanel.getLayout();
           layout.previous(rightPanel);
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel availCityLabel;
    private javax.swing.JLabel availableLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel brandNameLabel;
    private javax.swing.JTextField brandNameText;
    private javax.swing.JLabel carTyoeLabel;
    private javax.swing.JComboBox<String> carTypeCheckBox;
    private javax.swing.JComboBox<String> cityCheckBox;
    private javax.swing.JButton createButton;
    private javax.swing.JCheckBox expireCheck;
    private javax.swing.JLabel maintainLabel;
    private javax.swing.JLabel manufLabel;
    private javax.swing.JTextField manufTextField;
    private javax.swing.JLabel manufactureLabel;
    private javax.swing.JTextField manufacturetxtField;
    private javax.swing.JTextField maxFieldTxtField;
    private javax.swing.JLabel maxSeatsLabel;
    private javax.swing.JLabel minimumSeatsLabel;
    private javax.swing.JTextField minimumSeatsTextField;
    private javax.swing.JLabel modelNumLabel;
    private javax.swing.JTextField modelNumTxtField;
    private javax.swing.JLabel serialNumberLabel;
    private javax.swing.JTextField serialNumberText;
    private javax.swing.JLabel uploadLabel;
    private javax.swing.JCheckBox yesCheck;
    // End of variables declaration//GEN-END:variables
}
