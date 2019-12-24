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
public class TrekDirectory {
    private ArrayList<Trek> trekArrayList;

    public ArrayList<Trek> getTrekArrayList() {
        return trekArrayList;
    }

    public void setTrekArrayList(ArrayList<Trek> trekArrayList) {
        this.trekArrayList = trekArrayList;
    }

    public TrekDirectory() {
        trekArrayList = new ArrayList<Trek>();
    }
    
    public Trek addTrek(){
        Trek trek = new Trek();
        return trek;
    }
}
