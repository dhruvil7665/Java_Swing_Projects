/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Employee.Employee;
import Business.Organization.AdminOrganization;
import Business.UserAccount.UserAccount;

/**
 *
 * @author ran
 */
public class ConfigureABusiness {
    
    public static Business configure(){
        // Three roles: LabAssistant, Doctor, Admin
        
        Business business = Business.getInstance();
        AdminOrganization adminOrganization = new AdminOrganization();
        business.getOrganizationDirectory().getOrganizationList().add(adminOrganization);
        
        // create new employee
        Employee employee= new Employee();
        employee.setName("Dhruvil Shah");
        
        //create new user account
        UserAccount account= new UserAccount();
        account.setUsername("admin");
        account.setPassword("admin");
        account.setRole("Admin");
        
        //assign account to employee
        account.setEmployee(employee);
        
        //add employee to admin organization
        
        adminOrganization.getEmployeeDirectory().getEmployeeList().add(employee);
        //add account to admin organizatin
        adminOrganization.getUserAccountDirectory().getUserAccountList().add(account);
        
        Employee employee1=new Employee();
        employee1.setName("John");
        
        UserAccount account1= new UserAccount();
        account1.setUsername("lab");
        account1.setPassword("lab");
        account1.setRole("Lab Assistant");
        account1.setEmployee(employee1);
        adminOrganization.getEmployeeDirectory().getEmployeeList().add(employee1);
        adminOrganization.getUserAccountDirectory().getUserAccountList().add(account1);
        
        Employee employee2=new Employee();
        employee2.setName("Wilson");
        
        UserAccount account2= new UserAccount();
        account2.setUsername("doctor");
        account2.setPassword("doctor");
        account2.setRole("Doctor");
        account2.setEmployee(employee2);
        adminOrganization.getEmployeeDirectory().getEmployeeList().add(employee2);
        adminOrganization.getUserAccountDirectory().getUserAccountList().add(account2);
        
        return business;
    }
    
}
