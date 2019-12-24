/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Role.CustomerRole;
import Business.Trek.Trek;
import Business.Trek.TrekSlotAndCost;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author dedhi
 */
public class AddTrekCostWorkRequest extends WorkRequest{
    private TrekSlotAndCost trekSlotAndCost;
    private Trek trek;
    
    public AddTrekCostWorkRequest(){
        trekSlotAndCost = new TrekSlotAndCost();
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
    
}
