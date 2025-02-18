/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Business.Car;
import Business.CarCatalog;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shahd
 */
public class multiFilterJPanel extends javax.swing.JPanel {

    /**
     * Creates new form multiFilterJPanel
     */
     private JPanel rightPanel;
     private CarCatalog carCatalog;
     private Car car;
     DefaultTableModel dtm;
    public multiFilterJPanel(JPanel rightPanel, CarCatalog carCatalog) {
    //    initComponents();
 
        initComponents();
        this.rightPanel=rightPanel;
        this.carCatalog=carCatalog;     
    }
    
    public void populateList(CarCatalog carCatalog){
         dtm = (DefaultTableModel)manuList.getModel();
         dtm.setRowCount(0);
          ArrayList<String> search = new ArrayList<String>();
        for(Car c : carCatalog.getCarCatalog()){
           
            if(!search.contains(c.getManufacture())){
            search.add(c.getManufacture());   
            Object[] row = new Object[dtm.getColumnCount()];
            row[0]=c.getManufacture();
            dtm.addRow(row);
            }
        }
    
    }
    
  /*  public void Populate(CarCatalog){
    DefaultListModel<String> dlm = new DefaultListModel<String>();
    for(Car c : carCatalog.getCarCatalog()){
    dlm.addElement(c.getManufacture());
    }    
    JList<String> jList = new JList<String>(dlm);
    System.out.println(jList.getModel().toString()); //TO TEST if Jlist successfully added catalogo      
}         
    
    
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectManuButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        manuList = new javax.swing.JTable();
        showBrandsButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        brandTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        viewCarsButton = new javax.swing.JButton();
        viewDetailsButton = new javax.swing.JButton();
        backBUtton = new javax.swing.JButton();

        selectManuButton.setText("Select Manufacturer");
        selectManuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectManuButtonActionPerformed(evt);
            }
        });

        manuList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Manufacturer"
            }
        ));
        jScrollPane1.setViewportView(manuList);

        showBrandsButton.setText("Show Brands of Selected Manufacturer");
        showBrandsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBrandsButtonActionPerformed(evt);
            }
        });

        brandTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "brands"
            }
        ));
        jScrollPane2.setViewportView(brandTable);

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Brand", "City", "Car Type", "Is Available"
            }
        ));
        jScrollPane3.setViewportView(mainTable);

        viewCarsButton.setText("View Cars");
        viewCarsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCarsButtonActionPerformed(evt);
            }
        });

        viewDetailsButton.setText("View Details");
        viewDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailsButtonActionPerformed(evt);
            }
        });

        backBUtton.setText("<<BACK");
        backBUtton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBUttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectManuButton)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(showBrandsButton)
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(viewCarsButton)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(viewDetailsButton)
                            .addComponent(backBUtton))))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(selectManuButton)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(showBrandsButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(29, 29, 29)
                .addComponent(viewCarsButton)
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(viewDetailsButton)
                        .addGap(18, 18, 18)
                        .addComponent(backBUtton)))
                .addGap(340, 340, 340))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void showBrandsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showBrandsButtonActionPerformed
        // TODO add your handling code here:
        ArrayList<String> search = new ArrayList<String>();
        
        int selectedRows = manuList.getSelectedRow();
        
        if(selectedRows < 0){
            JOptionPane.showMessageDialog(null, "Please select a Row","Warning", JOptionPane.WARNING_MESSAGE);
        } else{
            //Car car = (Car)manuList.getValueAt(selectedRows,1);
            String sel = (String)manuList.getValueAt(selectedRows, 0);
            
            
            
             dtm = (DefaultTableModel)brandTable.getModel();
             dtm.setRowCount(0);
         search.clear();
        for(Car c : carCatalog.getCarCatalog()){
               if(c.getManufacture().equals(sel)&&(!search.contains(c.getBrand()))){
                   search.add(c.getBrand());
            Object[] row = new Object[dtm.getColumnCount()];
            
            row[0]=c.getBrand();
            
            //row[1]=c.getManufacture();
            
            
            dtm.addRow(row);
               }
        }
        }
        
    }//GEN-LAST:event_showBrandsButtonActionPerformed

    private void viewCarsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCarsButtonActionPerformed
        // TODO add your handling code here:
        
        int selectedRows = brandTable.getSelectedRow();
        
        if(selectedRows < 0){
            JOptionPane.showMessageDialog(null, "Please select a Row","Warning", JOptionPane.WARNING_MESSAGE);
        } else{
            //Car car = (Car)manuList.getValueAt(selectedRows,1);
            String sel = (String)brandTable.getValueAt(selectedRows, 0);
            
            
            
             dtm = (DefaultTableModel)mainTable.getModel();
             dtm.setRowCount(0);
         
        for(Car c : carCatalog.getCarCatalog()){
               if(c.getBrand().equals(sel)){
            Object[] row = new Object[dtm.getColumnCount()];
            row[0]=c;
            row[1]=c.getAvailble_city();
            row[2]=c.getCar_Type();
            row[3]=c.isAvailable();
            
            
            dtm.addRow(row);
               }
        }
        }
        
        
    }//GEN-LAST:event_viewCarsButtonActionPerformed

    private void viewDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailsButtonActionPerformed
        // TODO add your handling code here:
        
        int selectedRows = mainTable.getSelectedRow();
        
        if(selectedRows < 0){
            JOptionPane.showMessageDialog(null, "Please select a Row","Warning", JOptionPane.WARNING_MESSAGE);
        } else{
            Car car = (Car)mainTable.getValueAt(selectedRows,0);
            ViewCarPanel viewPanel = new ViewCarPanel(rightPanel, car, carCatalog);
            rightPanel.add("ViewPanel",viewPanel);
            CardLayout layout = (CardLayout) rightPanel.getLayout();
            layout.next(rightPanel);
        }
    }//GEN-LAST:event_viewDetailsButtonActionPerformed

    private void backBUttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBUttonActionPerformed
        // TODO add your handling code here:
        
         this.rightPanel.remove(this);
        CardLayout layout = (CardLayout)this.rightPanel.getLayout();

        Component[] comps = this.rightPanel.getComponents();
        for(Component comp :  comps){
            if(comp instanceof CarManagePanel){
                CarManagePanel carManagePanel = (CarManagePanel) comp;
              
            }
        }
        layout.previous(rightPanel);
        
        
    }//GEN-LAST:event_backBUttonActionPerformed

    private void selectManuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectManuButtonActionPerformed
        // TODO add your handling code here:
        
         populateList(carCatalog);
    }//GEN-LAST:event_selectManuButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBUtton;
    private javax.swing.JTable brandTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable mainTable;
    private javax.swing.JTable manuList;
    private javax.swing.JButton selectManuButton;
    private javax.swing.JButton showBrandsButton;
    private javax.swing.JButton viewCarsButton;
    private javax.swing.JButton viewDetailsButton;
    // End of variables declaration//GEN-END:variables
}
