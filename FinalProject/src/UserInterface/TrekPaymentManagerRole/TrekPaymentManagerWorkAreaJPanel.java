/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.TrekPaymentManagerRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.TrekManagerOrganization;
import Business.Organization.TrekPaymentOrganization;
import Business.Trek.Trek;
import Business.Trek.TrekSlotAndCost;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.ConfirmPaymentWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import UserInterface.TrekManagerRole.TrekManagerWorkAreaJPanel;

/**
 *
 * @author dedhi
 */
public class TrekPaymentManagerWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form TrekPaymentManagerWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private TrekPaymentOrganization trekPaymentOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private Session mailSession;
    private DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public TrekPaymentManagerWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, TrekPaymentOrganization organization, Enterprise enterprise, EcoSystem business) throws ParseException {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.trekPaymentOrganization = trekPaymentOrganization;
        this.enterprise = enterprise;
        this.business = business;
        populateAssignToMeTable();
        populateTaskCompletedTable();
    }

    public void populateAssignToMeTable() throws ParseException{
        DefaultTableModel model = (DefaultTableModel) assignTaskTable.getModel();
        model.setRowCount(0);
        
        for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof TrekPaymentOrganization){
                trekPaymentOrganization = (TrekPaymentOrganization) org;
                break;
            }
        }
        for(WorkRequest request: trekPaymentOrganization.getWorkQueue().getWorkRequestList()){
            ConfirmPaymentWorkRequest req = (ConfirmPaymentWorkRequest) request;
            if(req.getReceiver() == null && req.getStatus().equals("Payment pending for confirmation")){
                Object[] row = new Object[11];
                row[0] = req.getTrekBooking();
                row[1] = req.getTrekBooking().getTrek().getTrekLocation();
                row[2] = dateTimeFormat.format(req.getTrekBooking().getTrekSlotAndCost().getTrekStartDate());
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(req.getTrekBooking().getTrekSlotAndCost().getTrekStartDate());
                cal.add(Calendar.DATE,req.getTrekBooking().getTrek().getTrekDuration());
                Date trekEndDate = dateTimeFormat.parse(dateTimeFormat.format(cal.getTime()));
                row[3] = dateTimeFormat.format(trekEndDate);
                float totalCost = req.getTrekBooking().getTrekSlotAndCost().getTrekTotalCost()*req.getTrekBooking().getTotalTrekkers();
                row[4] = totalCost;
                row[5] = req.getCardHolderName();
                row[6] = req.getCardNumber();
                row[7] = req.getExpiryDate();
                row[8] = req.getCvv();
                row[9] = req;
                row[10] = dateTimeFormat.format(req.getTrekBooking().getBookingDate());
                model.addRow(row);
            }
        }
    }
    
    public void populateTaskCompletedTable() throws ParseException{
        DefaultTableModel model = (DefaultTableModel) taskCompletedTable.getModel();
        model.setRowCount(0);
        
//        for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
//            if(org instanceof TrekPaymentOrganization){
//                trekPaymentOrganization = (TrekPaymentOrganization) org;
//                break;
//            }
//        }
        for(WorkRequest request: account.getWorkQueue().getWorkRequestList()){
            ConfirmPaymentWorkRequest req = (ConfirmPaymentWorkRequest) request;
            Object[] row = new Object[12];
            row[0] = req.getSender();
            row[1] = req.getReceiver();
            row[2] = req.getTrekBooking().getTrek();
            row[3] = dateTimeFormat.format(req.getTrekBooking().getTrekSlotAndCost().getTrekStartDate());
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(req.getTrekBooking().getTrekSlotAndCost().getTrekStartDate());
            cal.add(Calendar.DATE,req.getTrekBooking().getTrek().getTrekDuration());
            Date trekEndDate = dateTimeFormat.parse(dateTimeFormat.format(cal.getTime()));
            row[4] = dateTimeFormat.format(trekEndDate);
            float totalCost = req.getTrekBooking().getTrekSlotAndCost().getTrekTotalCost()*req.getTrekBooking().getTotalTrekkers();
            row[5] = totalCost;
            row[6] = req.getCardHolderName();
            row[7] = req.getCardNumber();
            row[8] = req.getExpiryDate();
            row[9] = req.getCvv();
            row[10] = req.getStatus();
            model.addRow(row);
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
        assignTaskTable = new javax.swing.JTable();
        confirmPaymentButton = new javax.swing.JButton();
        declinePayment = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taskCompletedTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 255));
        jTabbedPane1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(33, 108, 205));

        assignTaskTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        assignTaskTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trek Name", "Trek Location", "Trek Start Date", "Trek End Date", "Trek Total Cost", "Credit Card Holder", "Credit Card Number", "Expiry Date", "CVV", "Payment Status", "Booking Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        assignTaskTable.setRowHeight(22);
        jScrollPane1.setViewportView(assignTaskTable);
        if (assignTaskTable.getColumnModel().getColumnCount() > 0) {
            assignTaskTable.getColumnModel().getColumn(0).setResizable(false);
            assignTaskTable.getColumnModel().getColumn(1).setResizable(false);
            assignTaskTable.getColumnModel().getColumn(2).setResizable(false);
            assignTaskTable.getColumnModel().getColumn(3).setResizable(false);
            assignTaskTable.getColumnModel().getColumn(4).setResizable(false);
            assignTaskTable.getColumnModel().getColumn(5).setResizable(false);
            assignTaskTable.getColumnModel().getColumn(6).setResizable(false);
            assignTaskTable.getColumnModel().getColumn(7).setResizable(false);
            assignTaskTable.getColumnModel().getColumn(8).setResizable(false);
            assignTaskTable.getColumnModel().getColumn(9).setResizable(false);
            assignTaskTable.getColumnModel().getColumn(10).setResizable(false);
        }

        confirmPaymentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekPaymentManagerRole/confirmPaymentButton.png"))); // NOI18N
        confirmPaymentButton.setContentAreaFilled(false);
        confirmPaymentButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        confirmPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPaymentButtonActionPerformed(evt);
            }
        });

        declinePayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekPaymentManagerRole/declinePaymentButton.png"))); // NOI18N
        declinePayment.setContentAreaFilled(false);
        declinePayment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        declinePayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declinePaymentActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pick a Task");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(confirmPaymentButton)
                        .addGap(125, 125, 125)
                        .addComponent(declinePayment))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(515, 515, 515)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(declinePayment)
                    .addComponent(confirmPaymentButton))
                .addGap(45, 45, 45))
        );

        jTabbedPane1.addTab("Pick a Task", jPanel1);

        jPanel2.setBackground(new java.awt.Color(33, 108, 205));

        taskCompletedTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        taskCompletedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request Sender", "Request Receiver", "Trek Name", "Trek Start Date", "Trek End Date", "Trek Total Cost", "Credit Card Holder", "Credit Card Number", "Expiry Date", "CVV", "Request Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(taskCompletedTable);
        if (taskCompletedTable.getColumnModel().getColumnCount() > 0) {
            taskCompletedTable.getColumnModel().getColumn(0).setResizable(false);
            taskCompletedTable.getColumnModel().getColumn(1).setResizable(false);
            taskCompletedTable.getColumnModel().getColumn(2).setResizable(false);
            taskCompletedTable.getColumnModel().getColumn(3).setResizable(false);
            taskCompletedTable.getColumnModel().getColumn(4).setResizable(false);
            taskCompletedTable.getColumnModel().getColumn(5).setResizable(false);
            taskCompletedTable.getColumnModel().getColumn(6).setResizable(false);
            taskCompletedTable.getColumnModel().getColumn(7).setResizable(false);
            taskCompletedTable.getColumnModel().getColumn(8).setResizable(false);
            taskCompletedTable.getColumnModel().getColumn(9).setResizable(false);
            taskCompletedTable.getColumnModel().getColumn(10).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("My Work Requests");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(464, 464, 464)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1465, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("My Work Requests", jPanel2);

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

    private void confirmPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPaymentButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = assignTaskTable.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a payment for confirmation","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        ConfirmPaymentWorkRequest request = (ConfirmPaymentWorkRequest) assignTaskTable.getValueAt(selectedRow, 9);
        request.setReceiver(account);
        account.getWorkQueue().getWorkRequestList().add(request);
        request.setStatus("Payment Confirmed");
        for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
            if(org instanceof TrekManagerOrganization){
                TrekManagerOrganization trekManagerOrganization = (TrekManagerOrganization) org;
                trekManagerOrganization.getTrekBookingDirectory().getTrekBookingList().add(request.getTrekBooking());
                break;
            }
        }
        
        try {
            populateAssignToMeTable();
            populateTaskCompletedTable();
        } catch (ParseException ex) {
            Logger.getLogger(TrekPaymentManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(null, "Payment confirmed");
        try {
            System.out.println("SimpleEmail Start"); 
            setMailServerProperties();
            draftEmailMessageConfirm(request.getTrekBooking().getTrek());
              sendEmailConfirm(request.getTrekBooking().getTrek());
        } catch (MessagingException ex) {
            Logger.getLogger(TrekManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_confirmPaymentButtonActionPerformed

    private void setMailServerProperties()
    {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getDefaultInstance(emailProperties, null);
    }
 
    private MimeMessage draftEmailMessageConfirm(Trek trek) throws AddressException, MessagingException
    {
        try{
        String[] toEmails = { account.getUsername() };
        String emailSubject = "Trek Payment Received";
        String emailBody = "This is to notify that your Trek "+trek.getTrekName()+" is confimed and payment is received<br><br>Regards,<br>"+enterprise.getName();
        MimeMessage emailMessage = new MimeMessage(mailSession);
        /**
         * Set the mail recipients
         * */
        for (int i = 0; i < toEmails.length; i++)
        {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }
        emailMessage.setSubject(emailSubject);
        /**
         * If sending HTML mail
         * */
        emailMessage.setContent(emailBody, "text/html");
        /**
         * If sending only text mail
         * */
        //emailMessage.setText(emailBody);// for a text email
        return emailMessage;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Email address is not correct","Warning",JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
    
    private MimeMessage draftEmailMessageDecline(String reason, Trek trek) throws AddressException, MessagingException
    {
        try{
        String[] toEmails = { account.getUsername() };
        String emailSubject = "Trek Payment Declined";
        String emailBody = "This is to notify that your payment for trek "+trek.getTrekName()+" is declined because of following reason:<br>"+reason+"<br><br>Please contact the company on (857)-800-23368 for further details.<br><br>Regards,<br>"+enterprise.getName();
        MimeMessage emailMessage = new MimeMessage(mailSession);
        /**
         * Set the mail recipients
         * */
        for (int i = 0; i < toEmails.length; i++)
        {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }
        emailMessage.setSubject(emailSubject);
        /**
         * If sending HTML mail
         * */
        emailMessage.setContent(emailBody, "text/html");
        /**
         * If sending only text mail
         * */
        //emailMessage.setText(emailBody);// for a text email
        return emailMessage;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Email address is not correct","Warning",JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
 
    private void sendEmailConfirm(Trek trek)throws AddressException, MessagingException
    {
        /**
         * Sender's credentials
         * */
        String fromUser = "dedhiadimpi@gmail.com";
        String fromUserEmailPassword = "dimpi@95";
 
        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        /**
         * Draft the message
         * */
        MimeMessage emailMessage = draftEmailMessageConfirm(trek);
        /**
         * Send the mail
         * */
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }
    
    private void sendEmailDecline(String reason, Trek trek) throws AddressException, MessagingException
    {
        /**
         * Sender's credentials
         * */
        String fromUser = "dedhiadimpi@gmail.com";
        String fromUserEmailPassword = "dimpi@95";
 
        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        /**
         * Draft the message
         * */
        MimeMessage emailMessage = draftEmailMessageDecline(reason, trek);
        /**
         * Send the mail
         * */
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }
    
    private void declinePaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declinePaymentActionPerformed
        // TODO add your handling code here:
        int selectedRow = assignTaskTable.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a payment to decline","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        String reason = JOptionPane.showInputDialog("Please enter a reason for declining the payment");
        if(reason.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter a valid reason");
            return;
        }
        ConfirmPaymentWorkRequest request = (ConfirmPaymentWorkRequest) assignTaskTable.getValueAt(selectedRow, 9);
        request.setReceiver(account);
        request.setStatus("Payment Declined");
        request.setComment(reason);
        int trekAvailableSeats = request.getTrekBooking().getTrekSlotAndCost().getAvailableSeats();
        int totalTrekkers = request.getTrekBooking().getTotalTrekkers();
        request.getTrekBooking().getTrekSlotAndCost().setAvailableSeats(trekAvailableSeats+totalTrekkers);
        account.getWorkQueue().getWorkRequestList().add(request);
        try {
            populateAssignToMeTable();
            populateTaskCompletedTable();
        } catch (ParseException ex) {
            Logger.getLogger(TrekPaymentManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(null, "Payment Declined");
        try {
            System.out.println("SimpleEmail Start"); 
            setMailServerProperties();
            draftEmailMessageDecline(reason,request.getTrekBooking().getTrek());
            sendEmailDecline(reason, request.getTrekBooking().getTrek());
        } catch (MessagingException ex) {
            Logger.getLogger(TrekManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_declinePaymentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable assignTaskTable;
    private javax.swing.JButton confirmPaymentButton;
    private javax.swing.JButton declinePayment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable taskCompletedTable;
    // End of variables declaration//GEN-END:variables
}
