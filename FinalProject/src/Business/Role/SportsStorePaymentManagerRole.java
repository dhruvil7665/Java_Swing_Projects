/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.TrekManagerOrganization;
import Business.Organization.Organization;
import Business.Organization.SportsStorePaymentOrganization;
import Business.Organization.TrekFinanceOrganization;
import Business.UserAccount.UserAccount;
import UserInterface.SportsStorePaymentManagerRole.SportsStorePaymentManagerJPanel;
import javax.swing.JPanel;
import UserInterface.TrekFinanceManagerRole.TrekFinanceManagerWorkAreaJPanel;

/**
 *
 * @author dimpi
 */
public class SportsStorePaymentManagerRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new SportsStorePaymentManagerJPanel(userProcessContainer, account, (SportsStorePaymentOrganization)organization, enterprise,business);
    }
       
}
