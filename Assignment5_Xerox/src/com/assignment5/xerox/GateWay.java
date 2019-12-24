/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment5.xerox;

import com.assignment5.entities.Item;
import com.assignment5.entities.Product;
import com.assignment5.entities.Order;
import java.io.IOException;
import java.util.Map;
import javax.activation.DataSource;
import com.assignment5.xerox.AnalysisHelper;

/**
 *
 * @author kasai
 */
public class GateWay {
    
    
  
    DataReader orderReader;
    DataReader productCatReader;
    AnalysisHelper helper;
    
    public GateWay() throws IOException {
        DataGenerator generator = DataGenerator.getInstance();
        orderReader = new DataReader(generator.getOrderFilePath());
        productCatReader = new DataReader(generator.getProductCataloguePath());
        helper = new AnalysisHelper();
    }
    
    public static void main(String args[]) throws IOException{
        
        GateWay inst = new GateWay();
        inst.readData();

//        System.out.println("Order Map: ");
//        Map<Integer,Order> orders = DataStore.getInstance().getOrders();
//        for(Map.Entry order : orders.entrySet()){
//            System.out.println("Key: "+order.getKey()+" Value: "+order.getValue());
//        }
//        System.out.println("Item Map: ");
//        Map<Integer, Item> items = DataStore.getInstance().getItems();
//        for(Map.Entry item : items.entrySet()){
//            System.out.println("Key: "+item.getKey()+" Value: "+item.getValue());
//        }
//        System.out.println("Product Map: ");
//        Map<Integer, Product> products = DataStore.getInstance().getProducts();
//        for(Map.Entry product : products.entrySet()){
//            System.out.println("Key: "+product.getKey()+" Value: "+product.getValue());
//        }
    }
  
     private void readData() throws IOException{
        String[] row; 
        while((row = productCatReader.getNextRow()) != null ){
            generateProduct(row);
        }
        while((row = orderReader.getNextRow()) != null ){
            Item item = generateItem(row);
            generateOrder(row,item);
        }
        runAnalysis();
    }
     
    public void generateOrder(String[] row, Item item){
        int orderId = Integer.parseInt(row[0]);
        
        Order order =  new Order(orderId, Integer.parseInt(row[4]), Integer.parseInt(row[5]),item ,row[7]);
        DataStore.getInstance().getOrders().put(orderId, order);
    }
     
    public static void printRow(String[] row){
        for (String row1 : row) {
            System.out.print(row1 + ", ");
        }
        System.out.println("");
    }
    
    private void generateProduct(String[] row){
        // TODO
        int prodId = Integer.parseInt(row[0]);
        int min_price = Integer.parseInt(row[1]);
        int max_price = Integer.parseInt(row[2]);
        int target_price = Integer.parseInt(row[3]);
        Product prod = new Product(prodId, min_price, max_price, target_price);  
        DataStore.getInstance().getProducts().put(prodId, prod);
    }
    
   private Item generateItem(String[] row){
        // TODO
        int itemId = Integer.parseInt(row[1]); 
        int prodId = Integer.parseInt(row[2]);
        int salesPrice = Integer.parseInt(row[6]);
        int quantity = Integer.parseInt(row[3]);
        Item item = new Item(itemId, prodId, salesPrice, quantity);
        DataStore.getInstance().getItems().put(itemId, item);
        return item;
    }
  
   private void runAnalysis(){
       //code to run analysis helper methods
        helper.top3BestNegotiatedProducts();
        helper.top3Customers();
        helper.getTopThreeBestSalesPerson();
        helper.printOriginalTable();
        helper.printModifiedTable();
    }
}