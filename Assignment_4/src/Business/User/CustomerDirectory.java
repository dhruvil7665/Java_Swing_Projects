/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.User;
import java.util.ArrayList;

import java.util.ArrayList;

/**
 *
 * @author dedhi
 */
public class CustomerDirectory {
  
    private ArrayList<Customer> customerDirectory;
    
    public CustomerDirectory(){
        customerDirectory = new ArrayList<Customer>();
    }

    public ArrayList<Customer> getCustomerDirectory() {
        return customerDirectory;
    }

    public void setCustomerDirectory(ArrayList<Customer> customerDirectory) {
        this.customerDirectory = customerDirectory;
    }
    
    public Customer add(String userName, String password){
        Customer customer = new Customer(userName, password);
        customerDirectory.add(customer);
        return customer;
    }
    
    public void delete(Customer customer){
        customerDirectory.remove(customer);
    }
}
