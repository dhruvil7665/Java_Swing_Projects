/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.TrekManagerRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.SportStoreEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.SportsStoreManagerOrganization;
import Business.Organization.TrekFinanceOrganization;
import Business.Organization.TrekLeaderOrganization;
import Business.Organization.TrekManagerOrganization;
import Business.Trek.Trek;
import Business.Trek.TrekBooking;
import Business.Trek.TrekBookingDirectory;
import Business.Trek.TrekSlotAndCost;
import Business.UserAccount.UserAccount;
import Business.Validations.Validations;
import Business.WorkQueue.AddTrekCostWorkRequest;
import Business.WorkQueue.AskSportsStoreForTrendWorkRequest;
import Business.WorkQueue.AskTrekCompanyForTrendWorkRequest;
import Business.WorkQueue.AssignTrekLeaderWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import UserInterface.CustomerRole.CustomerBookingJPanel;

/**
 *
 * @author dedhi
 */
public class TrekManagerWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageTrekJPanel
     */
//    public TrekManagerWorkAreaJPanel() {
//        initComponents();
//    }
    private JPanel userProcessContainer;
    private UserAccount account;
    private TrekManagerOrganization trekManagerOrganization;
    private Enterprise enterprise;
    private EcoSystem business;
    private String trekVideoPath;
    private ArrayList<String> trekImagePath = new ArrayList<String>();
    private Validations validations = new Validations();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    public TrekManagerWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, TrekManagerOrganization trekManagerOrganization, Enterprise enterprise, EcoSystem business) throws ParseException {
        initComponents();
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.trekManagerOrganization = trekManagerOrganization;
        this.enterprise = enterprise;
        this.business = business;
        populateTrekNameComboBox();
        populatePublicUploadTable();
        populateTrekDetailsTable();
        pieDiagram();
        populateAddCostWorkRequestTable();
        populateConfirmTrekLeaderWorkRequestTable();
        populateRequestTable();
        populateResponseTable();
    }
    
    public void populateTrekNameComboBox(){
        trekNameComboBox.removeAllItems();
        System.out.println("inside combo box: "+trekManagerOrganization.getTrekDirectory().getTrekArrayList().size());
        for(Trek trek : trekManagerOrganization.getTrekDirectory().getTrekArrayList()){
            trekNameComboBox.addItem(trek);
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
    
    public void populateConfirmTrekLeaderWorkRequestTable(){
        DefaultTableModel model = (DefaultTableModel) confirmTrekLeaderWorkRequestTable.getModel();
        model.setRowCount(0);
        
        for(WorkRequest wq: account.getWorkQueue().getWorkRequestList()){
            if(wq instanceof AssignTrekLeaderWorkRequest){
                AssignTrekLeaderWorkRequest req = (AssignTrekLeaderWorkRequest) wq;
                Object[] row = new Object[5];
                row[0] = req.getSender();
                row[1] = req.getReceiver();
                row[2] = req.getTrek();
                row[3] = dateTimeFormat.format(req.getTrekSlotAndCost().getTrekStartDate());
                row[4] = req.getStatus();
                model.addRow(row);
            }
        }
    }
    
    public void populateTrekDetailsTable(){
        DefaultTableModel model = (DefaultTableModel) trekDetailsTable.getModel();
        model.setRowCount(0);
        for(Trek t: trekManagerOrganization.getTrekDirectory().getTrekArrayList()){
            if(t.getTrekSlotAndCost().size() <= 0){
                System.out.println("inside no slots populate table");
                Object[] row = new Object[6];
                row[0] = t;
                row[1] = t.getTrekLocation();
                row[2] = "Slots not added";
                row[3] = t.getTrekDuration();
                row[4] = new TrekSlotAndCost();
                row[5] = new AddTrekCostWorkRequest();
                model.addRow(row);
            } else{
                int flag = 0;
                for(WorkRequest wq: trekManagerOrganization.getWorkQueue().getWorkRequestList()){
                    if(wq instanceof AssignTrekLeaderWorkRequest){
                        if(((AssignTrekLeaderWorkRequest) wq).getTrek() == t){
                            Object[] row = new Object[6];
                            row[0] = ((AssignTrekLeaderWorkRequest) wq).getTrek();
                            row[1] = ((AssignTrekLeaderWorkRequest) wq).getTrek().getTrekLocation();
                            row[2] = ((AssignTrekLeaderWorkRequest) wq).getTrekSlotAndCost().getAvailableSeats();
                            row[3] = ((AssignTrekLeaderWorkRequest) wq).getTrek().getTrekDuration();
                            row[4] = ((AssignTrekLeaderWorkRequest) wq).getTrekSlotAndCost();
                            row[5] = wq;
                            model.addRow(row);
                            flag = 1;
                            break;
                        }
                    }
                }
                if(flag == 0){
                    for(WorkRequest wq: trekManagerOrganization.getWorkQueue().getWorkRequestList()){
                    if(wq instanceof AddTrekCostWorkRequest){
                        if(((AddTrekCostWorkRequest) wq).getTrek() == t){
                            Object[] row = new Object[6];
                            row[0] = ((AddTrekCostWorkRequest) wq).getTrek();
                            row[1] = ((AddTrekCostWorkRequest) wq).getTrek().getTrekLocation();
                            row[2] = ((AddTrekCostWorkRequest) wq).getTrekSlotAndCost().getAvailableSeats();
                            row[3] = ((AddTrekCostWorkRequest) wq).getTrek().getTrekDuration();
                            row[4] = ((AddTrekCostWorkRequest) wq).getTrekSlotAndCost();
                            row[5] = wq;
                            model.addRow(row);
                            flag = 1;
                            break;
                        }
                    }
                }
                }
            }
        }
        
    }
    
    public void populatePublicUploadTable() throws ParseException{
        DefaultTableModel model = (DefaultTableModel) publicUploadTable.getModel();
        model.setRowCount(0);
//        TrekLeaderOrganization trekLeaderOrganization = null;
//        for(Organization org : enterprise.getOrganizationDirectory().getOrganizationList()){
//            if(org instanceof TrekLeaderOrganization){
//                trekLeaderOrganization = (TrekLeaderOrganization) org;
//                break;
//            }
//        }

        for (WorkRequest req : trekManagerOrganization.getWorkQueue().getWorkRequestList()){
            if(req instanceof AssignTrekLeaderWorkRequest){
            AssignTrekLeaderWorkRequest request = (AssignTrekLeaderWorkRequest) req;
            
            //row[0] = request.getTrekName();
            if(request.getReceiver() == null && request.getStatus().equals("Pending to confirm the assignment") ){
                Object[] row = new Object[7];
                row[0] = request.getTrek();
                row[1] = request.getTrek().getTrekLocation();
                row[2] = dateTimeFormat.format(request.getTrekSlotAndCost().getTrekStartDate());
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(request.getTrekSlotAndCost().getTrekStartDate());
                cal.add(Calendar.DATE,request.getTrek().getTrekDuration());
                Date trekEndDate = dateTimeFormat.parse(dateTimeFormat.format(cal.getTime()));
                row[3] = dateTimeFormat.format(trekEndDate);
                row[4] = request.getTrekSlotAndCost().getTrekTotalCost();
                row[5] = request.getReceiver();
                row[6] = request;
                model.addRow(row);
            }
            }
        }
        
    }

    public void pieDiagram(){
        //Categorywise total Treks
        Map<String,Integer> categorywiseTrekMap = categoryWithTotalTreks();
        DefaultPieDataset dataset1 = new DefaultPieDataset();
        
        for(Map.Entry<String,Integer> entry: categorywiseTrekMap.entrySet()){
            dataset1.setValue(entry.getKey(),entry.getValue());
        }
        JFreeChart chart1 = ChartFactory.createPieChart("Categorywise total Treks", dataset1, true, true, true);
       // ChartFrame yourFrame = new ChartFrame("Your title", chart);
        ChartPanel yourFrame1 = new ChartPanel(chart1);
        //yourFrame.setPreferredSize(new Dimension(500,100));
        jPanel3.add(yourFrame1);
        jPanel3.validate();
        
        //Categorywise total revenue
        Map<String,Float> categorywiseRevenueMap = categoryWiseTotalRevenue();
        DefaultPieDataset dataset2 = new DefaultPieDataset();
        
        for(Map.Entry<String,Float> entry: categorywiseRevenueMap.entrySet()){
            dataset2.setValue(entry.getKey(),entry.getValue());
        }
        JFreeChart chart2 = ChartFactory.createPieChart("Categorywise total Revenue", dataset2, true, true, true);
       // ChartFrame yourFrame = new ChartFrame("Your title", chart);
        ChartPanel yourFrame2 = new ChartPanel(chart2);
        //yourFrame.setPreferredSize(new Dimension(500,100));
        jPanel3.add(yourFrame2);
        jPanel3.validate();
        
        //Weatherwise total treks booked
        Map<String,Integer> weatherwiseTotalTreks = weatherWithTotalTreks();
        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
        
        for(Map.Entry<String,Integer> entry: weatherwiseTotalTreks.entrySet()){
            System.out.println("weather: "+entry.getKey());
            dataset3.setValue(entry.getValue(), "weather", entry.getKey());
           // dataset3.setValue(entry.getKey(),"weather",entry.getValue());
        }
        JFreeChart chart3 = ChartFactory.createLineChart("Weatherwise total Treks", "Weather", "Total No. of Treks", (CategoryDataset) dataset3);
        //JFreeChart chart3 = ChartFactory.createPieChart("Weatherwise total Treks", dataset3, true, true, true);
       // ChartFrame yourFrame = new ChartFrame("Your title", chart);
        ChartPanel yourFrame3 = new ChartPanel(chart3);
        //yourFrame.setPreferredSize(new Dimension(500,100));
        jPanel3.add(yourFrame3);
        jPanel3.validate();
    }
    
    public Map<String,Integer> categoryWithTotalTreks(){
        Map<String,Integer> countMap =  new HashMap<String, Integer>();
        ArrayList<TrekBooking> trekBookingList = trekManagerOrganization.getTrekBookingDirectory().getTrekBookingList();
        
        for(TrekBooking trekBooking: trekBookingList){
            if(countMap.containsKey(trekBooking.getTrek().getTrekLocation())){
                int cnt = countMap.get(trekBooking.getTrek().getTrekLocation());
                countMap.put(trekBooking.getTrek().getTrekLocation(),cnt++);
            } else{
                countMap.put(trekBooking.getTrek().getTrekLocation(),1);
            }
        }
        return countMap;
     }
    
    public Map<String,Float> categoryWiseTotalRevenue(){
         Map<String,Float> countMap =  new HashMap<String, Float>();
        ArrayList<TrekBooking> trekBookingList = trekManagerOrganization.getTrekBookingDirectory().getTrekBookingList();
        
        for(TrekBooking trekBooking: trekBookingList){
            if(countMap.containsKey(trekBooking.getTrek().getTrekLocation())){
                float cost = countMap.get(trekBooking.getTrek().getTrekLocation());
                cost = cost + trekBooking.getTrekSlotAndCost().getTrekTotalCost()*trekBooking.getTotalTrekkers();
                countMap.put(trekBooking.getTrek().getTrekLocation(),cost);
            } else{
                float cost = trekBooking.getTrekSlotAndCost().getTrekTotalCost()*trekBooking.getTotalTrekkers();
                countMap.put(trekBooking.getTrek().getTrekLocation(),cost);
            }
        }
        return countMap;
    }
    
    public Map<String,Integer> weatherWithTotalTreks(){
        Map<String,Integer> countMap =  new HashMap<String, Integer>();
        ArrayList<TrekBooking> trekBookingList = trekManagerOrganization.getTrekBookingDirectory().getTrekBookingList();
        
        for(TrekBooking trekBooking: trekBookingList){
            if(countMap.containsKey(trekBooking.getTrek().getTrekWeather())){
                int cnt = countMap.get(trekBooking.getTrek().getTrekWeather());
                countMap.put(trekBooking.getTrek().getTrekWeather(),cnt++);
            } else{
                countMap.put(trekBooking.getTrek().getTrekWeather(),1);
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

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        addTrekJPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        trekNameTxtField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        trekLocTxtField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        trekDescTxtField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        basicReqTxtArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        durationSpinner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itineraryTextArea = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pickUpPointTxtField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        baseCampTxtField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        maxAltitudeSpinner = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        minAgeSpinner = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        bestTimeToTrekTxtField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        fitnessRequiredTxtArea = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        uploadImageButton = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        uploadVideoButton = new javax.swing.JButton();
        trekImageNamesLabel = new javax.swing.JLabel();
        trekVideoNameLabel = new javax.swing.JLabel();
        addTrekButton = new javax.swing.JButton();
        difficultyLevelComboBox = new javax.swing.JComboBox();
        weatherComboBox = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        slotButton = new javax.swing.JButton();
        trekNameComboBox = new javax.swing.JComboBox();
        slotDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jLabel16 = new javax.swing.JLabel();
        totalSeatsTxtField = new javax.swing.JTextField();
        timeSpinner = new javax.swing.JSpinner(new SpinnerDateModel());
        jLabel24 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        trekDetailsTable = new javax.swing.JTable();
        editTrekButton = new javax.swing.JButton();
        deleteTrekButton = new javax.swing.JButton();
        publicUploadJPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        publicUploadTable = new javax.swing.JTable();
        publicUploadButton = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        addCostWorkRequestTable = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        confirmTrekLeaderWorkRequestTable = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        sendRequestButton = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();
        label = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        responseTable = new javax.swing.JTable();
        AddResponseButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1135, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 927, Short.MAX_VALUE)
        );

        jScrollPane8.setPreferredSize(new java.awt.Dimension(800, 800));

        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 255));
        jTabbedPane1.setFocusTraversalPolicyProvider(true);
        jTabbedPane1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(2713, 2061));

        addTrekJPanel.setBackground(new java.awt.Color(33, 108, 205));
        addTrekJPanel.setPreferredSize(new java.awt.Dimension(800, 800));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Trek Name");

        trekNameTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Trek Location");

        trekLocTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Trek Description");

        trekDescTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Difficulty Level");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Basic Requirements");

        basicReqTxtArea.setColumns(20);
        basicReqTxtArea.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        basicReqTxtArea.setRows(5);
        jScrollPane1.setViewportView(basicReqTxtArea);

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Duration");

        durationSpinner.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Trek Itinerary");

        itineraryTextArea.setColumns(20);
        itineraryTextArea.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        itineraryTextArea.setRows(5);
        jScrollPane2.setViewportView(itineraryTextArea);

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Weather");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Pick Up Point");

        pickUpPointTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Base Camp");

        baseCampTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Maximum Altitude");

        maxAltitudeSpinner.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Minimum Age");

        minAgeSpinner.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Best Time to trek");

        bestTimeToTrekTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Fitness Required");

        fitnessRequiredTxtArea.setColumns(20);
        fitnessRequiredTxtArea.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        fitnessRequiredTxtArea.setRows(5);
        jScrollPane3.setViewportView(fitnessRequiredTxtArea);

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Trek Images");

        uploadImageButton.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        uploadImageButton.setText("Upload Image");
        uploadImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadImageButtonActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Trek Video");

        uploadVideoButton.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        uploadVideoButton.setText("Upload Video");
        uploadVideoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadVideoButtonActionPerformed(evt);
            }
        });

        trekImageNamesLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        trekImageNamesLabel.setText("Image names");

        trekVideoNameLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        trekVideoNameLabel.setText("Video name");

        addTrekButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekManagerRole/addTrekButton.png"))); // NOI18N
        addTrekButton.setContentAreaFilled(false);
        addTrekButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addTrekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTrekButtonActionPerformed(evt);
            }
        });

        difficultyLevelComboBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        difficultyLevelComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Easy", "Easy-Moderate", "Moderate", "Moderate-Difficult", "Difficult" }));

        weatherComboBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        weatherComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Summer", "Winter", "Fall" }));

        jLabel23.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Add Trek");

        javax.swing.GroupLayout addTrekJPanelLayout = new javax.swing.GroupLayout(addTrekJPanel);
        addTrekJPanel.setLayout(addTrekJPanelLayout);
        addTrekJPanelLayout.setHorizontalGroup(
            addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTrekJPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(98, 98, 98)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addTrekJPanelLayout.createSequentialGroup()
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addTrekJPanelLayout.createSequentialGroup()
                                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(uploadImageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(uploadVideoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(trekImageNamesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(addTrekJPanelLayout.createSequentialGroup()
                                        .addComponent(trekVideoNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(trekDescTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bestTimeToTrekTxtField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(minAgeSpinner, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(maxAltitudeSpinner, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(baseCampTxtField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pickUpPointTxtField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(weatherComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(durationSpinner, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(difficultyLevelComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(310, 310, 310))
                    .addGroup(addTrekJPanelLayout.createSequentialGroup()
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(trekNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trekLocTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(addTrekButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))))
            .addGroup(addTrekJPanelLayout.createSequentialGroup()
                .addGap(447, 447, 447)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        addTrekJPanelLayout.setVerticalGroup(
            addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addTrekJPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel23)
                .addGap(31, 31, 31)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addTrekButton)
                    .addGroup(addTrekJPanelLayout.createSequentialGroup()
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(trekNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(trekLocTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trekDescTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(difficultyLevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(durationSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(10, 10, 10)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(weatherComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(pickUpPointTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(baseCampTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(maxAltitudeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(minAgeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(bestTimeToTrekTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(uploadImageButton)
                    .addComponent(trekImageNamesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTrekJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(uploadVideoButton)
                    .addComponent(trekVideoNameLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add trek", addTrekJPanel);

        jScrollPane11.setPreferredSize(new java.awt.Dimension(600, 500));

        jPanel3.setBackground(new java.awt.Color(33, 108, 205));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 600));
        jPanel3.setLayout(new java.awt.GridLayout(0, 2, 5, 5));
        jScrollPane11.setViewportView(jPanel3);

        jTabbedPane1.addTab("DashBoard", jScrollPane11);

        jPanel1.setBackground(new java.awt.Color(33, 108, 205));

        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Trek Name");

        jLabel20.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Slot");

        jButton1.setText("jButton1");

        slotButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekManagerRole/addSlotButton.png"))); // NOI18N
        slotButton.setContentAreaFilled(false);
        slotButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        slotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slotButtonActionPerformed(evt);
            }
        });

        trekNameComboBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        trekNameComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        slotDatePicker.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Total Seats");

        totalSeatsTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        timeSpinner.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Add Trek Slots");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(slotButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(trekNameComboBox, 0, 243, Short.MAX_VALUE)
                            .addComponent(slotDatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalSeatsTxtField))
                        .addGap(18, 18, 18)
                        .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1479, 1479, 1479))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel24)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(trekNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(slotDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(totalSeatsTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(slotButton)
                .addContainerGap(1665, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Trek Slots", jPanel1);

        jPanel4.setBackground(new java.awt.Color(33, 108, 205));

        trekDetailsTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        trekDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trek Name", "Trek Location", "Total Trekkers", "Trek Duration", "Trek Start Date", "Trek Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        trekDetailsTable.setRowHeight(22);
        jScrollPane9.setViewportView(trekDetailsTable);
        if (trekDetailsTable.getColumnModel().getColumnCount() > 0) {
            trekDetailsTable.getColumnModel().getColumn(0).setResizable(false);
            trekDetailsTable.getColumnModel().getColumn(1).setResizable(false);
            trekDetailsTable.getColumnModel().getColumn(2).setResizable(false);
            trekDetailsTable.getColumnModel().getColumn(3).setResizable(false);
            trekDetailsTable.getColumnModel().getColumn(4).setResizable(false);
            trekDetailsTable.getColumnModel().getColumn(5).setResizable(false);
        }

        editTrekButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekManagerRole/viewEditTrekButton.png"))); // NOI18N
        editTrekButton.setContentAreaFilled(false);
        editTrekButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editTrekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTrekButtonActionPerformed(evt);
            }
        });

        deleteTrekButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekManagerRole/deleteTrekButton.png"))); // NOI18N
        deleteTrekButton.setContentAreaFilled(false);
        deleteTrekButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteTrekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTrekButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(editTrekButton)
                        .addGap(64, 64, 64)
                        .addComponent(deleteTrekButton))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1647, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteTrekButton)
                    .addComponent(editTrekButton))
                .addContainerGap(1672, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View/Edit Trek", jPanel4);

        publicUploadJPanel.setBackground(new java.awt.Color(33, 108, 205));

        publicUploadTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        publicUploadTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trek Name", "Trek Location", "Trek Start Date", "Trek End Date", "Trek Total Cost", "Trek Leader Name", "Request Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        publicUploadTable.setRowHeight(22);
        jScrollPane4.setViewportView(publicUploadTable);
        if (publicUploadTable.getColumnModel().getColumnCount() > 0) {
            publicUploadTable.getColumnModel().getColumn(0).setResizable(false);
            publicUploadTable.getColumnModel().getColumn(1).setResizable(false);
            publicUploadTable.getColumnModel().getColumn(2).setResizable(false);
            publicUploadTable.getColumnModel().getColumn(2).setHeaderValue("Trek Start Date");
            publicUploadTable.getColumnModel().getColumn(3).setResizable(false);
            publicUploadTable.getColumnModel().getColumn(3).setHeaderValue("Trek End Date");
            publicUploadTable.getColumnModel().getColumn(4).setResizable(false);
            publicUploadTable.getColumnModel().getColumn(4).setHeaderValue("Trek Total Cost");
            publicUploadTable.getColumnModel().getColumn(5).setHeaderValue("Trek Leader Name");
            publicUploadTable.getColumnModel().getColumn(6).setResizable(false);
            publicUploadTable.getColumnModel().getColumn(6).setHeaderValue("Request Status");
        }

        publicUploadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/TrekManagerRole/publicUploadButton.png"))); // NOI18N
        publicUploadButton.setContentAreaFilled(false);
        publicUploadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        publicUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                publicUploadButtonActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Upload Trek for Users");

        javax.swing.GroupLayout publicUploadJPanelLayout = new javax.swing.GroupLayout(publicUploadJPanel);
        publicUploadJPanel.setLayout(publicUploadJPanelLayout);
        publicUploadJPanelLayout.setHorizontalGroup(
            publicUploadJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(publicUploadJPanelLayout.createSequentialGroup()
                .addGroup(publicUploadJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(publicUploadJPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(publicUploadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(publicUploadJPanelLayout.createSequentialGroup()
                        .addGap(425, 425, 425)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(publicUploadJPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1594, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        publicUploadJPanelLayout.setVerticalGroup(
            publicUploadJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, publicUploadJPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel25)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(publicUploadButton)
                .addGap(689, 689, 689))
        );

        jTabbedPane1.addTab("Public Upload", publicUploadJPanel);

        jPanel5.setBackground(new java.awt.Color(33, 108, 205));

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
        jScrollPane5.setViewportView(addCostWorkRequestTable);
        if (addCostWorkRequestTable.getColumnModel().getColumnCount() > 0) {
            addCostWorkRequestTable.getColumnModel().getColumn(0).setResizable(false);
            addCostWorkRequestTable.getColumnModel().getColumn(1).setResizable(false);
            addCostWorkRequestTable.getColumnModel().getColumn(2).setResizable(false);
            addCostWorkRequestTable.getColumnModel().getColumn(3).setResizable(false);
            addCostWorkRequestTable.getColumnModel().getColumn(4).setResizable(false);
        }

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

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("List of Request sent to Finance Manager for adding Cost");

        jLabel21.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("List of Request received from Trek Leaders for Confirmation");

        jLabel26.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("My Work Requests Status");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1448, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(270, 270, 270)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(285, 285, 285)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(375, 375, 375)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1448, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel26)
                .addGap(42, 42, 42)
                .addComponent(jLabel15)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jLabel21)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1223, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("My Work Request Status", jPanel5);

        jPanel6.setBackground(new java.awt.Color(33, 108, 205));

        sendRequestButton.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        sendRequestButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/TrekManagerRole/sendRequestToSport.png"))); // NOI18N
        sendRequestButton.setContentAreaFilled(false);
        sendRequestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendRequestButtonActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("List of Requests sent to Sports Store asking for Suitable Weather for Trending Gears");

        requestTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        requestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request Sender", "Request Receiver", "Response Received", "Request Status", "Date of Request Sent", "Date of Response Received"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(requestTable);
        if (requestTable.getColumnModel().getColumnCount() > 0) {
            requestTable.getColumnModel().getColumn(0).setResizable(false);
            requestTable.getColumnModel().getColumn(1).setResizable(false);
            requestTable.getColumnModel().getColumn(2).setResizable(false);
            requestTable.getColumnModel().getColumn(3).setResizable(false);
            requestTable.getColumnModel().getColumn(4).setResizable(false);
            requestTable.getColumnModel().getColumn(5).setResizable(false);
        }

        label.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setText("List of Requests send by the Sports Store asking for Trending Trek Weather");

        responseTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        responseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request Sender", "Request Receiver", "Response Received", "Request Status", "Date of Request Sent", "Date of Response Received"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(responseTable);
        if (responseTable.getColumnModel().getColumnCount() > 0) {
            responseTable.getColumnModel().getColumn(0).setResizable(false);
            responseTable.getColumnModel().getColumn(1).setResizable(false);
            responseTable.getColumnModel().getColumn(2).setResizable(false);
            responseTable.getColumnModel().getColumn(3).setResizable(false);
            responseTable.getColumnModel().getColumn(4).setResizable(false);
            responseTable.getColumnModel().getColumn(5).setResizable(false);
        }

        AddResponseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/SportsStoreManagerRole/responseButton.png"))); // NOI18N
        AddResponseButton.setContentAreaFilled(false);
        AddResponseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddResponseButtonActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(102, 102, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Requests to/from Sports Store");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sendRequestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddResponseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1427, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(611, 611, 611))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel27)
                .addGap(31, 31, 31)
                .addComponent(jLabel22)
                .addGap(41, 41, 41)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sendRequestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AddResponseButton)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Request to Sports Store", jPanel6);

        jScrollPane8.setViewportView(jTabbedPane1);
        jTabbedPane1.getAccessibleContext().setAccessibleName("Approve Trek");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1201, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1252, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void slotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slotButtonActionPerformed
            // TODO add your handling code here:
        Trek trek = (Trek) trekNameComboBox.getSelectedItem();
        if(trek == null){
            JOptionPane.showMessageDialog(null, "Please add a Trek first","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        try{
            if(totalSeatsTxtField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Total Number of seats cannot be empty","Warning",JOptionPane.WARNING_MESSAGE);
                return;
            }
            int totalSeats = Integer.parseInt(totalSeatsTxtField.getText());
            if(totalSeats <= 0){
                JOptionPane.showMessageDialog(null, "Please enter a valid number for Total seats","Warning",JOptionPane.WARNING_MESSAGE);
                return;
            }
           // Date trekStartDate = slotDatePicker.getDate();
            String trekStartDate = dateFormat.format(slotDatePicker.getDate());
            String trekStartTime = timeFormat.format(timeSpinner.getValue());
            String trekStartDateTimeString = trekStartDate+" "+trekStartTime;
            Date trekStartDateTime = dateTimeFormat.parse(trekStartDateTimeString);
            if(trekStartDate == null){
                JOptionPane.showMessageDialog(null, "Please select a Date","Warning",JOptionPane.WARNING_MESSAGE);
                return;
            }
            Date currentDate = dateTimeFormat.parse(dateTimeFormat.format(new Date()));
            if(trekStartDateTime.compareTo(currentDate) <= 0){
                JOptionPane.showMessageDialog(null, "Trek Start date has to be future date");
            }
            TrekFinanceOrganization trekFinanceOrganization = null;
            AddTrekCostWorkRequest request = new AddTrekCostWorkRequest();
            
            //Adding Slot and total members in a slot
            TrekSlotAndCost trekSlotAndCost = request.getTrekSlotAndCost();
            trekSlotAndCost.setTrekStartDate(trekStartDateTime);
            trekSlotAndCost.setTotalSeats(totalSeats);
            trekSlotAndCost.setAvailableSeats(totalSeats);
            request.setTrek(trek);
            request.setSender(account);
            request.setStatus("Pending for costing");
           // trek.getTrekSlotAndCost().add(trekSlotAndCost);

            //Added the work request in work queue of employee
            trekManagerOrganization.getWorkQueue().getWorkRequestList().add(request);
            account.getWorkQueue().getWorkRequestList().add(request);
            for(Organization org : enterprise.getOrganizationDirectory().getOrganizationList()){
                if(org instanceof TrekFinanceOrganization){
                    trekFinanceOrganization = (TrekFinanceOrganization) org;
                    break;
                }
            }
            if(trekFinanceOrganization != null){
                //Added the work request in work queue of Trek finance organization
                trekFinanceOrganization.getWorkQueue().getWorkRequestList().add(request);
            }
            JOptionPane.showMessageDialog(null, "Slot added successfully");
            } 
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a valid number for Total seats","Warning",JOptionPane.WARNING_MESSAGE);
                return;
            } catch (ParseException ex) {   
            Logger.getLogger(TrekManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        totalSeatsTxtField.setText("");
        slotDatePicker.setDate(new Date());
        timeSpinner.setValue(new Date());
        populateTrekDetailsTable();
        populateAddCostWorkRequestTable();
        populateConfirmTrekLeaderWorkRequestTable();
    }//GEN-LAST:event_slotButtonActionPerformed

    private void publicUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_publicUploadButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = publicUploadTable.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a Trek","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        AssignTrekLeaderWorkRequest request = (AssignTrekLeaderWorkRequest) publicUploadTable.getValueAt(selectedRow, 6);
        Trek trek = request.getTrek();
        request.setStatus("Completed");
        request.setReceiver(account);
        account.getWorkQueue().getWorkRequestList().add(request);
        TrekSlotAndCost slotAndCost = request.getTrekSlotAndCost();
        trek.getTrekSlotAndCost().add(slotAndCost);
        trek.setTrekLeader(request.getSender().getEmployee().getName());
        //UserAccount trekLeaderAccount = request.getReceiver();
        //trekLeaderAccount.getWorkQueue().getWorkRequestList().add(request);
        JOptionPane.showMessageDialog(null, "Trek added for public successfully");
        try {    
            populatePublicUploadTable();
        } catch (ParseException ex) {
            Logger.getLogger(TrekManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        populateAddCostWorkRequestTable();
        populateConfirmTrekLeaderWorkRequestTable();
        populateTrekDetailsTable();
    }//GEN-LAST:event_publicUploadButtonActionPerformed

    private void addTrekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTrekButtonActionPerformed
        // TODO add your handling code here:
        int flag = checkForValidations(trekNameTxtField.getText(),trekLocTxtField.getText(),trekDescTxtField.getText(),basicReqTxtArea.getText(),durationSpinner.getValue().toString(),
        itineraryTextArea.getText(),weatherComboBox.getSelectedItem().toString(),pickUpPointTxtField.getText(),baseCampTxtField.getText(),maxAltitudeSpinner.getValue().toString(),
        minAgeSpinner.getValue().toString(),bestTimeToTrekTxtField.getText(),fitnessRequiredTxtArea.getText(),trekImagePath);
        
        if(flag == 0){
            return;
        }
        
        Trek trek = trekManagerOrganization.getTrekDirectory().addTrek();
        trek.setTrekName(trekNameTxtField.getText());
        trek.setTrekLocation(trekLocTxtField.getText());
        trek.setTrekDescription(trekDescTxtField.getText());
        trek.setTrekDifficultyLevel(difficultyLevelComboBox.getSelectedItem().toString());
        trek.setTrekBasicRequirement(basicReqTxtArea.getText());
        trek.setTrekDuration(Integer.parseInt(durationSpinner.getValue().toString()));
        trek.setTrekItinerary(itineraryTextArea.getText());
        trek.setTrekWeather(weatherComboBox.getSelectedItem().toString());
        trek.setTrekPickUpPoint(pickUpPointTxtField.getText());
        trek.setTrekBaseCamp(baseCampTxtField.getText());
        trek.setTrekMaxAltitude(Integer.parseInt(maxAltitudeSpinner.getValue().toString()));
        trek.setTrekMinAge(Integer.parseInt(minAgeSpinner.getValue().toString()));
        trek.setTrekBestTimeToVisit(bestTimeToTrekTxtField.getText());
        trek.setTrekFitnessRequired(fitnessRequiredTxtArea.getText());
        trek.setTrekVideo(trekVideoPath);
        trek.setTrekImages(trekImagePath);
        // trek.setTrekVideo();
        trekManagerOrganization.getTrekDirectory().getTrekArrayList().add(trek);
        populateTrekNameComboBox();
        JOptionPane.showMessageDialog(null, "Trek added successfully");
        populateTrekDetailsTable();
        trekNameTxtField.setText("");
        trekLocTxtField.setText("");
        trekDescTxtField.setText("");
        difficultyLevelComboBox.setSelectedIndex(0);
        basicReqTxtArea.setText("");
        durationSpinner.setValue(0);
        itineraryTextArea.setText("");
        weatherComboBox.setSelectedIndex(0);
        pickUpPointTxtField.setText("");
        baseCampTxtField.setText("");
        maxAltitudeSpinner.setValue(0);
        minAgeSpinner.setValue(0);
        bestTimeToTrekTxtField.setText("");
        fitnessRequiredTxtArea.setText("");
        //totalTrekkersSpinner.setValue("");
        trekImageNamesLabel.setText("");
        trekVideoNameLabel.setText("");
        trekImagePath = new ArrayList<String>();
        
        populateAddCostWorkRequestTable();
        populateConfirmTrekLeaderWorkRequestTable();
    }//GEN-LAST:event_addTrekButtonActionPerformed

    private void uploadVideoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadVideoButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Trek video");
        fileChooser.setAcceptAllFileFilterUsed(false);
        //FileNameExtensionFilter nameFilter = new FileNameExtensionFilter("PNG, JPG and JPEG files only","png","jpg","jpeg");
        //fileChooser.addChoosableFileFilter(nameFilter);
        int i = fileChooser.showOpenDialog(fileChooser);

        if(i == JFileChooser.APPROVE_OPTION)
        {
            trekVideoPath = fileChooser.getSelectedFile().getAbsolutePath();
           
            trekVideoNameLabel.setText(fileChooser.getSelectedFile().getName());

        }else {
            JOptionPane.showMessageDialog(null,"No video selected","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_uploadVideoButtonActionPerformed

    private void uploadImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadImageButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Trek Images");
        fileChooser.setAcceptAllFileFilterUsed(false);
        //FileNameExtensionFilter nameFilter = new FileNameExtensionFilter("PNG, JPG and JPEG files only","png","jpg","jpeg");
        //fileChooser.addChoosableFileFilter(nameFilter);
        int i = fileChooser.showOpenDialog(fileChooser);

        if(i == JFileChooser.APPROVE_OPTION)
        {
            String trekImage = fileChooser.getSelectedFile().getAbsolutePath();
            String trekImageName = fileChooser.getSelectedFile().getName();
            trekImageNamesLabel.setText(trekImageNamesLabel.getText()+", "+trekImageName);
            trekImagePath.add(trekImage);
        }else {
            JOptionPane.showMessageDialog(null,"No image selected","Error",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }//GEN-LAST:event_uploadImageButtonActionPerformed

    private void editTrekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTrekButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = trekDetailsTable.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a Trek","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Trek trek = (Trek) trekDetailsTable.getValueAt(selectedRow, 0);
        CardLayout layout=(CardLayout)userProcessContainer.getLayout();
        userProcessContainer.add("editTrekJPanel",new EditTrekJPanel(userProcessContainer, account, trekManagerOrganization, enterprise, business,trek));
        layout.next(userProcessContainer);
    }//GEN-LAST:event_editTrekButtonActionPerformed

    private void deleteTrekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTrekButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = trekDetailsTable.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a Trek","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Trek trek = (Trek) trekDetailsTable.getValueAt(selectedRow, 0);
        System.out.println("trek slot date: "+trekDetailsTable.getValueAt(selectedRow, 4));
        if(trekDetailsTable.getValueAt(selectedRow, 4).toString().equals("Slots not added")){
            System.out.println("inside noslots available");
            trekManagerOrganization.getTrekDirectory().getTrekArrayList().remove(trek);
            JOptionPane.showMessageDialog(null, "Trek deleted successfully");
        } else{
            for(TrekBooking trekBooking: trekManagerOrganization.getTrekBookingDirectory().getTrekBookingList()){
                if(trekBooking.getTrek() == trek){
                    JOptionPane.showMessageDialog(null, "Customers have booked this trek. Cannot be deleted");
                    return;
                }
            }
            TrekSlotAndCost trekSlotAndCost = (TrekSlotAndCost) trekDetailsTable.getValueAt(selectedRow, 4);
            WorkRequest request = (WorkRequest) trekDetailsTable.getValueAt(selectedRow, 5);
            request.setStatus("Trek deleted by user: "+account.getEmployee().getName());
            if(request instanceof AssignTrekLeaderWorkRequest){
                for(WorkRequest wq: trekManagerOrganization.getWorkQueue().getWorkRequestList()){
                if(wq instanceof AddTrekCostWorkRequest){
                    AddTrekCostWorkRequest add = (AddTrekCostWorkRequest) wq;
                    if(add.getTrek() == trek){
                        add.setStatus("Trek deleted by user: "+account.getEmployee().getName());
                        break;
                    }
                }
            }
            }
            trek.getTrekSlotAndCost().remove(trekSlotAndCost);
            JOptionPane.showMessageDialog(null, "Trek Slot deleted successfully");
        }
        populateTrekDetailsTable();
//        populateAddCostWorkRequestTable();
//        populateConfirmTrekLeaderWorkRequestTable();
//        try {
//            populatePublicUploadTable();
//        } catch (ParseException ex) {
//            Logger.getLogger(TrekManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_deleteTrekButtonActionPerformed

    private void sendRequestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendRequestButtonActionPerformed
        // TODO add your handling code here:
        Network network = null;
        Business.Enterprise.SportStoreEnterprise sportsStoreEnterprise = null;
        AskSportsStoreForTrendWorkRequest req = new AskSportsStoreForTrendWorkRequest();
        req.setSender(account);
        req.setStatus("Pending");
        try {
            req.setDateOfRequest(dateFormat.parse(dateFormat.format(new Date())));
        } catch (ParseException ex) {
            Logger.getLogger(TrekManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        trekManagerOrganization.getWorkQueue().getWorkRequestList().add(req);
        account.getWorkQueue().getWorkRequestList().add(req);
        
        for(Network n: business.getNetworkList()){
            int flag = 0;
          for(Enterprise e: n.getEnterpriseDirectory().getEnterpriseList()){
              if(e.getName().equals(enterprise.getName())){
                  network = n;
                  flag = 1;
                  break;
              }
          }
          if(flag == 1){
              break;
          }
        }
        if(network != null){
            for(Enterprise e: network.getEnterpriseDirectory().getEnterpriseList()){
                if(e.getEnterpriseType() == Enterprise.EnterpriseType.SportStore){
                    sportsStoreEnterprise = (SportStoreEnterprise) e;
                    break;
                }
            }
        }
        if(sportsStoreEnterprise != null){
            for(Organization org: sportsStoreEnterprise.getOrganizationDirectory().getOrganizationList()){
                if(org instanceof SportsStoreManagerOrganization){
                    SportsStoreManagerOrganization managerOrg = (SportsStoreManagerOrganization) org;
                    managerOrg.getWorkQueue().getWorkRequestList().add(req);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Request sent successfully");
        populateRequestTable();
        populateResponseTable();
    }//GEN-LAST:event_sendRequestButtonActionPerformed

    private void AddResponseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddResponseButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = responseTable.getSelectedRow();
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a request","Warning",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Object[] options = {"Fall","Summer","Winter"};
        JComboBox optionList = new JComboBox(options);
        optionList.setSelectedIndex(0);
        JOptionPane.showMessageDialog(null, optionList, "Select a Trending Weather",JOptionPane.QUESTION_MESSAGE);
        String response = optionList.getSelectedItem().toString();
        System.out.println("response is: "+response);
        AskTrekCompanyForTrendWorkRequest trekCompanyReq = (AskTrekCompanyForTrendWorkRequest) responseTable.getValueAt(selectedRow, 3);
        trekCompanyReq.setReceiver(account);
        trekCompanyReq.setStatus("Completed");
        trekCompanyReq.setComment(response);
        try {
            trekCompanyReq.setDateOfResponse(dateFormat.parse(dateFormat.format(new Date())));
        } catch (ParseException ex) {
            Logger.getLogger(TrekManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Response sent successfully");
        populateRequestTable();
        populateResponseTable();
    }//GEN-LAST:event_AddResponseButtonActionPerformed

    public int checkForValidations(String trekName, String trekLocation, String trekDesc, String trekBasicReq, String trekDuration, String trekItinerary, String trekWeather,
            String trekPickUpPoint, String trekBaseCamp, String trekMaxAltitude, String trekMinAge, String trekBestTimeToVisit, String trekFitnessReq, ArrayList<String> trekImagePath){
        
        if(trekName == null || trekName.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek Name cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if(validations.atleastOneLetterValidation(trekName)){
            JOptionPane.showMessageDialog(null, "Please enter a valid Trek name","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        for(Trek trek: trekManagerOrganization.getTrekDirectory().getTrekArrayList()){
            if(trek.getTrekName().equals(trekName)){
                JOptionPane.showMessageDialog(null, "Trek already exist","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
            }
        }
        
        if(trekLocation == null || trekLocation.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek Location cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if(validations.atleastOneLetterValidation(trekLocation)){
            JOptionPane.showMessageDialog(null, "Trek Location can only contain letters","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        if(trekDesc == null || trekDesc.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek Description cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        if(validations.atleastOneLetterValidation(trekDesc)){
                JOptionPane.showMessageDialog(null, "Please enter valid Trek Description","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
        }
        
        if(trekBasicReq == null || trekBasicReq.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek Basic Requirements cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        if(validations.atleastOneLetterValidation(trekBasicReq)){
                JOptionPane.showMessageDialog(null, "Please enter valid Basic Requirements","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
        }
        
        if(trekDuration == null || trekDuration.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek duration cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        try{
            if(Integer.parseInt(trekDuration) <= 0){
                JOptionPane.showMessageDialog(null, "Please enter a valid Trek duration","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter a valid Trek duration","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
        }
        
        if(trekItinerary == null || trekItinerary.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek Itinerary cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        if(validations.atleastOneLetterValidation(trekItinerary)){
                JOptionPane.showMessageDialog(null, "Please enter valid Trek Itinerary","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
        }
        
        if(trekWeather == null || trekWeather.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek Weather cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        if(!validations.onlyLettersValidation(trekWeather)){
            JOptionPane.showMessageDialog(null, "Trek weather can only contain letters","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        if(trekPickUpPoint == null || trekPickUpPoint.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek Pickup Point cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        if(validations.atleastOneLetterValidation(trekPickUpPoint)){
                JOptionPane.showMessageDialog(null, "Please enter valid Pickup Point","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
        }
        
        if(trekBaseCamp == null || trekBaseCamp.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek BaseCamp cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        if(validations.atleastOneLetterValidation(trekBaseCamp)){
                JOptionPane.showMessageDialog(null, "Please enter valid BaseCamp","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
        }
        
        if(trekMaxAltitude == null || trekMaxAltitude.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek Maximum Altitude cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        try{
            if(Integer.parseInt(trekMaxAltitude) <= 0){
                JOptionPane.showMessageDialog(null, "Please enter a valid Maximum Altitude","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter a valid Maximum Altitude","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
        }
        
        if(trekMinAge == null || trekMinAge.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek Minimum Age cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        try{
            if(Integer.parseInt(trekMinAge) <= 0){
                JOptionPane.showMessageDialog(null, "Please enter a valid Minimum Age","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter a valid Minimum Age","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
        }
        
        if(trekBestTimeToVisit == null || trekBestTimeToVisit.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek Best time to visit cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        if(validations.atleastOneLetterValidation(trekBestTimeToVisit)){
                JOptionPane.showMessageDialog(null, "Please enter valid Best time to visit","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
        }
        
        if(trekFitnessReq == null || trekFitnessReq.isEmpty()){
            JOptionPane.showMessageDialog(null, "Trek Fitness Requirement cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        
        if(validations.atleastOneLetterValidation(trekFitnessReq)){
                JOptionPane.showMessageDialog(null, "Please enter valid Trek Fitness Requirement","Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
        }
        
        if(trekImagePath.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please upload atleast one image","Warning",JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        return 1;
    }
    
    public void populateRequestTable(){
        DefaultTableModel model = (DefaultTableModel) requestTable.getModel();
        model.setRowCount(0);
        
        for(WorkRequest wq: trekManagerOrganization.getWorkQueue().getWorkRequestList()){
            if(wq instanceof AskSportsStoreForTrendWorkRequest){
                AskSportsStoreForTrendWorkRequest request = (AskSportsStoreForTrendWorkRequest) wq;
                Object[] row = new Object[6];
                row[0] = request.getSender();
                row[1] = request.getReceiver();
                row[2] = request.getComment();
                row[3] = request;
                row[4] = dateFormat.format(request.getDateOfRequest());
                if(request.getDateOfResponse()!=null){
                    row[5] = dateFormat.format(request.getDateOfResponse());
                }
                
                model.addRow(row);
            }
        }
    }
    
    public void populateResponseTable(){
        DefaultTableModel model = (DefaultTableModel) responseTable.getModel();
        model.setRowCount(0);
        
        for(WorkRequest wq: trekManagerOrganization.getWorkQueue().getWorkRequestList()){
            if(wq instanceof AskTrekCompanyForTrendWorkRequest){
                AskTrekCompanyForTrendWorkRequest request = (AskTrekCompanyForTrendWorkRequest) wq;
                Object[] row = new Object[6];
                row[0] = request.getSender();
                row[1] = request.getReceiver();
                row[2] = request.getComment();
                row[3] = request;
                row[4] = dateFormat.format(request.getDateOfRequest());
                if(request.getDateOfResponse() != null){
                row[5] = dateFormat.format(request.getDateOfResponse());
                }
                model.addRow(row);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddResponseButton;
    private javax.swing.JTable addCostWorkRequestTable;
    private javax.swing.JButton addTrekButton;
    private javax.swing.JPanel addTrekJPanel;
    private javax.swing.JTextField baseCampTxtField;
    private javax.swing.JTextArea basicReqTxtArea;
    private javax.swing.JTextField bestTimeToTrekTxtField;
    private javax.swing.JTable confirmTrekLeaderWorkRequestTable;
    private javax.swing.JButton deleteTrekButton;
    private javax.swing.JComboBox difficultyLevelComboBox;
    private javax.swing.JSpinner durationSpinner;
    private javax.swing.JButton editTrekButton;
    private javax.swing.JTextArea fitnessRequiredTxtArea;
    private javax.swing.JTextArea itineraryTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel label;
    private javax.swing.JSpinner maxAltitudeSpinner;
    private javax.swing.JSpinner minAgeSpinner;
    private javax.swing.JTextField pickUpPointTxtField;
    private javax.swing.JButton publicUploadButton;
    private javax.swing.JPanel publicUploadJPanel;
    private javax.swing.JTable publicUploadTable;
    private javax.swing.JTable requestTable;
    private javax.swing.JTable responseTable;
    private javax.swing.JButton sendRequestButton;
    private javax.swing.JButton slotButton;
    private org.jdesktop.swingx.JXDatePicker slotDatePicker;
    private javax.swing.JSpinner timeSpinner;
    private javax.swing.JTextField totalSeatsTxtField;
    private javax.swing.JTextField trekDescTxtField;
    private javax.swing.JTable trekDetailsTable;
    private javax.swing.JLabel trekImageNamesLabel;
    private javax.swing.JTextField trekLocTxtField;
    private javax.swing.JComboBox trekNameComboBox;
    private javax.swing.JTextField trekNameTxtField;
    private javax.swing.JLabel trekVideoNameLabel;
    private javax.swing.JButton uploadImageButton;
    private javax.swing.JButton uploadVideoButton;
    private javax.swing.JComboBox weatherComboBox;
    // End of variables declaration//GEN-END:variables
}

