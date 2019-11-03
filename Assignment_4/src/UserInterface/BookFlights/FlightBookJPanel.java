/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.BookFlights;

import Business.Abstract.User;
import Business.Airliner.Airliner;
import Business.Flight.Flight;
import Business.Flight.FlightDirectory;
import Business.FlightBooking.Booking;
import Business.FlightBooking.BookingDirectory;
import Business.FlightBooking.Passenger;
import Business.FlightBooking.PassengerDirectory;
import Business.User.Customer;
import UserInterface.LoginScreen;
import UserInterface.MainJFrame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javafx.scene.input.KeyCode.K;
import static javafx.scene.input.KeyCode.V;
import javafx.scene.layout.Border;
import javafx.util.Pair;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author shahd
 */
public class FlightBookJPanel extends javax.swing.JPanel {

    /**
     * Creates new form FlightDetailsJPanel
     */
    private int locationPanel = 45;
    private int numberOfSeats = 0;
    private FlightDirectory flightDir;
    private Vector<String> header;
    private Vector<Boolean> data;
    private User userName;
    private JPanel rightPanel;
    private Flight flight;
    private float totalCost = 0;
    private ArrayList<ArrayList<JTextField>> arrayPanel;
    private ArrayList<Pair<Integer,Integer>> set_of_pairs = new ArrayList<Pair<Integer,Integer>>();
    private BookingDirectory bookingDir;
    private JPanel jPanelLeft;
    private DataModel dataModel;
    public FlightBookJPanel(JPanel rightPanel, JPanel jPanelLeft, User userName, Flight flight, BookingDirectory bookingDir) {
        this.rightPanel = rightPanel;
        this.userName = userName;
        this.flight = flight;
        this.bookingDir = bookingDir;
        this.jPanelLeft = jPanelLeft;
        arrayPanel = new ArrayList<ArrayList<JTextField>>();
        initComponents();
       // lblTotalCost.setText("Total Cost: "+totalCost);
        Table();
        
//         MyCellRenderer render = new MyCellRenderer();
//       tblSeats.setDefaultRenderer(Boolean.class,render);
      
        lblUserName.setText(userName.getUserName());
//        TableCellRenderer tcb = tblSeats.getCellRenderer(0, 0);
//        tblSeats.isCellEditable(0,0);
        //tblSeats.repaint();

        //addTextFields();
        
//        tblSeats.addMouseListener(new java.awt.event.MouseAdapter(){
//            public void mouseClicked(java.awt.event.MouseEvent e){
//                int row=tblSeats.rowAtPoint(e.getPoint());
//                int col= tblSeats.columnAtPoint(e.getPoint());
//                
//                if(tblSeats.isCellEditable(row, col)){
//                    System.out.println("cell editatble");
//                        if((boolean)tblSeats.getValueAt(row, col)){
//                            if(numberOfSeats < 5){
//                            numberOfSeats++;
//                            addTextFields();
//                            jPanelPassengerDetails.repaint();
//                    } else{
//                        JOptionPane.showMessageDialog(null, "You cannot book more than 5 seats","Warning",JOptionPane.WARNING_MESSAGE);
//                        tblSeats.setValueAt(false, row, col);
//                    }
//
//                } else{
//                    numberOfSeats--;
//                    deleteTextFields();
//                    jPanelPassengerDetails.repaint();
//                }
//                
//                }else{
//                
//                    System.out.println("Error");
//                
//                }
//                if((boolean)tblSeats.getValueAt(row, col)){
//                    if(numberOfSeats < 5){
//                        numberOfSeats++;
//                        addTextFields();
//                        jPanelPassengerDetails.repaint();
//                    } else{
//                        JOptionPane.showMessageDialog(null, "You cannot book more than 5 seats","Warning",JOptionPane.WARNING_MESSAGE);
//                        tblSeats.setValueAt(false, row, col);
//                    }
//
//                } else{
//                    numberOfSeats--;
//                    deleteTextFields();
//                    jPanelPassengerDetails.repaint();
//                }
//                JTextField tb = new JTextField();
//                jPanelPassengerDetails.add(new JTextField());
                
            //}
       // });
        
       
        DefaultTableModel model = (DefaultTableModel)tblSeats.getModel();
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel) e.getSource();
                String columnName = model.getColumnName(column);
                
                Boolean checked = (Boolean) model.getValueAt(row, column);
                if (checked) {
                    System.out.println(columnName + ": " + true);
                    
                    if(numberOfSeats < 5){
                        numberOfSeats++;
                        Pair<Integer,Integer> pair = new Pair<Integer,Integer>(row,column);
                        set_of_pairs.add(pair);
                        addTextFields();
                        jPanelPassengerDetails.repaint();
                        totalCost = flight.getFlightCost()*numberOfSeats;
                        //lblTotalCost.setText("Total Cost: "+totalCost);
                    } else{
                        JOptionPane.showMessageDialog(null, "You cannot book more than 5 seats","Warning",JOptionPane.WARNING_MESSAGE);
                        tblSeats.setValueAt(false, row, column);
                    }
                    
                } 
                else {
                    System.out.println(columnName + ": " + false);
                }
                
               
            }
            
            
        
        });
        
       
         
//        tblSeats.addMouseListener(new java.awt.event.MouseAdapter(){
//               
//                public void mouseClicked(java.awt.event.MouseEvent e){
//          
//                   
//                int row=tblSeats.rowAtPoint(e.getPoint());
//                int col= tblSeats.columnAtPoint(e.getPoint());
//                
//                int row1,col1;
//        for(Pair<Integer,Integer> pair : set_of_pairs){
//                row1=pair.getKey();
//                col1=pair.getValue();
//                if(row1==row&&col1==col){
//                     System.out.println("insideMouse");
//                tblSeats.setValueAt(false,row1,col1);
//                deleteTextFields();
//                jPanelPassengerDetails.repaint();
//                numberOfSeats--;
//                }
//                
//        }
//        
////       
////                String columnName = model.getColumnName(col);
////                System.out.println(tblSeats.getCellSelectionEnabled());
////                if((tblSeats.isCellSelected(row, col))&&columnName.equalsIgnoreCase("Middle")){
////                   System.out.println("InsideIF");
////                   tblSeats.setCellSelectionEnabled(true);
////                  // tblSeats.setValueAt(false, row, col);
////                
////                }
//                
//                }
//        });
        
        
       
        
        
        
        
        
    }
    
    

   
   public void Table()
   {
    
        data = flight.getSeats();
        
       // tblSeats.setModel(dataModel);
        //DefaultTableModel model = (DefaultTableModel)tblSeats.getModel();
        //model.addColumn("window", data);
             header = new Vector<String>();
    header.add("Window"); 
    header.add("Middle");
    header.add("Aisle");
    header.add("Aisle");
    header.add("Middle");
    header.add("Window");
    
    dataModel=new DataModel(data,header);
    
    
        
        
    dataModel.setDataVector(data,header);
     tblSeats.setModel(dataModel);
        //model.isCellEditable(, WIDTH)
        tblSeats.setOpaque(true);
        
//        header = new Vector<String>();
//    header.add("Column1"); 
//    header.add("Column2");
//    header.add("Column2");
//    header.add("Column2");
//    header.add("Column2");
//    header.add("Column2");
//    model=new DefaultTableModel(data,header);
//    tblSeats = new JTable(model);
    


        
   // jScrollPane1.setViewportView(tblSeats);
     
     
    }
    
    public void addTextFields(){
        JPanel panel = new JPanel();
        //panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLocation(0,locationPanel);
        panel.setSize(600,35);
        jPanelPassengerDetails.add(panel);
        JTextField txtFieldName = new JTextField();
        JTextField txtFieldPassport = new JTextField();
        JTextField txtFieldAge = new JTextField();
        txtFieldName.setSize(200, 30);
        txtFieldName.setLocation(0,0);
        txtFieldPassport.setSize(200,30);
        txtFieldPassport.setLocation(215,0);
        txtFieldAge.setSize(50, 30);
        txtFieldAge.setLocation(430, 0);
        panel.add(txtFieldName);
        panel.add(txtFieldPassport);
        panel.add(txtFieldAge);
        locationPanel = locationPanel + 40;
        ArrayList<JTextField> textFieldArr = new ArrayList<JTextField>();
        textFieldArr.add(txtFieldName);
        textFieldArr.add(txtFieldPassport);
        textFieldArr.add(txtFieldAge);
        arrayPanel.add(textFieldArr);
    }
    
    public void deleteTextFields(){
        Point p = new Point(0,locationPanel - 40);
        for(Component comp: jPanelPassengerDetails.getComponents()){
            if(comp instanceof JPanel){
                JPanel panel = (JPanel) comp;
                if(panel.getLocation().equals(p)){
                    jPanelPassengerDetails.remove(panel);
                }
            }
        }
        locationPanel = locationPanel - 40;
        arrayPanel.remove(arrayPanel.size()-1);
    }
    
    private boolean onlyLettersValidation(String input){
        Pattern p = Pattern.compile("^[a-zA-Z]+$");
        Matcher m = p.matcher(input);
        boolean b = m.matches();
        return b;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSeats = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanelPassengerDetails = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnConfirmBooking = new javax.swing.JButton();
        lblUserName = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnclearSeat = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        tblSeats.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(tblSeats.getValueAt(rowIndex, columnIndex).equals(true)){
                    return false;
                }
                else{
                    return true;
                }
            }
        });
        tblSeats.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblSeats);
        tblSeats.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Please select Seats of your choice");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Book Flight");

        jLabel3.setText("Passenger Name as per Passport");

        jLabel4.setText("Passport Number");

        jLabel5.setText("Age");

        javax.swing.GroupLayout jPanelPassengerDetailsLayout = new javax.swing.GroupLayout(jPanelPassengerDetails);
        jPanelPassengerDetails.setLayout(jPanelPassengerDetailsLayout);
        jPanelPassengerDetailsLayout.setHorizontalGroup(
            jPanelPassengerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPassengerDetailsLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanelPassengerDetailsLayout.setVerticalGroup(
            jPanelPassengerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPassengerDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPassengerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(250, Short.MAX_VALUE))
        );

        btnConfirmBooking.setText("Confirm Booking");
        btnConfirmBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmBookingActionPerformed(evt);
            }
        });

        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        btnBack.setText("<<Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnclearSeat.setText("Clear Seat Selection");
        btnclearSeat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearSeatActionPerformed(evt);
            }
        });

        btnLogout.setText("Log Out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnclearSeat, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(177, 177, 177))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jPanelPassengerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(290, 290, 290)
                                .addComponent(btnConfirmBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(100, 100, 100))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jPanelPassengerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnclearSeat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConfirmBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(240, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmBookingActionPerformed
        // TODO add your handling code here:
        if(arrayPanel.isEmpty()){
            JOptionPane.showMessageDialog(null, "Kindly book your seats","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTableModel dtm = (DefaultTableModel) tblSeats.getModel();
        flight.setSeats(dtm.getDataVector());
        PassengerDirectory passengerDirectory;
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Total Cost of Booking is :$"+totalCost+"\nAre you sure you want to confirm your Booking?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            passengerDirectory = new PassengerDirectory();
            for(ArrayList<JTextField> arrTextField: arrayPanel){
                Passenger p = passengerDirectory.addPassenger();
                int i = 1;
                for(JTextField textField: arrTextField ){
                    if(i == 1){
                        if(textField.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null, "Passenger Name cannot be empty","Warning",JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        if(!onlyLettersValidation(textField.getText())){
                            JOptionPane.showMessageDialog(null, "Name can only contain letters","Warning",JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        p.setPassengerName(textField.getText());
                    } else if(i == 2){
                        if(textField.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null, "Passenger Passport cannot be empty","Warning",JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        p.setPassengerPassportNumber(textField.getText());
                    }else if(i == 3){
                        if(textField.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null, "Passenger Age cannot be empty","Warning",JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        try{
                        p.setPassengerAge(Integer.parseInt(textField.getText()));
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null, "Please enter a valid number for Age","Warning",JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }               
                   i++;
                }
            }
            totalCost = passengerDirectory.getPassengerDirectory().size()*flight.getFlightCost();
            //BookingDirectory bookingDirectory = new BookingDirectory();
            Booking booking = bookingDir.addBooking();
            booking.setCustomer((Customer)userName);
            booking.setFlight(flight);
            booking.setPassengerDirectory(passengerDirectory);
            booking.setTotalNumOfSeatsBooked(numberOfSeats);
            booking.setTotalCost(totalCost);
            CardLayout layout = (CardLayout)rightPanel.getLayout();
            rightPanel.add(new BookingSummary(rightPanel,jPanelLeft, booking));
            layout.next(rightPanel);

        }
    }//GEN-LAST:event_btnConfirmBookingActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        rightPanel.remove(this);
        CardLayout layout = (CardLayout)this.rightPanel.getLayout();
        layout.previous(rightPanel);
        int row1,col1;
        
        for(Pair<Integer,Integer> pair : set_of_pairs){
                row1=pair.getKey();
                col1=pair.getValue();
                tblSeats.setValueAt(false,row1,col1);
        }
        
        
    
        //dataModel.fireTableChanged(e);
        
       
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnclearSeatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearSeatActionPerformed
        // TODO add your handling code here:
        int row1,col1;
        for(Pair<Integer,Integer> pair : set_of_pairs){
                row1=pair.getKey();
                col1=pair.getValue();
                tblSeats.setValueAt(false,row1,col1);
                
        }
        for(int i=numberOfSeats;i>0;i--){
        deleteTextFields();
        jPanelPassengerDetails.repaint();
        }
        numberOfSeats=0;
        totalCost = 0;
        //lblTotalCost.setText("Total Cost: "+totalCost);
    }//GEN-LAST:event_btnclearSeatActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        MainJFrame.setUserName(null);
        for(Component comp : rightPanel.getComponents()){
            if(comp instanceof LoginScreen){

            } else{
                rightPanel.remove(comp);
            }
            CardLayout layout = (CardLayout)this.rightPanel.getLayout();
            layout.previous(rightPanel);
        }
        for(Component comp : jPanelLeft.getComponents()){
            if(comp instanceof JButton)
            comp.setVisible(false);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnConfirmBooking;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnclearSeat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanelPassengerDetails;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JTable tblSeats;
    // End of variables declaration//GEN-END:variables

}
