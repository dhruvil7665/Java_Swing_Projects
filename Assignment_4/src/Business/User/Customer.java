/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.User;

import Business.Abstract.User;

/**
 *
 * @author dedhi
 */
public class Customer extends User {
     public Customer(String userName, String password){
        super(userName, password);
    }
    
    @Override
    public String toString() {
        return getUserName(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean verify(String password){
        if(password.equals(getPassword()))
            return true;
        return false;
    }
}
