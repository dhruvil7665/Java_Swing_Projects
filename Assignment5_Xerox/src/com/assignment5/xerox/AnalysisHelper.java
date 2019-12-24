/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment5.xerox;

import com.assignment5.entities.Item;
import com.assignment5.entities.Order;
import com.assignment5.entities.Product;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author shahd
 */
public class AnalysisHelper {
    
    //method for generating top 3 negotiated products based on the products sold above target price
     public void top3BestNegotiatedProducts(){
        System.out.println("---------------------------top 3 Best Negotiated products--------------------------");
        Map<Integer,Integer> countMap =  new HashMap<>();
        Map<Integer, Product> prod = DataStore.getInstance().getProducts();
        Map<Integer, Item> item = DataStore.getInstance().getItems();
        
       int qty =0;
       
       
       for(Item i : item.values()){
          Product p = prod.get(i.getProductId());
               if(countMap.containsKey(p.getProductId())){
                   if(i.getSalesPrice() >= p.getTarget_price()){
                       qty = countMap.get(i.getProductId());
                       countMap.put(i.getProductId(),qty + i.getQuantity());
                   }
               }
               else{
                   if(i.getSalesPrice() >= p.getTarget_price()){
                       countMap.put(p.getProductId(),i.getQuantity());
                   }
                }   
       }
        Map<Integer, Integer> sortedMapDesc = sortByComparator(countMap, false);    
        int i=0;
        for(Map.Entry<Integer, Integer> en : sortedMapDesc.entrySet()) { 
            if(i==3){
            break;
            }else{
             System.out.println("Product = " + en.getKey()+" with Sale Quanity: "+en.getValue());
             i++;
            }
         }
        
     }
     
     //Method to get top three sales person based on the total profit generated
     public void getTopThreeBestSalesPerson(){
         System.out.println("---------------------------Top 3 Best Sales Person--------------------------");
         Map<Integer,Order> orders = DataStore.getInstance().getOrders();
         Map<Integer,Product> products = DataStore.getInstance().getProducts();
         Map<Integer,Integer> profitForSalesPerson = new HashMap<>();
         for(Order order : orders.values()){
             Product product = products.get(order.getItem().getProductId());
             int profit = (order.getItem().getSalesPrice() - product.getTarget_price())*order.getItem().getQuantity();
             if(profitForSalesPerson.containsKey(order.getSalesId())){
                 profit = profit + profitForSalesPerson.get(order.getSalesId());
                 profitForSalesPerson.put(order.getSalesId(),profit);
             }else{
                 profitForSalesPerson.put(order.getSalesId(),profit);
             }
         }
         Map<Integer,Integer> sortedProfitForSalesPerson = sortByComparator(profitForSalesPerson, false);
         int i=0;
        for(Map.Entry<Integer, Integer> en : sortedProfitForSalesPerson.entrySet()) { 
            if(i==3){
            break;
            }else{
             System.out.println("SalesPerson = " + en.getKey()+" with Profit: "+en.getValue());
             i++;
            }
         }
         System.out.println("---------------------------Total Revenue for the Year--------------------------");
         int totalRevenue = 0;
         for(Integer p : sortedProfitForSalesPerson.values()){
             totalRevenue = totalRevenue + p;
         }
         System.out.println("The total revenue for the year is: "+totalRevenue);
         System.out.println("");
     }
     
     //method to display original data in tabular form in the output console
     public void printOriginalTable(){
         DecimalFormat df = new DecimalFormat("0.00");
         System.out.println("Original Table with Product Id, Sales Price, Target Price and difference between Sales Price and Target Price");
         System.out.println("-----------------------------------------------------------------------------------------------------------");
         System.out.printf("%16s %16s %16s %30s", "|PRODUCT ID|", "|AVG SALES PRICE|", "|TARGET PRICE|", "|DIFFERENCE BETWEEN SALES PRICE AND TARGET PRICE|");
         System.out.println("");
         System.out.println("-----------------------------------------------------------------------------------------------------------");
         
         Map<Integer,Float> avgSalesPrice = new HashMap<>();
         Map<Integer,Item> items = DataStore.getInstance().getItems();
         Map<Integer,Product> products = DataStore.getInstance().getProducts();
         Map<Integer,Float> differenceMap = new HashMap<>();
         
         for(Product product : products.values()){
            int sumQuantitySalePrice = 0;
            int sumQuantity = 0;
            int cnt = 0;
            for(Item item : items.values()){
                 if(item.getProductId() == product.getProductId()){
                    sumQuantitySalePrice = sumQuantitySalePrice + (item.getQuantity()*item.getSalesPrice());
                    sumQuantity = sumQuantity + item.getQuantity();
                }    
            }
            avgSalesPrice.put(product.getProductId(),(float)sumQuantitySalePrice/sumQuantity);
            differenceMap.put(product.getProductId(),((float)sumQuantitySalePrice/sumQuantity) - product.getTarget_price());
         }
         
         Map<Integer,Float> sortedDifferenceMap = sortByComparatorFloat(differenceMap, false);
         for(Map.Entry<Integer,Float> en : sortedDifferenceMap.entrySet()){
             for(Product product : products.values()){
                if(product.getProductId() == en.getKey()){
                    int productId = product.getProductId();
                    float salesPrice = avgSalesPrice.get(product.getProductId());
                    int targetPrice = product.getTarget_price();
                    if(salesPrice <= targetPrice){
                       System.out.printf("%16s %16s %16s %16s",productId,df.format(salesPrice),targetPrice,df.format(salesPrice-targetPrice));
                       System.out.println("");
                    }
                }
            }
         }
         System.out.println("-----------------------------------------------------------------------------------------------------------");
         for(Map.Entry<Integer,Float> en : sortedDifferenceMap.entrySet()){
         for(Product product : products.values()){
             if(product.getProductId() == en.getKey()){
             int productId = product.getProductId();
             float salesPrice = avgSalesPrice.get(product.getProductId());
             int targetPrice = product.getTarget_price();
             if(salesPrice > targetPrice){
                System.out.printf("%16s %16s %16s %16s",productId,df.format(salesPrice),targetPrice,df.format(salesPrice-targetPrice));
                System.out.println("");
             }
         }
         }
     }
         System.out.println("-----------------------------------------------------------------------------------------------------------");
     } 
     
     //method to display Modified data after adjusting target price in tabular form in the output console
     public void printModifiedTable(){
         DecimalFormat df = new DecimalFormat("0.00");
         System.out.println("Modified Table with Product Id, Sales Price, Target Price, difference between Sales Price and Target Price, Adjusted Target Price and error");
         System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
         System.out.printf("%16s %16s %16s %26s %16s", "|PRODUCT ID|", "|AVG SALES PRICE|", "|MODIFIED TARGET PRICE|", "|DIFFERENCE BETWEEN SALES PRICE AND TARGET PRICE|", "|ERROR IN PRICING|");
         System.out.println("");
         System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
         Map<Integer,Float> avgSalesPrice = new HashMap<>();
         Map<Integer,Item> items = DataStore.getInstance().getItems();
         Map<Integer,Product> products = DataStore.getInstance().getProducts();
         Map<Integer,Float> differenceMap = new HashMap<>();
         
         for(Product product : products.values()){
            int sumQuantitySalePrice = 0;
            int sumQuantity = 0;
            int cnt = 0;
            for(Item item : items.values()){
                 if(item.getProductId() == product.getProductId()){
                    sumQuantitySalePrice = sumQuantitySalePrice + (item.getQuantity()*item.getSalesPrice());
                    sumQuantity = sumQuantity + item.getQuantity();
                }    
            }
            avgSalesPrice.put(product.getProductId(),(float)sumQuantitySalePrice/sumQuantity);
            differenceMap.put(product.getProductId(),((float)sumQuantitySalePrice/sumQuantity) - product.getTarget_price());
         }
         
         Map<Integer,Float> adjustedTargetPrice = new HashMap<>();
         float adjustedTPrice=0;
         for(Product p : products.values()){
             if(p.getTarget_price()<avgSalesPrice.get(p.getProductId())-(5*avgSalesPrice.get(p.getProductId()))/100){
                 
                 adjustedTPrice=avgSalesPrice.get(p.getProductId())-(5*avgSalesPrice.get(p.getProductId()))/100;
                
         }else if(p.getTarget_price()>avgSalesPrice.get(p.getProductId())+(5*avgSalesPrice.get(p.getProductId()))/100){
             
                adjustedTPrice=avgSalesPrice.get(p.getProductId())+(5*avgSalesPrice.get(p.getProductId()))/100;
           
         }else{
          adjustedTPrice=avgSalesPrice.get(p.getProductId());
         
         }
             adjustedTargetPrice.put(p.getProductId(), adjustedTPrice);
             
         }
         Map<Integer,Float> sortedDifferenceMap = sortByComparatorFloat(differenceMap, false);
         for(Map.Entry<Integer,Float> en : sortedDifferenceMap.entrySet()){
            for(Product product : products.values()){
                if(product.getProductId() == en.getKey()){
                    int productId = product.getProductId();
                    float salesPrice = avgSalesPrice.get(product.getProductId());
                    int targetPrice = product.getTarget_price();
                    float adjustedPricePrint = adjustedTargetPrice.get(product.getProductId());
                    if(salesPrice <= targetPrice){
                       System.out.printf("%16s %16s %16s %34s %36s",productId,df.format(salesPrice),df.format(adjustedPricePrint),df.format(salesPrice-targetPrice),df.format(100*((adjustedPricePrint-salesPrice)/salesPrice))+"%");
                       System.out.println("");
                    }
                }
            }
         }
         System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
         for(Map.Entry<Integer,Float> en : sortedDifferenceMap.entrySet()){
            for(Product product : products.values()){
                if(product.getProductId() == en.getKey()){
                    int productId = product.getProductId();
                    float salesPrice = avgSalesPrice.get(product.getProductId());
                    int targetPrice = product.getTarget_price();
                    float adjustedPricePrint = adjustedTargetPrice.get(product.getProductId());
                    if(salesPrice > targetPrice){
                       System.out.printf("%16s %16s %16s %34s %36s",productId,df.format(salesPrice),df.format(adjustedPricePrint),df.format(salesPrice-targetPrice),df.format(100*((adjustedPricePrint-salesPrice)/salesPrice))+"%");
                       System.out.println("");
                    }
                }
            }
         }
         System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
     } 
    
     //method to get the top three customers who buy close to the target price
     public void top3Customers(){
         
         System.out.println("---------------------------top 3 Best Customers------------------------------------");
         Map<Integer,Integer> countMap =  new HashMap<>();
         Map<Integer, Product> prod = DataStore.getInstance().getProducts();
         Map<Integer, Item> item = DataStore.getInstance().getItems();
         Map<Integer,Order> ord = DataStore.getInstance().getOrders();
         
         
         for(Order o : ord.values()){
          Product p = prod.get(o.getItem().getProductId());
           int diff = Math.abs(o.getItem().getSalesPrice() - p.getTarget_price());
              if(countMap.containsKey(o.getCustomerId())){
                   countMap.put(o.getCustomerId(), diff + countMap.get(o.getCustomerId()));
                   }          
               else{
                       countMap.put(o.getCustomerId(),diff);
                   }   
       }
        Map<Integer, Integer> sortedMapDesc = sortByComparator(countMap, true);    
        int i=0;
        for(Map.Entry<Integer, Integer> en : sortedMapDesc.entrySet()) { 
            if(i==3){
            break;
            }else{
             System.out.println("Customer = " + en.getKey()+" with Sum of difference between Target price and Sales price: "+en.getValue());
             i++;
            }
     }
     }
     

     //Method to sort a HashMap with Integer Values
     private static Map<Integer, Integer> sortByComparator(Map<Integer, Integer> unsortMap, final boolean order)
    {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>()
        {
            public int compare(Map.Entry<Integer, Integer> o1,
                    Map.Entry<Integer, Integer> o2)
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
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
    }
     
     
     //sorting for hashMap with float value
     private static Map<Integer, Float> sortByComparatorFloat(Map<Integer, Float> unsortMap, final boolean order)
    {
        List<Map.Entry<Integer, Float>> list = new LinkedList<Map.Entry<Integer, Float>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<Integer, Float>>()
        {
            public int compare(Map.Entry<Integer, Float> o1,
                    Map.Entry<Integer, Float> o2)
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
        Map<Integer, Float> sortedMap = new LinkedHashMap<Integer, Float>();
        for (Map.Entry<Integer, Float> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
    }
     
}
