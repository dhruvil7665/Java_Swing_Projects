/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.SportsStoreFinanceOrganization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import UserInterface.SportsStoreFinanceManagerRole.SportsStoreFinanceManagerWorkAreaJPanel;

/**
 *
 * @author dimpi
 */
public class SportsStoreFinanceManagerRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new SportsStoreFinanceManagerWorkAreaJPanel(userProcessContainer, account, (SportsStoreFinanceOrganization)organization, enterprise , business);
    } 
}
