/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.SportsStoreFinanceManagerRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.CustomerOrganization;
import Business.Organization.Organization;
import Business.Organization.SportsStoreFinanceOrganization;
import Business.Organization.TrekManagerOrganization;
import Business.Products.CustomerOrders;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.LabTestWorkRequest;
import Business.WorkQueue.ProductAddWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author shahd
 */
public class SportsStoreFinanceManagerWorkAreaJPanel extends javax.swing.JPanel {

    
    private JPanel userProcessContainer;
    private SportsStoreFinanceOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private EcoSystem business;
    /**
     * Creates new form SportsStoreFinanceManagerWorkAreaJPanel
     */
    public SportsStoreFinanceManagerWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise , EcoSystem business) {
       initComponents();
        this.userProcessContainer = userProcessContainer;
        this.organization = (SportsStoreFinanceOrganization) organization;
        this.enterprise = enterprise;
        this.userAccount = account;
        this.business=business;
        //valueLabel.setText(enterprise.getName());
        populateTable();
        populateApprovalTable();
        pieDiagram();
        populateMyTaskTable();
    }

    
    
    public void populateTable(){
        DefaultTableModel model = (DefaultTableModel)financeManagerRequestTable.getModel();
        
        model.setRowCount(0);
        
        for(WorkRequest request : organization.getWorkQueue().getWorkRequestList()){
            if(request instanceof ProductAddWorkRequest){
          //  if(request.equals("Sent")){
            Object[] row = new Object[4];
            row[0] = request;
            row[1] = request.getSender().getEmployee().getName();
            row[2] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getName();
            row[3] = request.getStatus();
            
            model.addRow(row);
            }
         //   }
        }
    }
    
    
     public void populateApprovalTable(){
        DefaultTableModel model = (DefaultTableModel)costAssigningTable.getModel();
        
        model.setRowCount(0);
        
        for(WorkRequest request : userAccount.getWorkQueue().getWorkRequestList()){
             if(request instanceof ProductAddWorkRequest){
           // if(request.equals("Sent")){
            Object[] row = new Object[4];
            row[0] = request;
            row[1] = request.getSender().getEmployee().getName();
            row[2] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getName();
            row[3] = request.getStatus();
            
            model.addRow(row);
            }
            // }
        }
    }
     
       public void populateMyTaskTable(){
        DefaultTableModel model = (DefaultTableModel)costAssigningTable1.getModel();
        
        model.setRowCount(0);
        
        for(WorkRequest request : userAccount.getWorkQueue().getWorkRequestList()){
             if(request instanceof ProductAddWorkRequest){
            Object[] row = new Object[4];
            row[0] = request;
            row[1] = request.getSender().getEmployee().getName();
            row[2] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getName();
            row[3] = request.getStatus();
            
            model.addRow(row);
             }
        }
    }
      public void pieDiagram(){
        
        Map<String,Integer> categoryMap = RevenueBasedOnCategories();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(Map.Entry<String,Integer> entry: categoryMap.entrySet()){
            dataset.setValue(entry.getValue(),entry.getKey(),entry.getKey());
        }
       
        JFreeChart chart = ChartFactory.createBarChart("Revenue Generated Based on Categories","Categories","Total Revenue in $", dataset,PlotOrientation.VERTICAL, true, true, true);
       // ChartFrame yourFrame = new ChartFrame("Your title", chart);
        ChartPanel yourFrame = new ChartPanel(chart);
        //yourFrame.setPreferredSize(new Dimension(500,100));
        dashBoardJPanel.add(yourFrame);
        dashBoardJPanel.validate();
        
   
    }
      
        public Map<String,Integer> RevenueBasedOnCategories(){
        
         CustomerOrganization customerOrganization = null;
        for(Organization org : enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof CustomerOrganization){
                customerOrganization = (CustomerOrganization) org;
                break;
            }
        }
         
         //System.out.println("cust"+customerOrganization);
         Map<String,Integer> countMap =  new HashMap<String, Integer>();
        ArrayList<CustomerOrders> orderList = customerOrganization.getOrderDir().getOrderList();
        
        for(CustomerOrders orders: orderList){
            
            if(countMap.containsKey(orders.getProduct().getCategory())){
                int cnt = countMap.get(orders.getProduct().getCategory());
                countMap.put(orders.getProduct().getCategory(),countMap.get(orders.getProduct().getCategory())+(int)orders.getProduct().getSellingPrice());
            }
            else{
                countMap.put(orders.getProduct().getCategory(),(int)orders.getProduct().getSellingPrice());
            }
        }
        return countMap;
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        financeManagerRequestTable = new javax.swing.JTable();
        assignToMeButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        costAssigningTable = new javax.swing.JTable();
        assignCostBtn1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        costAssigningTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        dashBoardJPanel = new javax.swing.JPanel();

        jLabel2.setText("jLabel2");

        jTabbedPane1.setBackground(new java.awt.Color(33, 108, 205));
        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 255));
        jTabbedPane1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(33, 108, 205));

        financeManagerRequestTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        financeManagerRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request Status", "Sender", "Receiver", "Approval From Finance Status"
            }
        ));
        financeManagerRequestTable.setRowHeight(22);
        jScrollPane3.setViewportView(financeManagerRequestTable);

        assignToMeButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/TrekFinanceManagerRole/assignToMeButton.png"))); // NOI18N
        assignToMeButton1.setContentAreaFilled(false);
        assignToMeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignToMeButton1ActionPerformed(evt);
            }
        });

        costAssigningTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        costAssigningTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request Status", "Sender", "Receiver", "Approval Status"
            }
        ));
        costAssigningTable.setRowHeight(22);
        jScrollPane4.setViewportView(costAssigningTable);

        assignCostBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/TrekFinanceManagerRole/addCostButton.png"))); // NOI18N
        assignCostBtn1.setContentAreaFilled(false);
        assignCostBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignCostBtn1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tasks to be Assigned");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("My Pending Tasks");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(336, 336, 336)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(assignCostBtn1)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(assignToMeButton1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(assignToMeButton1)
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(assignCostBtn1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Home", jPanel1);

        jPanel2.setBackground(new java.awt.Color(33, 108, 205));

        costAssigningTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        costAssigningTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request Status", "Sender", "Receiver", "Approval Status"
            }
        ));
        costAssigningTable1.setRowHeight(22);
        jScrollPane5.setViewportView(costAssigningTable1);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("All Task Assigned To Me");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(409, 409, 409))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(422, 422, 422))
        );

        jTabbedPane1.addTab("My Tasks", jPanel2);

        dashBoardJPanel.setBackground(new java.awt.Color(33, 108, 205));
        dashBoardJPanel.setLayout(new java.awt.GridLayout(1, 0));
        jTabbedPane1.addTab("DashBoard", dashBoardJPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void assignToMeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignToMeButton1ActionPerformed
        // TODO add your handling code here:
       
        int selectedRow = financeManagerRequestTable.getSelectedRow();

        if (selectedRow < 0){
            return;
        }

        WorkRequest request = (WorkRequest)financeManagerRequestTable.getValueAt(selectedRow, 0);
        request.setReceiver(userAccount);
        request.setStatus("Pending");
        userAccount.getWorkQueue().getWorkRequestList().add(request);
        populateTable();
        populateApprovalTable();
        populateMyTaskTable();
    }//GEN-LAST:event_assignToMeButton1ActionPerformed

    private void assignCostBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignCostBtn1ActionPerformed
        // TODO add your handling code here:
        
        int selectedRow = costAssigningTable.getSelectedRow();

        if (selectedRow < 0){
            return;
        }

        ProductAddWorkRequest request = (ProductAddWorkRequest)costAssigningTable.getValueAt(selectedRow, 0);

        CostAssignJPanel costAssignJPanel = new CostAssignJPanel(userProcessContainer, request);
        userProcessContainer.add("costAssignJPanel", costAssignJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
        
    }//GEN-LAST:event_assignCostBtn1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton assignCostBtn1;
    private javax.swing.JButton assignToMeButton1;
    private javax.swing.JTable costAssigningTable;
    private javax.swing.JTable costAssigningTable1;
    private javax.swing.JPanel dashBoardJPanel;
    private javax.swing.JTable financeManagerRequestTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
