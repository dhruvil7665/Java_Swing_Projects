/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.SportsStoreFinanceManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author dimpi
 */
public class SportsStoreFinanceOrganization extends Organization{

    public SportsStoreFinanceOrganization() {
        super(Organization.Type.SportsStoreFinance.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new SportsStoreFinanceManagerRole());
        return roles;
    }
     
}