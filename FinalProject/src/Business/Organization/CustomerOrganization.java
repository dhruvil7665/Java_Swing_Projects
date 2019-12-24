/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Products.CustomerOrderDirectory;
import Business.Products.MyCartDirectory;
import Business.Role.CustomerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author shahd
 */
public class CustomerOrganization extends Organization{
    
    private MyCartDirectory cartDir;
    private CustomerOrderDirectory orderDir;
     public CustomerOrganization() {
        super(Organization.Type.Customer.getValue());
        cartDir = new MyCartDirectory();
        orderDir=new CustomerOrderDirectory();
    }

    public CustomerOrderDirectory getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(CustomerOrderDirectory orderDir) {
        this.orderDir = orderDir;
    }

    public MyCartDirectory getCartDir() {
        return cartDir;
    }

    public void setCartDir(MyCartDirectory cartDir) {
        this.cartDir = cartDir;
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new CustomerRole());
        return roles;
    }
}
