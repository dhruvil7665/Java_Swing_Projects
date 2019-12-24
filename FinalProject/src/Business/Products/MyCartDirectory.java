/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Products;

import java.util.ArrayList;

/**
 *
 * @author shahd
 */
public class MyCartDirectory {
     private ArrayList<CustomerItems> cartList;
     
      public MyCartDirectory() {
        cartList=new ArrayList<CustomerItems>();
    }

    public ArrayList<CustomerItems> getCartList() {
        return cartList;
    }

    public void setCartList(ArrayList<CustomerItems> cartList) {
        this.cartList = cartList;
    }
      
       public CustomerItems addProduct(CustomerItems item){
      
         cartList.add(item);
         return item;
    }
    
    public void deleteProduct(CustomerItems item){
        cartList.remove(item);
    } 
      
     
     
}
