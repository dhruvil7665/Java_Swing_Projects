/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Organization.Organization.Type;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class OrganizationDirectory {
    
    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    
    public Organization createOrganization(Type type){
        Organization organization = null;
         if (type.getValue().equals(Type.TrekManager.getValue())){
            organization = new TrekManagerOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.TrekLeader.getValue())){
            organization = new TrekLeaderOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.TrekFinance.getValue())){
            organization = new TrekFinanceOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.SportsStoreFinance.getValue())){
            organization = new SportsStoreFinanceOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.SportsStoreMananger.getValue())){
            organization = new SportsStoreManagerOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.Customer.getValue())){
            organization = new CustomerOrganization();
            organizationList.add(organization);
        }
         else if (type.getValue().equals(Type.TrekPaymentOrganization.getValue())){
            organization = new TrekPaymentOrganization();
            organizationList.add(organization);
        }
         else if (type.getValue().equals(Type.SportsStorePaymentOrganization.getValue())){
            organization = new SportsStorePaymentOrganization();
            organizationList.add(organization);
        }
        return organization;
    }
    
    
     public Organization deleteOrganization(Organization organization){
        
         
         
         organizationList.remove(organization);
//         if (type.getValue().equals(Type.TrekManager.getValue())){
//            organization = new TrekManagerOrganization();
//            organizationList.remove(organization);
//        }
//        else if (type.getValue().equals(Type.TrekLeader.getValue())){
//            organization = new TrekLeaderOrganization();
//            organizationList.add(organization);
//        }
//        else if (type.getValue().equals(Type.TrekFinance.getValue())){
//            organization = new TrekFinanceOrganization();
//            organizationList.add(organization);
//        }
//        else if (type.getValue().equals(Type.SportsStoreFinance.getValue())){
//            organization = new SportsStoreFinanceOrganization();
//            organizationList.add(organization);
//        }
//        else if (type.getValue().equals(Type.SportsStoreMananger.getValue())){
//            organization = new SportsStoreManagerOrganization();
//            organizationList.add(organization);
//        }
//        else if (type.getValue().equals(Type.Customer.getValue())){
//            organization = new CustomerOrganization();
//            organizationList.add(organization);
//        }
        return organization;
    }
}