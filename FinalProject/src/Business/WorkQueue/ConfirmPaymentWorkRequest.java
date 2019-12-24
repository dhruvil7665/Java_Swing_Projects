/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Trek.TrekBooking;

/**
 *
 * @author dedhi
 */
public class ConfirmPaymentWorkRequest extends WorkRequest{
    private TrekBooking trekBooking;
    private String cardHolderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public TrekBooking getTrekBooking() {
        return trekBooking;
    }

    public void setTrekBooking(TrekBooking trekBooking) {
        this.trekBooking = trekBooking;
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
    
    public String toString(){
        return this.getTrekBooking().toString();
    }
    
    
}
