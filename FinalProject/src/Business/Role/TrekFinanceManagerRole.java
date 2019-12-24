/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.TrekManagerOrganization;
import Business.Organization.Organization;
import Business.Organization.TrekFinanceOrganization;
import Business.UserAccount.UserAccount;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import UserInterface.TrekFinanceManagerRole.TrekFinanceManagerWorkAreaJPanel;

/**
 *
 * @author dimpi
 */
public class TrekFinanceManagerRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        try {
            return new TrekFinanceManagerWorkAreaJPanel(userProcessContainer, account, (TrekFinanceOrganization)organization, enterprise,business);
        } catch (ParseException ex) {
            Logger.getLogger(TrekFinanceManagerRole.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
