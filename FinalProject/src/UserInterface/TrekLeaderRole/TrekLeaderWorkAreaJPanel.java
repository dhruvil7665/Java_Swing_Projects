/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.TrekLeaderRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.TrekLeaderOrganization;
import Business.Organization.TrekManagerOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AssignTrekLeaderWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dedhi
 */
public class TrekLeaderWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form TrekLeaderWorkArea
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private TrekLeaderOrganization trekLeaderOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public TrekLeaderWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, TrekLeaderOrganization trekLeaderOrganization, Enterprise enterprise, EcoSystem business) throws ParseException {
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.trekLeaderOrganization = trekLeaderOrganization;
        this.enterprise = enterprise;
        this.business = business;
        initComponents();
        populateAssignTrekTable();
        populateTrekLeaderTable();
        populateConfirmTrekLeaderWorkRequestTable();
    }
    
    public void populateAssignTrekTable() throws ParseException{
        DefaultTableModel model = (DefaultTableModel) assignTrekLeaderTable.getModel();
        model.setRowCount(0);
        for (WorkRequest req : trekLeaderOrganization.getWorkQueue().getWorkRequestList()){
            AssignTrekLeaderWorkRequest request = (AssignTrekLeaderWorkRequest) req;
            
            //row[0] = request.getTrekName();
            if(request.getStatus().equals("Pending for a Trek Leader") && request.getReceiver() == null){
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
    
    public void populateTrekLeaderTable() throws ParseException{
        DefaultTableModel model = (DefaultTableModel) assignedTaskTable.getModel();
        model.setRowCount(0);
        ArrayList<WorkRequest> workRequestList = account.getWorkQueue().getWorkRequestList();
        //Sorting required
        for (WorkRequest req : workRequestList){
            AssignTrekLeaderWorkRequest request = (AssignTrekLeaderWorkRequest) req;
            Object[] row = new Object[7];
            //row[0] = request.getTrekName();
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
    
     public void populateConfirmTrekLeaderWorkRequestTable(){
        DefaultTableModel model = (DefaultTableModel) confirmTrekLeaderWorkRequestTable.getModel();
        model.setRowCount(0);
        
        for(WorkRequest wq: account.getWorkQueue().getWorkRequestList()){
            if(wq instanceof AssignTrekLeaderWorkRequest){
                AssignTrekLeaderWorkRequest req = (AssignTrekLeaderWorkRequest) wq;
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
        assignTrekLeaderTable = new javax.swing.JTable();
        assignToMeButton = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        assignedTaskTable = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        confirmTrekLeaderWorkRequestTable = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(33, 108, 205));

        jTabbedPane1.setBackground(new java.awt.Color(33, 108, 205));
        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 255));
        jTabbedPane1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(33, 108, 205));

        assignTrekLeaderTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        assignTrekLeaderTable.setModel(new javax.swing.table.DefaultTableModel(
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
        assignTrekLeaderTable.setRowHeight(22);
        jScrollPane1.setViewportView(assignTrekLeaderTable);

        assignToMeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekLeaderRole/requestTrekButton.png"))); // NOI18N
        assignToMeButton.setContentAreaFilled(false);
        assignToMeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        assignToMeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignToMeButtonActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Pick up Assignment");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(assignToMeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1475, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(assignToMeButton)
                .addGap(257, 257, 257))
        );

        jTabbedPane1.addTab("Pick up Assignment", jPanel1);

        jPanel2.setBackground(new java.awt.Color(33, 108, 205));

        assignedTaskTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        assignedTaskTable.setModel(new javax.swing.table.DefaultTableModel(
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
        assignedTaskTable.setRowHeight(22);
        jScrollPane2.setViewportView(assignedTaskTable);

        jLabel28.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("My Assignments");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(883, 883, 883))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(374, 374, 374))
        );

        jTabbedPane1.addTab("My Assignments", jPanel2);

        jPanel3.setBackground(new java.awt.Color(33, 108, 205));

        confirmTrekLeaderWorkRequestTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        confirmTrekLeaderWorkRequestTable.setModel(new javax.swing.table.DefaultTableModel(
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
        confirmTrekLeaderWorkRequestTable.setRowHeight(22);
        jScrollPane6.setViewportView(confirmTrekLeaderWorkRequestTable);
        if (confirmTrekLeaderWorkRequestTable.getColumnModel().getColumnCount() > 0) {
            confirmTrekLeaderWorkRequestTable.getColumnModel().getColumn(0).setResizable(false);
            confirmTrekLeaderWorkRequestTable.getColumnModel().getColumn(1).setResizable(false);
            confirmTrekLeaderWorkRequestTable.getColumnModel().getColumn(2).setResizable(false);
            confirmTrekLeaderWorkRequestTable.getColumnModel().getColumn(3).setResizable(false);
            confirmTrekLeaderWorkRequestTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel21.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("List of Request sent to Trek Manager for Confirmation");

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("My Work Request");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(511, 511, 511)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1508, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );

        jTabbedPane1.addTab("My Work Requests", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void assignToMeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignToMeButtonActionPerformed
        // TODO add your handling code here:
        TrekManagerOrganization trekManagerOrganization = null;
        int selectedRow = assignTrekLeaderTable.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a Trek","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        AssignTrekLeaderWorkRequest request = (AssignTrekLeaderWorkRequest) assignTrekLeaderTable.getValueAt(selectedRow, 6);
        request.setSender(account);
        request.setStatus("Pending to confirm the assignment");
        account.getWorkQueue().getWorkRequestList().add(request);
        for(Organization org : enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof TrekManagerOrganization){
                trekManagerOrganization = (TrekManagerOrganization) org;
                break;
            }
        }
        if(trekManagerOrganization != null){
            trekManagerOrganization.getWorkQueue().getWorkRequestList().add(request);
        }
        
        JOptionPane.showMessageDialog(null, "Request sent to the Manager successfully");
        try {
            populateAssignTrekTable();
            populateTrekLeaderTable();
        } catch (ParseException ex) {
            Logger.getLogger(TrekLeaderWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        populateConfirmTrekLeaderWorkRequestTable();
    }//GEN-LAST:event_assignToMeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton assignToMeButton;
    private javax.swing.JTable assignTrekLeaderTable;
    private javax.swing.JTable assignedTaskTable;
    private javax.swing.JTable confirmTrekLeaderWorkRequestTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
