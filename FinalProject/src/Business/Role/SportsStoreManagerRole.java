/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.SportsStoreManagerOrganization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import UserInterface.SportsStoreManagerRole.SportsStoreManagerWorkAreaJPanel;

/**
 *
 * @author dimpi
 */
public class SportsStoreManagerRole extends Role {
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new SportsStoreManagerWorkAreaJPanel(userProcessContainer, account, (SportsStoreManagerOrganization)organization, enterprise, business);
    }
    
}
