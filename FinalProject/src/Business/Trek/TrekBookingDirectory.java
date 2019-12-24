/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Trek;

import java.util.ArrayList;

/**
 *
 * @author dedhi
 */
public class TrekBookingDirectory {
    private ArrayList<TrekBooking> trekBookingList;

    public TrekBookingDirectory() {
        trekBookingList = new ArrayList<TrekBooking>();
    }

    public ArrayList<TrekBooking> getTrekBookingList() {
        return trekBookingList;
    }

    public void setTrekBookingList(ArrayList<TrekBooking> trekBookingList) {
        this.trekBookingList = trekBookingList;
    }
    
}
