/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Products;

import java.util.ArrayList;

/**
 *
 * @author shahd
 */
public class CustomerOrderDirectory {
    
    private ArrayList<CustomerOrders> orderList;

    public CustomerOrderDirectory() {
        orderList = new ArrayList<CustomerOrders>();
    }

    public ArrayList<CustomerOrders> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<CustomerOrders> orderList) {
        this.orderList = orderList;
    }
    
    
    
    
}
