/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.TrekLeaderRole;
import Business.Role.Role;
import Business.Role.TrekFinanceManagerRole;
import java.util.ArrayList;

/**
 *
 * @author dimpi
 */
public class TrekFinanceOrganization extends Organization{

    public TrekFinanceOrganization() {
        super(Organization.Type.TrekFinance.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new TrekFinanceManagerRole());
        return roles;
    }
}
