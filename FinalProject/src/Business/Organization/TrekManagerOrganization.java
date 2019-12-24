/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.TrekManagerRole;
import Business.Role.Role;
import Business.Trek.TrekBooking;
import Business.Trek.TrekBookingDirectory;
import Business.Trek.TrekDirectory;
import java.util.ArrayList;

/**
 *
 * @author dimpi
 */
public class TrekManagerOrganization extends Organization{
    private TrekDirectory trekDirectory;
    private TrekBookingDirectory trekBookingDirectory;

    public TrekBookingDirectory getTrekBookingDirectory() {
        return trekBookingDirectory;
    }

    public void setTrekBookingDirectory(TrekBookingDirectory trekBookingDirectory) {
        this.trekBookingDirectory = trekBookingDirectory;
    }

    public TrekDirectory getTrekDirectory() {
        return trekDirectory;
    }

    public void setTrekDirectory(TrekDirectory trekDirectory) {
        this.trekDirectory = trekDirectory;
    }

    public TrekManagerOrganization() {
        super(Organization.Type.TrekManager.getValue());
        trekDirectory = new TrekDirectory();
        trekBookingDirectory = new TrekBookingDirectory();
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new TrekManagerRole());
        return roles;
    }
     
}