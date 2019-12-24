/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Products.ProductDirectory;
import Business.Role.SportsStoreManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author dimpi
 */


public class SportsStoreManagerOrganization extends Organization{
private ProductDirectory prodDir;
    public SportsStoreManagerOrganization() {
        super(Organization.Type.SportsStoreMananger.getValue());
        prodDir = new ProductDirectory();
    }

    public ProductDirectory getProdDir() {
        return prodDir;
    }

    public void setProdDir(ProductDirectory prodDir) {
        this.prodDir = prodDir;
    }


    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new SportsStoreManagerRole());
        return roles;
    }
}
