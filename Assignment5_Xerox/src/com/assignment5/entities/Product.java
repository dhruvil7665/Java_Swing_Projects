/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment5.entities;

/**
 *
 * @author DD
 */
public class Product {
   
   int productId;
   int max_price;
   int min_price;
   int target_price;
 
   
   
   public Product(int productId, int min_price, int max_price, int target_price){
       this.productId=productId;
       this.max_price=max_price;
       this.min_price=min_price;
       this.target_price=target_price;
   }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMax_price() {
        return max_price;
    }

    public void setMax_price(int max_price) {
        this.max_price = max_price;
    }

    public int getMin_price() {
        return min_price;
    }

    public void setMin_price(int min_price) {
        this.min_price = min_price;
    }

    public int getTarget_price() {
        return target_price;
    }

    public void setTarget_price(int target_price) {
        this.target_price = target_price;
    }
   
   @Override
    public String toString(){
        return "ProductId: "+productId+" MinPrice: "+min_price+" MaxPrice: "+max_price+" TargetPrice: "+target_price;
    }

}
