/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Airliner;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author dedhi
 */
public class AirlinerDirectory {
    private ArrayList<Airliner> airlinerDirectory;
    
    public AirlinerDirectory(){
        airlinerDirectory = new ArrayList<Airliner>();
    }

    public ArrayList<Airliner> getAirlinerDirectory() {
        return airlinerDirectory;
    }

    public void setAirlinerDirectory(ArrayList<Airliner> airlinerDirectory) {
        this.airlinerDirectory = airlinerDirectory;
    }
    
    public Airliner add(){
        Airliner airliner = new Airliner();
        airlinerDirectory.add(airliner);
        return airliner;
    }
    
    public void deleteAirliner(Airliner airliner){
        airlinerDirectory.remove(airliner);
    }
    
    
}
