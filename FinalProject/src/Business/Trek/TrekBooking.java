/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Trek;

import Business.UserAccount.UserAccount;
import java.util.Date;

/**
 *
 * @author dedhi
 */
public class TrekBooking {
    private UserAccount customer;
    private int totalTrekkers;
    private Trek trek;
    private TrekSlotAndCost trekSlotAndCost;
    private Date bookingDate;

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public UserAccount getCustomer() {
        return customer;
    }
    public void setCustomer(UserAccount customer) {
        this.customer = customer;
    }

    public Trek getTrek() {
        return trek;
    }

    public void setTrek(Trek trek) {
        this.trek = trek;
    }

    public TrekSlotAndCost getTrekSlotAndCost() {
        return trekSlotAndCost;
    }

    public void setTrekSlotAndCost(TrekSlotAndCost trekSlotAndCost) {
        this.trekSlotAndCost = trekSlotAndCost;
    }

    public int getTotalTrekkers() {
        return totalTrekkers;
    }

    public void setTotalTrekkers(int totalTrekkers) {
        this.totalTrekkers = totalTrekkers;
    }
    
    public String toString(){
        return this.trek.toString();
    }
    
}
