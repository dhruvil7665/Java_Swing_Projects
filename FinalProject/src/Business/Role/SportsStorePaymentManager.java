/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.SportsStoreManagerOrganization;
import Business.Organization.SportsStorePaymentOrganization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import UserInterface.SportsStorePaymentManagerRole.SportsStorePaymentManagerJPanel;

/**
 *
 * @author shahd
 */
public class SportsStorePaymentManager extends Role {
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        return new SportsStorePaymentManagerJPanel(userProcessContainer, account, (SportsStorePaymentOrganization)organization, enterprise,business);
    }
}