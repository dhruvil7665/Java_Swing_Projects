/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.SportsStoreManagerRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.TrekCompanyEnterprise;
import Business.Network.Network;
import Business.Organization.CustomerOrganization;
import Business.Organization.Organization;
import Business.Organization.SportsStoreFinanceOrganization;
import Business.Organization.SportsStoreManagerOrganization;
import Business.Organization.TrekFinanceOrganization;
import Business.Organization.TrekLeaderOrganization;
import Business.Organization.TrekManagerOrganization;
import Business.Products.CustomerOrderDirectory;
import Business.Products.CustomerOrders;
import Business.Products.Product;
import Business.Trek.TrekBooking;
import Business.UserAccount.UserAccount;
import Business.Validations.Validations;
import Business.WorkQueue.AskSportsStoreForTrendWorkRequest;
import Business.WorkQueue.AskTrekCompanyForTrendWorkRequest;
import Business.WorkQueue.LabTestWorkRequest;
import Business.WorkQueue.ProductAddWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
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
public class SportsStoreManagerWorkAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private SportsStoreManagerOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    String filename;
    ProductAddWorkRequest lastRequest1; 
    Validations validations = new Validations();
    EcoSystem business;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Creates new form SportsStoreManagerWorkAreaJPanel
     */
    public SportsStoreManagerWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, SportsStoreManagerOrganization organization, Enterprise enterprise, EcoSystem business) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = account;
        this.business = business;
        populateRequestTable();
        populateProductTable();
        populatePublicTable();
        pieDiagram();
        populateRequestTableToTrek();
        populateResponseTableFromTrek();
        //System.out.println("org: "+organization);
        
          dashboard.getVerticalScrollBar().setUnitIncrement(16);
        
    }
    
    
    
    public void populateRequestTable(){
        DefaultTableModel model = (DefaultTableModel) productManagerRequestTable.getModel();
        
        model.setRowCount(0);
        String st = "Completed";
        for (WorkRequest request : userAccount.getWorkQueue().getWorkRequestList()){
            if(request instanceof ProductAddWorkRequest){
            if(!st.equals(((ProductAddWorkRequest) request).getApprovedStatus())){
            Object[] row = new Object[4];
            row[0] = request;
            row[1] = request.getSender();
            row[2] = request.getReceiver();
            String result = ((ProductAddWorkRequest) request).getApprovedStatus();
            row[3] = result == null ? "Waiting" : result;
            
            model.addRow(row);
            
            }
            }
        }
    }
    
    
    public void populatePublicTable(){
        DefaultTableModel model = (DefaultTableModel) approvedRequestTable.getModel();
        
        model.setRowCount(0);
        String st = "Completed";
        String st2 = "Uploaded";
        for (WorkRequest request : userAccount.getWorkQueue().getWorkRequestList()){
            if(request instanceof ProductAddWorkRequest){
            if(st.equals(((ProductAddWorkRequest) request).getApprovedStatus())&&!st2.equals(((ProductAddWorkRequest) request).getStatus())){
            Object[] row = new Object[4];
            row[0] = request;
            row[1] = request.getReceiver();
            row[2] = request.getStatus();
            String result = ((ProductAddWorkRequest) request).getApprovedStatus();
            row[3] = result == null ? "Waiting" : result;
            
            model.addRow(row);
        }
      }
    }
    }
    
    public void populateProductTable(){
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
        //System.out.println("fgfg"+organization.getProdDir().getProductList());
        model.setRowCount(0);
        for (Product prod : organization.getProdDir().getProductList()){
            //System.out.println("fgfg"+prod);
            Object[] row = new Object[7];
            row[0] = prod;
            row[1] = prod.getName();
            row[2] = prod.getDescription();
            row[3] = prod.getCostPrice();
            row[4] = prod.getSellingPrice();
            row[5] = prod.getCategory();
            row[6] = prod.getBrandName();
                    
            model.addRow(row);
        }
    }

    
     public void pieDiagram(){
        
        Map<String,Integer> categoryMap = categoriesWithTotalOrders();
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        for(Map.Entry<String,Integer> entry: categoryMap.entrySet()){
            dataset.setValue(entry.getKey(),entry.getValue());
        }
        JFreeChart chart = ChartFactory.createPieChart("Top 5 Categories based on No of Orders", dataset, true, true, true);
       // ChartFrame yourFrame = new ChartFrame("Your title", chart);
        ChartPanel yourFrame = new ChartPanel(chart);
        //yourFrame.setPreferredSize(new Dimension(500,100));
        dashboardJPanel.add(yourFrame);
        dashboardJPanel.validate();
        
        
        Map<String,Integer> top5CustomersMap = top5Customers();
        DefaultPieDataset dataset1 = new DefaultPieDataset();
        int i =0;
         for(Map.Entry<String,Integer> entry: top5CustomersMap.entrySet()){
             if(i<=5){
            dataset1.setValue(entry.getKey(),entry.getValue());
             }
        }
//        dataset1.setValue("Value 1", new Integer(10));
//        dataset1.setValue("Value 2", new Integer(20));
//        dataset1.setValue("Value 3", new Integer(30));
//        dataset1.setValue("Value 4", new Integer(40));
        JFreeChart chart1 = ChartFactory.createPieChart("Top 5 Customers", dataset1, true, true, true);
       // ChartFrame yourFrame = new ChartFrame("Your title", chart);
        ChartPanel yourFrame1 = new ChartPanel(chart1);
        //yourFrame1.setSize(new Dimension(100,100));
        dashboardJPanel.add(yourFrame1);
        dashboardJPanel.validate();
        
        Map<String,Integer> top5ProductsMap = top5Products();
        DefaultPieDataset dataset2 = new DefaultPieDataset();
         i =0;
         for(Map.Entry<String,Integer> entry: top5ProductsMap.entrySet()){
             if(i<=5){
            dataset2.setValue(entry.getKey(),entry.getValue());
             }
        }
//        dataset2.setValue("Value 1", new Integer(10));
//        dataset2.setValue("Value 2", new Integer(20));
//        dataset2.setValue("Value 3", new Integer(30));
//        dataset2.setValue("Value 4", new Integer(40));
        JFreeChart chart2 = ChartFactory.createPieChart("Top 5 Products", dataset2, true, true, true);
       // ChartFrame yourFrame = new ChartFrame("Your title", chart);
        ChartPanel yourFrame2 = new ChartPanel(chart2);
        //yourFrame2.setPreferredSize(new Dimension(500,100));
        dashboardJPanel.add(yourFrame2);
        dashboardJPanel.validate();
        
        //TOP Brands
        
         Map<String,Integer> topBrandsMap = top5Brands();
         DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
         i =0;
         for(Map.Entry<String,Integer> entry: topBrandsMap.entrySet()){
             if(i<=5){
            dataset3.setValue(entry.getValue(),entry.getKey(),entry.getKey());
             }
        }
//        dataset2.setValue("Value 1", new Integer(10));
//        dataset2.setValue("Value 2", new Integer(20));
//        dataset2.setValue("Value 3", new Integer(30));
//        dataset2.setValue("Value 4", new Integer(40));
        JFreeChart chart3 = ChartFactory.createBarChart("Top 5 Products","Brands","No.of Orders", dataset3,PlotOrientation.VERTICAL, true, true, true);
       // ChartFrame yourFrame = new ChartFrame("Your title", chart);
        ChartPanel yourFrame3 = new ChartPanel(chart3);
        //yourFrame2.setPreferredSize(new Dimension(500,100));
        dashboardJPanel.add(yourFrame3);
        dashboardJPanel.validate();
    }
     
     public Map<String,Integer> categoriesWithTotalOrders(){
        
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
                countMap.put(orders.getProduct().getCategory(),countMap.get(orders.getProduct().getCategory())+1);
            }
            else{
                countMap.put(orders.getProduct().getCategory(),1);
            }
        }
        return countMap;
     }
     
      public Map<String,Integer> top5Customers(){
        
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
           
            if(countMap.containsKey(orders.getCustAccount().toString())){
                int cnt = countMap.get(orders.getCustAccount().toString());
                countMap.put(orders.getCustAccount().toString(),countMap.get(orders.getCustAccount().toString())+1);
            }
            else{
                 countMap.put(orders.getCustAccount().toString(),1);
            }
        }
       
        Map<String,Integer> sortedDifferenceMap = sortByComparator(countMap, false);  
        return sortedDifferenceMap;
     }
      
      
       public Map<String,Integer> top5Products(){
        
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
          //  System.out.println("cut"+orders.getProduct().));
            if(countMap.containsKey(orders.getProduct().getName())){
                int cnt = countMap.get(orders.getProduct().getName());
                countMap.put(orders.getProduct().getName(),countMap.get(orders.getProduct().getName())+1);
            }
            else{
                 countMap.put(orders.getProduct().getName(),1);
            }
        }
       
        Map<String,Integer> sortedDifferenceMap = sortByComparator(countMap, false);  
        return sortedDifferenceMap;
     }
      
       
        public Map<String,Integer> top5Brands(){
        
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
          //  System.out.println("cut"+orders.getProduct().));
            if(countMap.containsKey(orders.getProduct().getBrandName())){
                int cnt = countMap.get(orders.getProduct().getBrandName());
                countMap.put(orders.getProduct().getBrandName(),countMap.get(orders.getProduct().getBrandName())+1);
            }
            else{
                 countMap.put(orders.getProduct().getBrandName(),1);
            }
        }
       
        Map<String,Integer> sortedDifferenceMap = sortByComparator(countMap, false);  
        return sortedDifferenceMap;
     }
       
       
        private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String , Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        AddProductJPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nameTxtField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CategoryJComboBox = new javax.swing.JComboBox();
        SubCategory = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        costPriceTxtField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descTxtArea = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        uploadImageButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        brandNameTxtField = new javax.swing.JTextField();
        requestSellPriceBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        pathName = new javax.swing.JLabel();
        CategoryJComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        initQtyText = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        weatherCombo = new javax.swing.JComboBox();
        ViewProductsJPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        productsTable = new javax.swing.JTable();
        viewDetailsButton = new javax.swing.JButton();
        dashboard = new javax.swing.JScrollPane();
        dashboardJPanel = new javax.swing.JPanel();
        FinanceManagerRequestJPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        productManagerRequestTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        approvedRequestTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        requestToTrekCompany = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        sendRequestButton = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        responseTable = new javax.swing.JTable();
        AddResponseButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(33, 108, 205));

        jTabbedPane1.setBackground(new java.awt.Color(33, 108, 205));
        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 255));
        jTabbedPane1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        AddProductJPanel.setBackground(new java.awt.Color(33, 108, 205));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add a new Product");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name");

        nameTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Category");

        CategoryJComboBox.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        CategoryJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cycling", "Excersice and Fitness", "Backpacking", "Camping", "Hiking", "Running", "Winter Sports" }));
        CategoryJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CategoryJComboBoxActionPerformed(evt);
            }
        });

        SubCategory.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        SubCategory.setForeground(new java.awt.Color(255, 255, 255));
        SubCategory.setText("SubCategory");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CostPrice");

        costPriceTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Description");

        descTxtArea.setColumns(20);
        descTxtArea.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        descTxtArea.setRows(5);
        jScrollPane1.setViewportView(descTxtArea);

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Product Image");

        uploadImageButton.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        uploadImageButton.setText("Upload Image");
        uploadImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadImageButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Brand Name");

        brandNameTxtField.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        requestSellPriceBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/SportsStoreManagerRole/managerRequest.png"))); // NOI18N
        requestSellPriceBtn.setContentAreaFilled(false);
        requestSellPriceBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        requestSellPriceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestSellPriceBtnActionPerformed2(evt);
            }
        });

        CategoryJComboBox1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        CategoryJComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Shoes", "Apparel", "Equipments", "Accessories" }));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Initial Quanity");

        initQtyText.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Weather");

        weatherCombo.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        weatherCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Winter", "Fall", "Summer" }));

        javax.swing.GroupLayout AddProductJPanelLayout = new javax.swing.GroupLayout(AddProductJPanel);
        AddProductJPanel.setLayout(AddProductJPanelLayout);
        AddProductJPanelLayout.setHorizontalGroup(
            AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProductJPanelLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddProductJPanelLayout.createSequentialGroup()
                        .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddProductJPanelLayout.createSequentialGroup()
                                .addComponent(uploadImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pathName, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(brandNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddProductJPanelLayout.createSequentialGroup()
                        .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SubCategory)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(22, 22, 22)
                        .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(costPriceTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(CategoryJComboBox1, 0, 400, Short.MAX_VALUE)
                            .addComponent(CategoryJComboBox, 0, 400, Short.MAX_VALUE)
                            .addComponent(nameTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addGroup(AddProductJPanelLayout.createSequentialGroup()
                        .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(initQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weatherCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel12)
                    .addGroup(AddProductJPanelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddProductJPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(requestSellPriceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        AddProductJPanelLayout.setVerticalGroup(
            AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProductJPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CategoryJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubCategory)
                    .addComponent(CategoryJComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(costPriceTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pathName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel11))
                    .addComponent(uploadImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(brandNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(initQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddProductJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(weatherCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(requestSellPriceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Add a Product", AddProductJPanel);

        ViewProductsJPanel.setBackground(new java.awt.Color(33, 108, 205));

        productsTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        productsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prod ID", "Prod Name", "Description", "Cost Price", "Selling Price", "Category", "Brand Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productsTable.setRowHeight(22);
        jScrollPane3.setViewportView(productsTable);

        viewDetailsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/TrekManagerRole/updateButton.png"))); // NOI18N
        viewDetailsButton.setContentAreaFilled(false);
        viewDetailsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ViewProductsJPanelLayout = new javax.swing.GroupLayout(ViewProductsJPanel);
        ViewProductsJPanel.setLayout(ViewProductsJPanelLayout);
        ViewProductsJPanelLayout.setHorizontalGroup(
            ViewProductsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewProductsJPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(ViewProductsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewDetailsButton)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(296, Short.MAX_VALUE))
        );
        ViewProductsJPanelLayout.setVerticalGroup(
            ViewProductsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewProductsJPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(viewDetailsButton)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View/Edit A Product", ViewProductsJPanel);

        dashboard.setBackground(new java.awt.Color(33, 108, 205));

        dashboardJPanel.setBackground(new java.awt.Color(33, 108, 205));
        dashboardJPanel.setLayout(new java.awt.GridLayout(0, 2, 10, 10));
        dashboard.setViewportView(dashboardJPanel);

        jTabbedPane1.addTab("DashBoard", dashboard);

        FinanceManagerRequestJPanel.setBackground(new java.awt.Color(33, 108, 205));
        FinanceManagerRequestJPanel.setForeground(new java.awt.Color(153, 153, 255));

        productManagerRequestTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        productManagerRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status of Product Request", "Sender", "Receiver", "Cost Assign Request Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productManagerRequestTable.setRowHeight(22);
        jScrollPane2.setViewportView(productManagerRequestTable);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/TrekManagerRole/publicUploadButton.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        approvedRequestTable.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        approvedRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status of Product Request", "Sender", "Receiver", "Cost Assign Request Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        approvedRequestTable.setRowHeight(22);
        jScrollPane4.setViewportView(approvedRequestTable);

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Request Completed by Finance Manager");

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Requests for Assigning Selling Price");

        javax.swing.GroupLayout FinanceManagerRequestJPanelLayout = new javax.swing.GroupLayout(FinanceManagerRequestJPanel);
        FinanceManagerRequestJPanel.setLayout(FinanceManagerRequestJPanelLayout);
        FinanceManagerRequestJPanelLayout.setHorizontalGroup(
            FinanceManagerRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(FinanceManagerRequestJPanelLayout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addGroup(FinanceManagerRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FinanceManagerRequestJPanelLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(635, 635, 635))
                    .addGroup(FinanceManagerRequestJPanelLayout.createSequentialGroup()
                        .addGroup(FinanceManagerRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        FinanceManagerRequestJPanelLayout.setVerticalGroup(
            FinanceManagerRequestJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinanceManagerRequestJPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton2)
                .addGap(49, 49, 49))
        );

        jTabbedPane1.addTab("Request to Finance Manager", FinanceManagerRequestJPanel);

        requestToTrekCompany.setBackground(new java.awt.Color(33, 108, 205));

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
        requestTable.setRowHeight(22);
        jScrollPane7.setViewportView(requestTable);

        jLabel22.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("List of Requests sent to Trek Company asking for Trending Trek Weather");

        sendRequestButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/SportsStoreManagerRole/sendRequestToTrek.png"))); // NOI18N
        sendRequestButton.setContentAreaFilled(false);
        sendRequestButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sendRequestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendRequestButtonActionPerformed(evt);
            }
        });

        label.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setText("List of Requests send by the Trek Company asking for Suitable Weather for Trending Gears");

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
        responseTable.setRowHeight(22);
        jScrollPane10.setViewportView(responseTable);

        AddResponseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UserInterface/SportsStoreManagerRole/responseButton.png"))); // NOI18N
        AddResponseButton.setContentAreaFilled(false);
        AddResponseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddResponseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout requestToTrekCompanyLayout = new javax.swing.GroupLayout(requestToTrekCompany);
        requestToTrekCompany.setLayout(requestToTrekCompanyLayout);
        requestToTrekCompanyLayout.setHorizontalGroup(
            requestToTrekCompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestToTrekCompanyLayout.createSequentialGroup()
                .addGroup(requestToTrekCompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(requestToTrekCompanyLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(requestToTrekCompanyLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(sendRequestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(requestToTrekCompanyLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(requestToTrekCompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(requestToTrekCompanyLayout.createSequentialGroup()
                        .addGap(770, 770, 770)
                        .addComponent(AddResponseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
        requestToTrekCompanyLayout.setVerticalGroup(
            requestToTrekCompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestToTrekCompanyLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(requestToTrekCompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendRequestButton)
                    .addComponent(jLabel22))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(label)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AddResponseButton)
                .addGap(178, 178, 178))
        );

        jTabbedPane1.addTab("Request to Trek Company", requestToTrekCompany);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void requestSellPriceBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
      
    }                                                   


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int selectedRow = approvedRequestTable.getSelectedRow();
        
        if (selectedRow < 0){
            return;
        }
         
        ProductAddWorkRequest request = (ProductAddWorkRequest)approvedRequestTable.getValueAt(selectedRow, 0);
        Product p = new Product();
        p.setName(request.getName());
        p.setCostPrice(request.getCostPrice());
        p.setCategory(request.getCategory());
        p.setDescription(request.getDescription());
        p.setBrandName(request.getBrandName());
        p.setSellingPrice(request.getSellingPrice());
        p.setImagePath(request.getImagePath());
        p.setInitialQuantity(request.getInitialQuantity());
        p.setSubCategory(request.getSubCategory());
        p.setRemainingQuantity(request.getRemainingQuantity());
        p.setUniqueId(request.getUniqueId());
        p.setSuitableWeather(request.getSuitableWeather());
        
        
      //  System.out.println("first"+organization.getProdDir());
//      System.out.println("second"+organization.getProdDir().getProductList());
        organization.getProdDir().getProductList().add(p);
       // System.out.println("ghgh"+organization.getProdDir().getProductList());
        request.setStatus("Uploaded");
        
        populateProductTable();
        populatePublicTable();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void uploadImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadImageButtonActionPerformed
        // TODO add your handling code here:
         JFileChooser choose = new JFileChooser();
        choose.setDialogTitle("Please Select an Image of the Product");
        choose.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filt = new FileNameExtensionFilter("Image Files ONLY","PNG","JPG","JPEG");
        choose.addChoosableFileFilter(filt);
        int r=choose.showOpenDialog(null);
        File f=choose.getSelectedFile();
        
        if ( r == JFileChooser.APPROVE_OPTION){
           //filename=f.getAbsolutePath();
           filename=f.getPath();
        }else
            JOptionPane.showMessageDialog(null,"Please select a File to Upload","Error",JOptionPane.INFORMATION_MESSAGE);
        
       pathName.setText(choose.getSelectedFile().getName());
    }//GEN-LAST:event_uploadImageButtonActionPerformed

    private void viewDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailsButtonActionPerformed
        // TODO add your handling code here:
        
         int selectedRows = productsTable.getSelectedRow();
        
        if(selectedRows < 0){
            JOptionPane.showMessageDialog(null, "Please select a Product","Warning", JOptionPane.WARNING_MESSAGE);
        } else{
            Product prod = (Product)productsTable.getValueAt(selectedRows,0);
            ViewProductJPanel viewPanel = new ViewProductJPanel(userProcessContainer, userAccount, organization,enterprise,prod,organization.getProdDir());
            userProcessContainer.add("ViewPanel",viewPanel);
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
    }//GEN-LAST:event_viewDetailsButtonActionPerformed

    private void CategoryJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategoryJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CategoryJComboBoxActionPerformed


    private void sendRequestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendRequestButtonActionPerformed
        // TODO add your handling code here:
        Network network = null;
        Business.Enterprise.TrekCompanyEnterprise trekCompanyEnterprise = null;
        AskTrekCompanyForTrendWorkRequest req = new AskTrekCompanyForTrendWorkRequest();
        req.setSender(userAccount);
        req.setStatus("Pending");
        try {
            req.setDateOfRequest(dateFormat.parse(dateFormat.format(new Date())));
        } catch (ParseException ex) {
            Logger.getLogger(SportsStoreManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        organization.getWorkQueue().getWorkRequestList().add(req);
        userAccount.getWorkQueue().getWorkRequestList().add(req);

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
                if(e.getEnterpriseType() == Enterprise.EnterpriseType.TrekCompany){
                    trekCompanyEnterprise = (TrekCompanyEnterprise) e;
                    break;
                }
            }
        }
        if(trekCompanyEnterprise != null){
            for(Organization org: trekCompanyEnterprise.getOrganizationDirectory().getOrganizationList()){
                if(org instanceof TrekManagerOrganization){
                    TrekManagerOrganization managerOrg = (TrekManagerOrganization) org;
                    managerOrg.getWorkQueue().getWorkRequestList().add(req);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Request sent successfully");
        populateRequestTableToTrek();
        populateResponseTableFromTrek();
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
        AskSportsStoreForTrendWorkRequest sportsStoreReq = (AskSportsStoreForTrendWorkRequest) responseTable.getValueAt(selectedRow, 3);
        sportsStoreReq.setReceiver(userAccount);
        sportsStoreReq.setStatus("Completed");
        sportsStoreReq.setComment(response);
        try {
            sportsStoreReq.setDateOfResponse(dateFormat.parse(dateFormat.format(new Date())));
        } catch (ParseException ex) {
            Logger.getLogger(SportsStoreManagerWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Response sent successfully");
         populateRequestTableToTrek();
        populateResponseTableFromTrek();
    }//GEN-LAST:event_AddResponseButtonActionPerformed

    public void populateRequestTableToTrek(){
        DefaultTableModel model = (DefaultTableModel) requestTable.getModel();
        model.setRowCount(0);
        
        for(WorkRequest wq: organization.getWorkQueue().getWorkRequestList()){
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
    
    public void populateResponseTableFromTrek(){
        DefaultTableModel model = (DefaultTableModel) responseTable.getModel();
        model.setRowCount(0);
        
        for(WorkRequest wq: organization.getWorkQueue().getWorkRequestList()){
            if(wq instanceof AskSportsStoreForTrendWorkRequest){
                AskSportsStoreForTrendWorkRequest request = (AskSportsStoreForTrendWorkRequest) wq;
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

    private void requestSellPriceBtnActionPerformed2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestSellPriceBtnActionPerformed2
        // TODO add your handling code here:
  try{
        if( nameTxtField.getText() == null || nameTxtField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Product Name cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(validations.atleastOneLetterValidation(nameTxtField.getText())){
            JOptionPane.showMessageDialog(null, "Please enter a valid Product Name","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(CategoryJComboBox.getSelectedItem() == null || CategoryJComboBox.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please select a Category","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(CategoryJComboBox1.getSelectedItem() == null || CategoryJComboBox1.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please select a Sub Category","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(costPriceTxtField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Cost Price cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(Float.parseFloat(costPriceTxtField.getText()) < 0){
            JOptionPane.showMessageDialog(null, "Please enter a valid Cost Price","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(descTxtArea.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Product Description cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(brandNameTxtField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Brand Name cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(!validations.onlyLettersValidation(brandNameTxtField.getText())){
            JOptionPane.showMessageDialog(null, "Brand Name can only contain letters","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(initQtyText.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Initial Quantity cannot be empty","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(Integer.parseInt(initQtyText.getText()) < 0){
            JOptionPane.showMessageDialog(null, "Please enter a valid Initial Quantity","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(weatherCombo.getSelectedItem() == null || weatherCombo.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please select suitable Weather for the product","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(filename == null || filename.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please select an Image for the product","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ProductAddWorkRequest request = new ProductAddWorkRequest();
        ProductAddWorkRequest lastRequest;        
        String name = nameTxtField.getText();
        String category = CategoryJComboBox.getSelectedItem().toString();
        String subCategory = CategoryJComboBox1.getSelectedItem().toString();
        float costPrice = Float.parseFloat(costPriceTxtField.getText());
        String Desc = descTxtArea.getText();
        String brandName = brandNameTxtField.getText();
        int initQty = Integer.parseInt(initQtyText.getText());
        
          Organization org = null;
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()){
            if (organization instanceof SportsStoreFinanceOrganization){
                org = organization;
                break;
            }
        }
        
        if(org.getWorkQueue().getWorkRequestList().size()!=0){
        lastRequest = (ProductAddWorkRequest)org.getWorkQueue().getWorkRequestList().get(org.getWorkQueue().getWorkRequestList().size() - 1);
            System.out.println(lastRequest);
        }else{
        lastRequest=new ProductAddWorkRequest();
        }
        
        
        request.setMessage("product:"+name);
        request.setApprovedStatus("pending");
        request.setCostPrice(costPrice);
        request.setDescription(Desc);
        request.setName(name);
        request.setBrandName(brandName);
        request.setCategory(category);
        request.setSubCategory(subCategory);
        request.setInitialQuantity(initQty);
        request.setRemainingQuantity(initQty);
        request.setSender(userAccount);
        request.setStatus("Sent");
        request.setImagePath(filename);
        request.setSuitableWeather(weatherCombo.getSelectedItem().toString());
        if(lastRequest.equals(null)){
        request.setUniqueId(1);
            System.out.println("inside if"+request.getUniqueId());
        }else{
            System.out.println("inside else"+lastRequest.getUniqueId());
        request.setUniqueId(lastRequest.getUniqueId()+1);
        }
      
        if (org!=null){
            org.getWorkQueue().getWorkRequestList().add(request);
            userAccount.getWorkQueue().getWorkRequestList().add(request);
        }
        JOptionPane.showMessageDialog(null,"Product added Successfully","Information",JOptionPane.INFORMATION_MESSAGE);
        
        nameTxtField.setText("");
        CategoryJComboBox.setSelectedIndex(0);
        CategoryJComboBox1.setSelectedIndex(0);
        descTxtArea.setText("");
        costPriceTxtField.setText("");
        filename = "";
        brandNameTxtField.setText("");
        initQtyText.setText("");
        weatherCombo.setSelectedIndex(0);
        pathName.setText("");
        populateRequestTable();
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Please enter a valid Number","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_requestSellPriceBtnActionPerformed2



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddProductJPanel;
    private javax.swing.JButton AddResponseButton;
    private javax.swing.JComboBox CategoryJComboBox;
    private javax.swing.JComboBox CategoryJComboBox1;
    private javax.swing.JPanel FinanceManagerRequestJPanel;
    private javax.swing.JLabel SubCategory;
    private javax.swing.JPanel ViewProductsJPanel;
    private javax.swing.JTable approvedRequestTable;
    private javax.swing.JTextField brandNameTxtField;
    private javax.swing.JTextField costPriceTxtField;
    private javax.swing.JScrollPane dashboard;
    private javax.swing.JPanel dashboardJPanel;
    private javax.swing.JTextArea descTxtArea;
    private javax.swing.JTextField initQtyText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel label;
    private javax.swing.JTextField nameTxtField;
    private javax.swing.JLabel pathName;
    private javax.swing.JTable productManagerRequestTable;
    private javax.swing.JTable productsTable;
    private javax.swing.JButton requestSellPriceBtn;
    private javax.swing.JTable requestTable;
    private javax.swing.JPanel requestToTrekCompany;
    private javax.swing.JTable responseTable;
    private javax.swing.JButton sendRequestButton;
    private javax.swing.JButton uploadImageButton;
    private javax.swing.JButton viewDetailsButton;
    private javax.swing.JComboBox weatherCombo;
    // End of variables declaration//GEN-END:variables
}
