/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.TrekLeaderOrganization;
import Business.UserAccount.UserAccount;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import UserInterface.TrekLeaderRole.TrekLeaderWorkAreaJPanel;

/**
 *
 * @author dimpi
 */
public class TrekLeaderRole extends Role {
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        try {
            return new TrekLeaderWorkAreaJPanel(userProcessContainer, account, (TrekLeaderOrganization) organization, enterprise, business);
        } catch (ParseException ex) {
            Logger.getLogger(TrekLeaderRole.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
