/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.TrekFinanceManagerRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.TrekFinanceOrganization;
import Business.Trek.Trek;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AddTrekCostWorkRequest;
import Business.WorkQueue.LabTestWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import UserInterface.TrekFinanceManagerRole.CostPlanningJPanel;

/**
 *
 * @author dedhi
 */
public class TrekFinanceManagerWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form TrekFinanceManagerWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private TrekFinanceOrganization trekFinanceOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public TrekFinanceManagerWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, TrekFinanceOrganization trekFinanceOrganization, Enterprise enterprise, EcoSystem business) throws ParseException {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.trekFinanceOrganization = trekFinanceOrganization;
        this.enterprise = enterprise;
        this.business = business;
        populateAssignTaskTable();
        populateTaskAllocatedTable();
        populateAddCostWorkRequestTable();
        populateCompletedTaskTable();
    }

    public void populateAssignTaskTable() throws ParseException{
        DefaultTableModel model = (DefaultTableModel) financeManagerAssignTaskTable.getModel();
        model.setRowCount(0);
        for (WorkRequest req : trekFinanceOrganization.getWorkQueue().getWorkRequestList()){
            AddTrekCostWorkRequest request = (AddTrekCostWorkRequest) req;
            System.out.println("request receiver: "+request.getReceiver()+" request status: "+request);
            
            //row[0] = request.getTrekName();
            if(request.getStatus().equals("Pending for costing") && request.getReceiver() == null){
                Object[] row = new Object[7];
                row[0] = request.getTrek();
                row[1] = request.getTrek().getTrekLocation();
                row[2] = dateTimeFormat.format(request.getTrekSlotAndCost().getTrekStartDate());
                System.out.println("trek duration: "+request.getTrek().getTrekDuration());
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(request.getTrekSlotAndCost().getTrekStartDate());
                cal.add(Calendar.DATE,request.getTrek().getTrekDuration());
                Date trekEndDate = dateTimeFormat.parse(dateTimeFormat.format(cal.getTime()));
                row[3] = dateTimeFormat.format(trekEndDate);
                row[4] = request.getSender();
                row[5] = request.getReceiver();
                row[6] = request;
                model.addRow(row);
            }
        }
    }
    
    public void populateTaskAllocatedTable() throws ParseException{
        DefaultTableModel model = (DefaultTableModel) financeManagerTaskAllocatedTable.getModel();
        model.setRowCount(0);
        for (WorkRequest req : account.getWorkQueue().getWorkRequestList()){
            AddTrekCostWorkRequest request = (AddTrekCostWorkRequest) req;
            
            //row[0] = request.getTrekName();
            if(request.getStatus().equals("Pending for costing") && request.getReceiver() == account){
                Object[] row = new Object[7];
                row[0] = request.getTrek();
                row[1] = request.getTrek().getTrekLocation();
                row[2] = dateTimeFormat.format(request.getTrekSlotAndCost().getTrekStartDate());
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(request.getTrekSlotAndCost().getTrekStartDate());
                cal.add(Calendar.DATE,request.getTrek().getTrekDuration());
                Date trekEndDate = dateTimeFormat.parse(dateTimeFormat.format(cal.getTime()));
                row[3] = dateTimeFormat.format(trekEndDate);
                row[4] = request.getSender();
                row[5] = request.getReceiver();
                row[6] = request;
                model.addRow(row);
            }
        }
    }
    
    public void populateAddCostWorkRequestTable(){
        DefaultTableModel model = (DefaultTableModel) addCostWorkRequestTable.getModel();
        model.setRowCount(0);
        
        for(WorkRequest wq: account.getWorkQueue().getWorkRequestList()){
            if(wq instanceof AddTrekCostWorkRequest){
                AddTrekCostWorkRequest req = (AddTrekCostWorkRequest) wq;
                Object[] row = new Object[7];
                row[0] = req.getSender();
                row[1] = req.getReceiver();
                row[2] = req.getTrek();
                row[3] = dateTimeFormat.format(req.getTrekSlotAndCost().getTrekStartDate());
                row[4] = req.getStatus();
                model.addRow(row);
            }
        }
    }
    
    public void populateCompletedTaskTable() throws ParseException{
        DefaultTableModel model = (DefaultTableModel) completedTasksTable.getModel();
        model.setRowCount(0);
        for(WorkRequest wq: account.getWorkQueue().getWorkRequestList()){
            if(wq instanceof AddTrekCostWorkRequest){
                AddTrekCostWorkRequest req = (AddTrekCostWorkRequest) wq;
                if(req.getStatus().equals("Completed")){
                    Object[] row = new Object[11];
                    row[0] = req.getTrek();
                    row[1] = req.getTrek().getTrekLocation();
                    row[2] = dateTimeFormat.format(req.getTrekSlotAndCost().getTrekStartDate());
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.setTime(req.getTrekSlotAndCost().getTrekStartDate());
                    cal.add(Calendar.DATE,req.getTrek().getTrekDuration());
                    Date trekEndDate = dateTimeFormat.parse(dateTimeFormat.format(cal.getTime()));
                    row[3] = dateTimeFormat.format(trekEndDate);
                    row[4] = req.getTrekSlotAndCost().getTotalSeats();
                    row[5] = req.getTrekSlotAndCost().getTrekTravelCost();
                    row[6] = req.getTrekSlotAndCost().getTrekAccomodationCost();
                    row[7] = req.getTrekSlotAndCost().getTrekMealCost();
                    row[8] = req.getTrekSlotAndCost().getTrekMedicalEmergency();
                    row[9] = req.getTrekSlotAndCost().getTrekTax();
                    row[10] = req.getTrekSlotAndCost().getTrekTotalCost();
                    model.addRow(row);
                }
            }
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        financeManagerAssignTaskTable = new javax.swing.JTable();
        assignToMeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        financeManagerTaskAllocatedTable = new javax.swing.JTable();
        addCostButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        completedTasksTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        addCostWorkRequestTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(33, 108, 205));

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 255));
        jTabbedPane1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(33, 108, 205));

        financeManagerAssignTaskTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        financeManagerAssignTaskTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trek Name", "Trek Location", "Trek Start Date", "Trek End Date", "Request Sender", "Request Receiver", "Request Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        financeManagerAssignTaskTable.setRowHeight(22);
        jScrollPane1.setViewportView(financeManagerAssignTaskTable);
        if (financeManagerAssignTaskTable.getColumnModel().getColumnCount() > 0) {
            financeManagerAssignTaskTable.getColumnModel().getColumn(0).setResizable(false);
            financeManagerAssignTaskTable.getColumnModel().getColumn(1).setResizable(false);
            financeManagerAssignTaskTable.getColumnModel().getColumn(2).setResizable(false);
            financeManagerAssignTaskTable.getColumnModel().getColumn(3).setResizable(false);
            financeManagerAssignTaskTable.getColumnModel().getColumn(4).setResizable(false);
            financeManagerAssignTaskTable.getColumnModel().getColumn(5).setResizable(false);
            financeManagerAssignTaskTable.getColumnModel().getColumn(6).setResizable(false);
        }

        assignToMeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekFinanceManagerRole/assignToMeButton.png"))); // NOI18N
        assignToMeButton.setContentAreaFilled(false);
        assignToMeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        assignToMeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignToMeButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pick a Task");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(assignToMeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1515, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(550, 550, 550)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(assignToMeButton)
                .addGap(309, 309, 309))
        );

        jTabbedPane1.addTab("Pick a Task", jPanel1);

        jPanel2.setBackground(new java.awt.Color(33, 108, 205));

        financeManagerTaskAllocatedTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        financeManagerTaskAllocatedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trek Name", "Trek Location", "Trek Start Date", "Trek End Date", "Request Sender", "Request Receive", "Request Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        financeManagerTaskAllocatedTable.setRowHeight(22);
        jScrollPane2.setViewportView(financeManagerTaskAllocatedTable);
        if (financeManagerTaskAllocatedTable.getColumnModel().getColumnCount() > 0) {
            financeManagerTaskAllocatedTable.getColumnModel().getColumn(0).setResizable(false);
            financeManagerTaskAllocatedTable.getColumnModel().getColumn(1).setResizable(false);
            financeManagerTaskAllocatedTable.getColumnModel().getColumn(2).setResizable(false);
            financeManagerTaskAllocatedTable.getColumnModel().getColumn(3).setResizable(false);
            financeManagerTaskAllocatedTable.getColumnModel().getColumn(4).setResizable(false);
            financeManagerTaskAllocatedTable.getColumnModel().getColumn(5).setResizable(false);
            financeManagerTaskAllocatedTable.getColumnModel().getColumn(6).setResizable(false);
        }

        addCostButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekFinanceManagerRole/addCostButton.png"))); // NOI18N
        addCostButton.setContentAreaFilled(false);
        addCostButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addCostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCostButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("My Pending Tasks");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(500, 500, 500)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1505, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addCostButton)
                .addContainerGap(480, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("My Pending Tasks", jPanel2);

        jPanel4.setBackground(new java.awt.Color(33, 108, 205));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("My Completed Tasks");

        completedTasksTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        completedTasksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trek Name", "Trek Location", "Trek Start Date", "Trek End Date", "Total Number of Trekkers", "Travel Cost/person in $", "Accomodation/person Cost in $", "Meal Cost/person in $", "Medical Emergency Cost/person in $", "Tax in $", "Total Cost/person in $"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        completedTasksTable.setRowHeight(22);
        jScrollPane4.setViewportView(completedTasksTable);
        if (completedTasksTable.getColumnModel().getColumnCount() > 0) {
            completedTasksTable.getColumnModel().getColumn(0).setResizable(false);
            completedTasksTable.getColumnModel().getColumn(1).setResizable(false);
            completedTasksTable.getColumnModel().getColumn(2).setResizable(false);
            completedTasksTable.getColumnModel().getColumn(3).setResizable(false);
            completedTasksTable.getColumnModel().getColumn(4).setResizable(false);
            completedTasksTable.getColumnModel().getColumn(5).setResizable(false);
            completedTasksTable.getColumnModel().getColumn(6).setResizable(false);
            completedTasksTable.getColumnModel().getColumn(7).setResizable(false);
            completedTasksTable.getColumnModel().getColumn(8).setResizable(false);
            completedTasksTable.getColumnModel().getColumn(9).setResizable(false);
            completedTasksTable.getColumnModel().getColumn(10).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(415, 415, 415)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(873, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("My Completed Tasks", jPanel4);

        jPanel3.setBackground(new java.awt.Color(33, 108, 205));

        addCostWorkRequestTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        addCostWorkRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request Sender", "Request Receiver", "Trek Name", "Trek Slot", "Request Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        addCostWorkRequestTable.setRowHeight(22);
        jScrollPane3.setViewportView(addCostWorkRequestTable);
        if (addCostWorkRequestTable.getColumnModel().getColumnCount() > 0) {
            addCostWorkRequestTable.getColumnModel().getColumn(0).setResizable(false);
            addCostWorkRequestTable.getColumnModel().getColumn(1).setResizable(false);
            addCostWorkRequestTable.getColumnModel().getColumn(2).setResizable(false);
            addCostWorkRequestTable.getColumnModel().getColumn(3).setResizable(false);
            addCostWorkRequestTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("List of Request received from Trek Manager to add cost");

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("My Work Requests");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(531, 531, 531)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel4)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(308, 308, 308))
        );

        jTabbedPane1.addTab("My Work Requests", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void assignToMeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignToMeButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = financeManagerAssignTaskTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a Trek","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        AddTrekCostWorkRequest request = (AddTrekCostWorkRequest) financeManagerAssignTaskTable.getValueAt(selectedRow, 6);
        request.setReceiver(account);
        account.getWorkQueue().getWorkRequestList().add(request);
        try {
            populateAssignTaskTable();
            populateTaskAllocatedTable();
        } catch (ParseException ex) {
            Logger.getLogger(TrekFinanceManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        populateAddCostWorkRequestTable();
    }//GEN-LAST:event_assignToMeButtonActionPerformed

    private void addCostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCostButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = financeManagerTaskAllocatedTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a Trek","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        populateAddCostWorkRequestTable();
        AddTrekCostWorkRequest request = (AddTrekCostWorkRequest) financeManagerTaskAllocatedTable.getValueAt(selectedRow, 6);
        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
        userProcessContainer.add("CostPlanningJPanel",new CostPlanningJPanel(userProcessContainer,request,account,enterprise));
        layout.next(userProcessContainer);
        
    }//GEN-LAST:event_addCostButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCostButton;
    private javax.swing.JTable addCostWorkRequestTable;
    private javax.swing.JButton assignToMeButton;
    private javax.swing.JTable completedTasksTable;
    private javax.swing.JTable financeManagerAssignTaskTable;
    private javax.swing.JTable financeManagerTaskAllocatedTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
