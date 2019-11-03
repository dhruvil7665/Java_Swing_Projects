/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import java.util.ArrayList;

/**
 *
 * @author dhruvil
 */
public class AdminOrganization extends Organization{
    public AdminOrganization(){
        super(Type.Admin.getValue());
    }
    
    public ArrayList<String> getSupportedRole(){
        ArrayList<String> roles= new ArrayList<>();
        roles.add("Admin");
        return roles;
        
    }
}

