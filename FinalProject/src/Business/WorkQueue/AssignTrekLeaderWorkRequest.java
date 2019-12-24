/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Trek.Trek;
import Business.Trek.TrekSlotAndCost;

/**
 *
 * @author dedhi
 */
public class AssignTrekLeaderWorkRequest extends WorkRequest{
    private TrekSlotAndCost trekSlotAndCost;
    private Trek trek;
    private String trekLeader;
    
    public TrekSlotAndCost getTrekSlotAndCost() {
        return trekSlotAndCost;
    }

    public void setTrekSlotAndCost(TrekSlotAndCost trekSlotAndCost) {
        this.trekSlotAndCost = trekSlotAndCost;
    }

    public Trek getTrek() {
        return trek;
    }

    public void setTrek(Trek trek) {
        this.trek = trek;
    }

    public String getTrekLeader() {
        return trekLeader;
    }

    public void setTrekLeader(String trekLeader) {
        this.trekLeader = trekLeader;
    }
    
    
}
