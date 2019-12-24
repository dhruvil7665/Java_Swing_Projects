/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Products;

import Business.UserAccount.UserAccount;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author shahd
 */
public class CustomerOrders {
     private Product product;
    private UserAccount custAccount;
    private int quantity;
    private Date orderDate;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserAccount getCustAccount() {
        return custAccount;
    }

    public void setCustAccount(UserAccount custAccount) {
        this.custAccount = custAccount;
    }
}
