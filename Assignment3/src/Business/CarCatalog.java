/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author shahd
 */
public class CarCatalog {
    
    private ArrayList<Car> carCatalog;
    public static Date latestUpdate;
    
   public CarCatalog(){
        
        carCatalog = new ArrayList<Car>();
        
        Car car1 = new Car(true, "Ferrari","Ferrari", 2019, 1, 2, 1,"Red One", "Boston","Sedan", true);
        Car car2 = new Car(true, "BMW","BMW5", 2019, 1, 4, 2, "X1", "New York", "Sedan",true);
        Car car3 = new Car(true, "Toyota","Toyota", 2018, 0, 4, 3, "T1", "Boston","Hatchback", true);
        Car car4 = new Car(false, "GM","GM", 2018, 1, 4, 4, "G1", "New York","Sedan", true);
        Car car5 = new Car(true, "Toyota","Toyota", 2017, 0, 4, 5, "T2", "Chicago","SUV", false);
        Car car6 = new Car(false, "GM","GM" ,2017, 1, 4, 6, "G2", "Chicago", "Sedan",true);
        Car car7 = new Car(true, "Ferrari","Ferrari", 2016, 1, 4, 7, "Ferr", "Seattle", "Sedan",true);
        Car car8 = new Car(false, "BMW","BMW5", 2016, 1, 4, 8, "X2", "Seattle", "SUV",true);
        Car car9 = new Car(true, "Toyota","Toyota", 2019, 0, 4, 9, "T3", "Austin","Sedan", false);
        Car car10 = new Car(true, "GMC","GM" ,2019, 1, 4, 10, "G3", "Austin","SportsCar", true);
        Car car11 = new Car(false, "BMW","BMW5" ,2019, 1, 4, 10, "gh5", "Boston","SportsCar", false);
        Car car12 = new Car(true, "GMC","GM" ,2019, 0, 5, 15, "J4", "Seattle","SportsCar", true);
        Car car13 = new Car(true, "BMW","BMW5" ,2019, 1, 5, 14, "K4", "Austin","Hatchback", false);
        Car car14 = new Car(true, "GMC","GM" ,2019, 0, 4, 12, "L7", "Boston","SUV", false);
        Car car15 = new Car(true, "NEXA","MS" ,2019, 1, 5, 10, "N5", "Boston","SportsCar", true);
        Car car16 = new Car(true, "NEXA","MS" ,2019, 1, 4, 10, "M4", "Austin","SUV", true);
        
        carCatalog.add(car1);
        carCatalog.add(car2);
        carCatalog.add(car3);
        carCatalog.add(car4);
        carCatalog.add(car5);
        carCatalog.add(car6);
        carCatalog.add(car7);
        carCatalog.add(car8);
        carCatalog.add(car9);
        carCatalog.add(car10);
        carCatalog.add(car11);
        carCatalog.add(car12);
        carCatalog.add(car13);
        carCatalog.add(car14);
        carCatalog.add(car15);
        carCatalog.add(car16);
        Calendar now = Calendar.getInstance();
        CarCatalog.setLatestUpdate(now.getTime());
        
    } 
    
  
 
    

    public ArrayList<Car> getCarCatalog() {
        return carCatalog;
    }

    public void setCarCatalog(ArrayList<Car> carCatalog) {
        this.carCatalog = carCatalog;
    }
        
    
    public static Date getLatestUpdate(){
    
    return latestUpdate;
    }
    
    public static void setLatestUpdate(Date latestUpdate){
    
    CarCatalog.latestUpdate=latestUpdate;
    
    }
    
    public Car getFirstCar(){
     for(Car c : getCarCatalog()){
            if(c.isAvailable()){
             return c;
            }
     }
     return null;
    }
    
    
    
    
    public Car addCar(){
    
    Car car = new Car();
    carCatalog.add(car);
     
    return car;
    }
    
      public void deleteProduct(Car car){
        carCatalog.remove(car);
    }
    
    public Car searchAccount(String name){
        for(Car car : this.carCatalog){
            if(car.getBrand().equalsIgnoreCase(name)){
                return car;
            }
        }
        return null;
    }
    
 
    
}
