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
public class Order {
    
    int orderId;
    int salesId;
    int customerId;
    Item item;
    String market_segment;

    public Order(int orderId, int salesId, int customerId, Item item , String market_segment) {
        this.orderId = orderId;
        this.salesId = salesId;
        this.customerId = customerId;
        this.item = item;
        this.market_segment = market_segment;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

   
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Item getItem() {
        return item;
    }

    public String getMarket_segment() {
        return market_segment;
    }

    public void setMarket_segment(String market_segment) {
        this.market_segment = market_segment;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    @Override
    public String toString(){
        return "OrderId: "+orderId+" SaleId: "+salesId+" CustomerID: "+customerId+" Item: "+item.getItemId()+"  MarketSegment: "+market_segment;
    }
    
}
