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
import Business.Organization.TrekFinanceOrganization;
import Business.Organization.TrekManagerOrganization;
import Business.Trek.Trek;
import Business.Trek.TrekBooking;
import Business.Trek.TrekSlotAndCost;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AddTrekCostWorkRequest;
import Business.WorkQueue.ConfirmPaymentWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
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
public class CutomerTrekCompanyWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CutomerTrekCompanyWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private CustomerOrganization customerOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private TrekManagerOrganization trekManagerOrganization;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public CutomerTrekCompanyWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, CustomerOrganization customerOrganization, Enterprise enterprise, EcoSystem business) throws ParseException {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.customerOrganization = customerOrganization;
        this.enterprise = enterprise;
        this.business = business;
        for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof TrekManagerOrganization){
                trekManagerOrganization = (TrekManagerOrganization) org;
                break;
            }
        }
        System.out.println("trek manager organization: "+trekManagerOrganization);
        populateTrekLocationTable();
        populateMyTreks("All Treks");
        populateMyDeclinedTreksTable();
        populateMyPendingTreksTable();
    }
    
    public void populateMyTreks(String filter) throws ParseException{
        Date currentDate = dateFormat.parse(dateFormat.format(new Date()));
        DefaultTableModel model = (DefaultTableModel) myTrekTable.getModel();
        model.setRowCount(0);
        if(trekManagerOrganization != null){
        for(TrekBooking trekBooking: trekManagerOrganization.getTrekBookingDirectory().getTrekBookingList()){
            Date trekStartDate = dateFormat.parse(dateFormat.format(trekBooking.getTrekSlotAndCost().getTrekStartDate()));
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(trekStartDate);
            cal.add(Calendar.DATE,trekBooking.getTrek().getTrekDuration());
            Date trekEndDate = dateFormat.parse(dateFormat.format(cal.getTime()));
            
            //row[0] = request.getTrekName();
            if(trekBooking.getCustomer() == account){
                if(filter.equals("All Treks")){
                    Object[] row = new Object[8];
                    row[0] = trekBooking.getTrek();
                    row[1] = trekBooking.getTrek().getTrekLocation();
                    row[2] = trekBooking.getTotalTrekkers();
                    row[3] = dateFormat.format(trekBooking.getTrekSlotAndCost().getTrekStartDate());
                    row[4] = dateFormat.format(trekEndDate);
                    row[5] = trekBooking.getTrek().getTrekDuration();
                    row[6] = trekBooking.getTrekSlotAndCost().getTrekTotalCost();
                    row[7] = dateFormat.format(trekBooking.getBookingDate());
                    
                    model.addRow(row);  
                } else if(filter.equals("Upcoming Treks")){
                    if(trekStartDate.compareTo(currentDate) > 0){
                        Object[] row = new Object[8];
                        row[0] = trekBooking.getTrek();
                        row[1] = trekBooking.getTrek().getTrekLocation();
                        row[2] = trekBooking.getTotalTrekkers();
                        row[3] = dateFormat.format(trekBooking.getTrekSlotAndCost().getTrekStartDate());
                        row[4] = dateFormat.format(trekEndDate);
                        row[5] = trekBooking.getTrek().getTrekDuration();
                        row[6] = trekBooking.getTrekSlotAndCost().getTrekTotalCost();
                        row[7] = dateFormat.format(trekBooking.getBookingDate());
                        model.addRow(row);  
                    }
                } else if(filter.equals("Ongoing Treks")){
                    if(trekStartDate.compareTo(currentDate) <= 0 && trekEndDate.compareTo(currentDate) >=0 ){
                        Object[] row = new Object[8];
                        row[0] = trekBooking.getTrek();
                        row[1] = trekBooking.getTrek().getTrekLocation();
                        row[2] = trekBooking.getTotalTrekkers();
                        row[3] = dateFormat.format(trekBooking.getTrekSlotAndCost().getTrekStartDate());
                        row[4] = dateFormat.format(trekEndDate);
                        row[5] = trekBooking.getTrek().getTrekDuration();
                        row[6] = trekBooking.getTrekSlotAndCost().getTrekTotalCost();
                        row[7] = dateFormat.format(trekBooking.getBookingDate());
                        model.addRow(row);
                    }
                    
                } else if(filter.equals("Past Treks")){
                    if(trekEndDate.compareTo(currentDate) <= 0){
                        Object[] row = new Object[8];
                        row[0] = trekBooking.getTrek();
                        row[1] = trekBooking.getTrek().getTrekLocation();
                        row[2] = trekBooking.getTotalTrekkers();
                        row[3] = dateFormat.format(trekBooking.getTrekSlotAndCost().getTrekStartDate());
                        row[4] = dateFormat.format(trekEndDate);
                        row[5] = trekBooking.getTrek().getTrekDuration();
                        row[6] = trekBooking.getTrekSlotAndCost().getTrekTotalCost();
                        row[7] = dateFormat.format(trekBooking.getBookingDate());
                        model.addRow(row);
                    }
                }
            }
        }
    }
}
    
    public void populateTrekLocationTable(){
        DefaultTableModel model = (DefaultTableModel) trekLocationTable.getModel();
        model.setRowCount(0);
        ArrayList<String> temp = new ArrayList<String>();
        if(trekManagerOrganization != null){
        for (Trek trek : trekManagerOrganization.getTrekDirectory().getTrekArrayList()){
            if(temp.contains(trek.getTrekLocation())){
                continue;
            } else{
                 Object[] row = new Object[1];
                row[0] = trek.getTrekLocation();
                model.addRow(row); 
                temp.add(trek.getTrekLocation());
            }
            //row[0] = request.getTrekName();
             
        }
    }
}
    
    public void populateMyPendingTreksTable() throws ParseException{
        DefaultTableModel model = (DefaultTableModel) myPendingTreks.getModel();
        model.setRowCount(0);
        System.out.println("inside pending request");
        for(WorkRequest wq: account.getWorkQueue().getWorkRequestList()){
            if(wq instanceof ConfirmPaymentWorkRequest){
                System.out.println("got the work request");
                ConfirmPaymentWorkRequest req = (ConfirmPaymentWorkRequest) wq;
                if(req.getStatus().equals("Payment pending for confirmation")){
                    System.out.println("found pending one");
                Object[] row = new Object[9];
                row[0] = req.getTrekBooking().getTrek();
                row[1] = req.getTrekBooking().getTrek().getTrekLocation();
                row[2] = req.getTrekBooking().getTotalTrekkers();
                row[3] = dateFormat.format(req.getTrekBooking().getTrekSlotAndCost().getTrekStartDate());
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(req.getTrekBooking().getTrekSlotAndCost().getTrekStartDate());
                cal.add(Calendar.DATE,req.getTrekBooking().getTrek().getTrekDuration());
                Date trekEndDate = dateFormat.parse(dateFormat.format(cal.getTime()));
                row[4] = dateFormat.format(trekEndDate);
                row[5] = req.getTrekBooking().getTrek().getTrekDuration();
                row[6] = req.getTrekBooking().getTrekSlotAndCost().getTrekTotalCost();
                row[7] = req.getComment();
                row[8] = dateFormat.format(req.getTrekBooking().getBookingDate());
                model.addRow(row);
                }
            }
        }
    }
    
    public void populateMyDeclinedTreksTable() throws ParseException{
        DefaultTableModel model = (DefaultTableModel) myDeclinedTreks.getModel();
        model.setRowCount(0);
        
        for(WorkRequest wq: account.getWorkQueue().getWorkRequestList()){
            if(wq instanceof ConfirmPaymentWorkRequest){
                ConfirmPaymentWorkRequest req = (ConfirmPaymentWorkRequest) wq;
                if(req.getStatus().equals("Payment Declined")){
                Object[] row = new Object[9];
                row[0] = req.getTrekBooking().getTrek();
                row[1] = req.getTrekBooking().getTrek().getTrekLocation();
                row[2] = req.getTrekBooking().getTotalTrekkers();
                row[3] = dateFormat.format(req.getTrekBooking().getTrekSlotAndCost().getTrekStartDate());
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(req.getTrekBooking().getTrekSlotAndCost().getTrekStartDate());
                cal.add(Calendar.DATE,req.getTrekBooking().getTrek().getTrekDuration());
                Date trekEndDate = dateFormat.parse(dateFormat.format(cal.getTime()));
                row[4] = dateFormat.format(trekEndDate);
                row[5] = req.getTrekBooking().getTrek().getTrekDuration();
                row[6] = req.getTrekBooking().getTrekSlotAndCost().getTrekTotalCost();
                row[7] = req.getComment();
                row[8] = dateFormat.format(req.getTrekBooking().getBookingDate());
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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        trekLocationTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        myTrekTable = new javax.swing.JTable();
        trekFilterComboBox = new javax.swing.JComboBox();
        backButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        myDeclinedTreks = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        myPendingTreks = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(33, 108, 205));

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 255));
        jTabbedPane1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(33, 108, 205));

        trekLocationTable.setFont(new java.awt.Font("Comic Sans MS", 0, 25)); // NOI18N
        trekLocationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Locations"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        trekLocationTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        trekLocationTable.setRowHeight(50);
        trekLocationTable.setShowVerticalLines(false);
        trekLocationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trekLocationTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(trekLocationTable);
        if (trekLocationTable.getColumnModel().getColumnCount() > 0) {
            trekLocationTable.getColumnModel().getColumn(0).setResizable(false);
        }

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/CustomerRole/backButton.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jButton1)))
                .addContainerGap(1165, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton1)
                .addContainerGap(488, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Explore", jPanel5);

        jPanel3.setBackground(new java.awt.Color(33, 108, 205));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("My Confirmed Treks");

        myTrekTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        myTrekTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trek Name", "Trek Location", "Total No. of Trekkers", "Trek Start Date", "Trek End Date", "Trek Duration", "Total Cost/person", "Booking Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        myTrekTable.setRowHeight(22);
        jScrollPane2.setViewportView(myTrekTable);
        if (myTrekTable.getColumnModel().getColumnCount() > 0) {
            myTrekTable.getColumnModel().getColumn(0).setResizable(false);
            myTrekTable.getColumnModel().getColumn(1).setResizable(false);
            myTrekTable.getColumnModel().getColumn(2).setResizable(false);
            myTrekTable.getColumnModel().getColumn(3).setResizable(false);
            myTrekTable.getColumnModel().getColumn(4).setResizable(false);
            myTrekTable.getColumnModel().getColumn(5).setResizable(false);
            myTrekTable.getColumnModel().getColumn(6).setResizable(false);
            myTrekTable.getColumnModel().getColumn(7).setResizable(false);
        }

        trekFilterComboBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        trekFilterComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All Treks", "Upcoming Treks", "Ongoing Treks", "Past Treks" }));
        trekFilterComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                trekFilterComboBoxItemStateChanged(evt);
            }
        });

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/CustomerRole/backButton.png"))); // NOI18N
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        myDeclinedTreks.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        myDeclinedTreks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trek Name", "Trek Location", "Total No. of Trekkers", "Trek Start Date", "Trek End Date", "Trek Duration", "Total Cost/person", "Payment Decline Reason", "Booking Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(myDeclinedTreks);
        if (myDeclinedTreks.getColumnModel().getColumnCount() > 0) {
            myDeclinedTreks.getColumnModel().getColumn(0).setResizable(false);
            myDeclinedTreks.getColumnModel().getColumn(1).setResizable(false);
            myDeclinedTreks.getColumnModel().getColumn(2).setResizable(false);
            myDeclinedTreks.getColumnModel().getColumn(3).setResizable(false);
            myDeclinedTreks.getColumnModel().getColumn(4).setResizable(false);
            myDeclinedTreks.getColumnModel().getColumn(5).setResizable(false);
            myDeclinedTreks.getColumnModel().getColumn(6).setResizable(false);
            myDeclinedTreks.getColumnModel().getColumn(7).setResizable(false);
            myDeclinedTreks.getColumnModel().getColumn(8).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("My Declined Treks");

        myPendingTreks.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        myPendingTreks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trek Name", "Trek Location", "Total No. of Trekkers", "Trek Start Date", "Trek End Date", "Trek Duration", "Total Cost/person", "Payment Decline Reason", "Booking Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(myPendingTreks);
        if (myPendingTreks.getColumnModel().getColumnCount() > 0) {
            myPendingTreks.getColumnModel().getColumn(0).setResizable(false);
            myPendingTreks.getColumnModel().getColumn(1).setResizable(false);
            myPendingTreks.getColumnModel().getColumn(2).setResizable(false);
            myPendingTreks.getColumnModel().getColumn(3).setResizable(false);
            myPendingTreks.getColumnModel().getColumn(4).setResizable(false);
            myPendingTreks.getColumnModel().getColumn(5).setResizable(false);
            myPendingTreks.getColumnModel().getColumn(6).setResizable(false);
            myPendingTreks.getColumnModel().getColumn(7).setResizable(false);
            myPendingTreks.getColumnModel().getColumn(8).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("My Pending Confirmation Treks");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(654, 654, 654)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(trekFilterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(792, 792, 792)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(780, 780, 780)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1600, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(426, 426, 426))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trekFilterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(backButton)
                .addContainerGap())
        );

        jTabbedPane1.addTab("My Treks", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void trekLocationTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trekLocationTableMouseClicked
        // TODO add your handling code here:
        String selectedTrekLocation = (String) trekLocationTable.getValueAt(trekLocationTable.getSelectedRow(), 0);
        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
        userProcessContainer.add("TrekCatalogJPanel",new TrekCatalogJPanel(userProcessContainer, account, customerOrganization, enterprise, business, selectedTrekLocation));
        layout.next(userProcessContainer);

    }//GEN-LAST:event_trekLocationTableMouseClicked

    private void trekFilterComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_trekFilterComboBoxItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            try{
            if(evt.getItem() == "Upcoming Treks"){
                populateMyTreks("Upcoming Treks");
            } else if(evt.getItem() == "Ongoing Treks"){
                populateMyTreks("Ongoing Treks");
            } else if(evt.getItem() == "Past Treks"){
                populateMyTreks("Past Treks");
            } else if(evt.getItem() == "All Treks"){
                populateMyTreks("All Treks");
            }
        } catch (ParseException ex) {
                    Logger.getLogger(CutomerTrekCompanyWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }//GEN-LAST:event_trekFilterComboBoxItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable myDeclinedTreks;
    private javax.swing.JTable myPendingTreks;
    private javax.swing.JTable myTrekTable;
    private javax.swing.JComboBox trekFilterComboBox;
    private javax.swing.JTable trekLocationTable;
    // End of variables declaration//GEN-END:variables
}
