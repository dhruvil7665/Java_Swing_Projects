/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Products;

import Business.UserAccount.UserAccount;

/**
 *
 * @author shahd
 */
public class CustomerItems {
    
    
    private Product product;
    private UserAccount custAccount;
    private int quantity;

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
    
    public String toString(){
        return this.product.getName();
    }
  
}
