/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Business.Abstract.User;
import Business.Airliner.AirlinerDirectory;
import Business.ConfigurationFile;
import Business.Flight.FlightDirectory;
import Business.FlightBooking.BookingDirectory;
import Business.User.Customer;
import Business.User.CustomerDirectory;
import UserInterface.BookFlights.SearchFlightJPanel;
import UserInterface.BookFlights.ViewBookingHistoryJPanel;

import UserInterface.ManageFlight.ManageAirlinerFlightScheduleJPanel;

import UserInterface.ManageAirliner.ManageAirlinerJPanel;

import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shahd
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    private static User userName = null;
    CustomerDirectory customerDirectory = new CustomerDirectory();
    AirlinerDirectory airlinerDir = new AirlinerDirectory();
    FlightDirectory flightDir = new FlightDirectory();
    BookingDirectory bookingDir = new BookingDirectory();
    
    public MainJFrame() {
        try {   
            ConfigurationFile conf = new ConfigurationFile(customerDirectory, airlinerDir, flightDir);
        } catch (ParseException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        btnManageAirliner.setName("btnManageAirliner");
        btnManageFlights.setName("btnManageFlights");
        btnBookFlight.setName("btnBookFlight");
        btnViewBookingHistory.setName("btnViewBookingHistory");
        btnManageAirliner.setVisible(false);
        btnManageFlights.setVisible(false);
        btnBookFlight.setVisible(false);
        btnViewBookingHistory.setVisible(false);
        
        CardLayout layout = (CardLayout)jPanelRight.getLayout();
        jPanelRight.add(new LoginScreen(jPanelRight, jPanelLeft, customerDirectory));
        layout.next(jPanelRight);
    }

    public static User getUserName() {
        return userName;
    }

    public static void setUserName(User userName) {
        MainJFrame.userName = userName;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        jPanelLeft = new javax.swing.JPanel();
        btnBookFlight = new javax.swing.JButton();
        btnManageFlights = new javax.swing.JButton();
        btnManageAirliner = new javax.swing.JButton();
        btnViewBookingHistory = new javax.swing.JButton();
        jPanelRight = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelLeft.setMinimumSize(new java.awt.Dimension(175, 169));
        jPanelLeft.setPreferredSize(new java.awt.Dimension(500, 598));
        jPanelLeft.setRequestFocusEnabled(false);

        btnBookFlight.setText("Book Flight");
        btnBookFlight.setMaximumSize(new java.awt.Dimension(143, 29));
        btnBookFlight.setMinimumSize(new java.awt.Dimension(143, 29));
        btnBookFlight.setPreferredSize(new java.awt.Dimension(143, 29));
        btnBookFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookFlightActionPerformed(evt);
            }
        });

        btnManageFlights.setText("Manage Flights");
        btnManageFlights.setMaximumSize(new java.awt.Dimension(143, 29));
        btnManageFlights.setMinimumSize(new java.awt.Dimension(143, 29));
        btnManageFlights.setPreferredSize(new java.awt.Dimension(143, 29));
        btnManageFlights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageFlightsActionPerformed(evt);
            }
        });

        btnManageAirliner.setText("Manage Airliner");
        btnManageAirliner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageAirlinerActionPerformed(evt);
            }
        });

        btnViewBookingHistory.setText("View Booking History");
        btnViewBookingHistory.setMaximumSize(new java.awt.Dimension(143, 29));
        btnViewBookingHistory.setMinimumSize(new java.awt.Dimension(143, 29));
        btnViewBookingHistory.setPreferredSize(new java.awt.Dimension(143, 29));
        btnViewBookingHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewBookingHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLeftLayout = new javax.swing.GroupLayout(jPanelLeft);
        jPanelLeft.setLayout(jPanelLeftLayout);
        jPanelLeftLayout.setHorizontalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLeftLayout.createSequentialGroup()
                        .addGroup(jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnViewBookingHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(btnBookFlight, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                        .addContainerGap(303, Short.MAX_VALUE))
                    .addGroup(jPanelLeftLayout.createSequentialGroup()
                        .addGroup(jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnManageAirliner, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(btnManageFlights, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                        .addGap(0, 303, Short.MAX_VALUE))))
        );
        jPanelLeftLayout.setVerticalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeftLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnManageAirliner, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnManageFlights, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btnBookFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnViewBookingHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(408, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(jPanelLeft);

        jPanelRight.setLayout(new java.awt.CardLayout());
        jSplitPane2.setRightComponent(jPanelRight);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnManageFlightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageFlightsActionPerformed
        // TODO add your handling code here:
        ManageAirlinerFlightScheduleJPanel panel = new ManageAirlinerFlightScheduleJPanel(jPanelRight,jPanelLeft,flightDir,airlinerDir);
        jPanelRight.add("ManageAirlinerFlightScheduleJPanel",panel);
        CardLayout layout = (CardLayout)jPanelRight.getLayout();
        layout.next(jPanelRight);
        
        
    }//GEN-LAST:event_btnManageFlightsActionPerformed

    private void btnManageAirlinerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageAirlinerActionPerformed
        // TODO add your handling code here:
            CardLayout layout = (CardLayout)jPanelRight.getLayout();
            jPanelRight.add(new ManageAirlinerJPanel(jPanelRight,jPanelLeft,airlinerDir,flightDir));
            layout.next(jPanelRight);
    }//GEN-LAST:event_btnManageAirlinerActionPerformed

    private void btnBookFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookFlightActionPerformed
        // TODO add your handling code here:
        SearchFlightJPanel panel = new SearchFlightJPanel(jPanelRight,jPanelLeft,flightDir,airlinerDir,userName,bookingDir);
        jPanelRight.add("SearchFlightJPanel",panel);
        CardLayout layout = (CardLayout)jPanelRight.getLayout();
        layout.next(jPanelRight);
    }//GEN-LAST:event_btnBookFlightActionPerformed

    private void btnViewBookingHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewBookingHistoryActionPerformed
        // TODO add your handling code here
        
        if(userName == null || userName.equals("")){
            ViewBookingHistoryJPanel panel = new ViewBookingHistoryJPanel(jPanelRight,jPanelLeft, bookingDir);
            jPanelRight.add("ViewBookingJPanel",panel);
        }else{
            ViewBookingHistoryJPanel panel = new ViewBookingHistoryJPanel(jPanelRight,jPanelLeft, bookingDir,userName);
            jPanelRight.add("ViewBookingJPanel",panel);
        }
        CardLayout layout = (CardLayout) jPanelRight.getLayout();
        layout.next(jPanelRight);
    }//GEN-LAST:event_btnViewBookingHistoryActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBookFlight;
    private javax.swing.JButton btnManageAirliner;
    private javax.swing.JButton btnManageFlights;
    private javax.swing.JButton btnViewBookingHistory;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JSplitPane jSplitPane2;
    // End of variables declaration//GEN-END:variables
}
