/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.TrekManagerOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import UserInterface.TrekManagerRole.EditTrekJPanel;
import UserInterface.TrekManagerRole.TrekManagerWorkAreaJPanel;

/**
 *
 * @author dimpi
 */

public class TrekManagerRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        try {
            return new TrekManagerWorkAreaJPanel(userProcessContainer, account, (TrekManagerOrganization)organization, enterprise, business);
        } catch (ParseException ex) {
            Logger.getLogger(TrekManagerRole.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
