/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.SportsStoreManagerRole;
import Business.Role.Role;
import Business.Role.SportsStorePaymentManagerRole;
import java.util.ArrayList;

/**
 *
 * @author dimpi
 */
public class SportsStorePaymentOrganization extends Organization{

    public SportsStorePaymentOrganization() {
        super(Organization.Type.SportsStorePaymentOrganization.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new SportsStorePaymentManagerRole());
        return roles;
    }
}