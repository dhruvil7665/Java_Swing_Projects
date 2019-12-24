/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.SportsStoreManagerRole;
import Business.Role.Role;
import Business.Role.TrekPaymentManagerRole;
import java.util.ArrayList;

/**
 *
 * @author dimpi
 */
public class TrekPaymentOrganization extends Organization{

    public TrekPaymentOrganization() {
        super(Organization.Type.TrekPaymentOrganization.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new TrekPaymentManagerRole());
        return roles;
    }
}
