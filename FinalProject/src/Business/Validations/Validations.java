/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dedhi
 */
public class Validations {
    
    public boolean onlyLettersValidation(String input){
        Pattern p = Pattern.compile("^[a-zA-Z]+$");
        Matcher m = p.matcher(input);
        boolean b = m.matches();
        return b;
    }
    
    public boolean usernamePatternCorrect(String input){
        Pattern p = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher m = p.matcher(input);
        boolean b = m.matches();
        return b;
    }
    
    public boolean passwordPatternCorrect(String input){
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$");
        Matcher m = p.matcher(input);
        boolean b = m.matches();
        return b;
    }
    
    public boolean atleastOneLetterValidation(String input){
        Pattern p = Pattern.compile("^([^A-Za-z]+)$");
        Matcher m = p.matcher(input);
        boolean b = m.matches();
        return b;
    }
    
    public boolean twoDigits(String input){
        Pattern p = Pattern.compile("[0-9]{2}");
        Matcher m = p.matcher(input);
        boolean b = m.matches();
        return b;
    }
    
    public boolean threeDigits(String input){
        Pattern p = Pattern.compile("[0-9]{3}");
        Matcher m = p.matcher(input);
        boolean b = m.matches();
        return b;
    }
    
    public boolean sixteenDigits(String input){
        Pattern p = Pattern.compile("[0-9]{16}");
        Matcher m = p.matcher(input);
        boolean b = m.matches();
        return b;
    }
}
