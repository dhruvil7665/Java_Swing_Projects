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
public class SalesPerson {
    int salesId;

    public SalesPerson(int salesId){
        this.salesId = salesId;
    }
    
    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }
    
}
