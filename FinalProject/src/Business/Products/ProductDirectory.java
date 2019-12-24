/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Products;

import Business.Employee.Employee;
import java.util.ArrayList;

/**
 *
 * @author shahd
 */
public class ProductDirectory {
    
     private ArrayList<Product> productList;

    public ProductDirectory() {
    
        productList=new ArrayList<Product>();
    }
     
     
    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }
    
     public Product createProduct(Product product){
      
         productList.add(product);
         return product;
    }
    
    public void deleteProduct(Product product){
        productList.remove(product);
    }
    
}
