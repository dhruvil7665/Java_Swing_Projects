/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Products.CustomerOrders;
import Business.Trek.TrekBooking;

/**
 *
 * @author shahd
 */
public class SportsStoreConfirmPaymentRequest extends WorkRequest{
    private CustomerOrders custOrder;
    private String cardHolderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public CustomerOrders getCustOrder() {
        return custOrder;
    }

    public void setCustOrder(CustomerOrders custOrder) {
        this.custOrder = custOrder;
    }
    


    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
  
    
}
