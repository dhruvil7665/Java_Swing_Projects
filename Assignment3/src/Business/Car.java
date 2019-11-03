/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author shahd
 */
public class Car {
    
    private boolean available;
    private String brand;
    private String manufacture;
    private int manufactured_year;
    private int min_seats;
    private int max_seats;
    private int serial_num;
    private String model_num;
    private String availble_city;
    private String car_Type;
    private boolean maintenance_certificate;

    public Car(boolean available, String brand,String manufacture, int manufactured_year, int min_seats, int max_seats, int serial_num, String model_num, String availble_city, String car_Type, boolean maintenance_certificate) {
        this.available = available;
        this.brand = brand;
        this.manufactured_year = manufactured_year;
        this.min_seats = min_seats;
        this.max_seats = max_seats;
        this.serial_num = serial_num;
        this.model_num = model_num;
        this.availble_city = availble_city;
        this.maintenance_certificate = maintenance_certificate;
        this.car_Type=car_Type;
        this.manufacture=manufacture;
        
    }
    
    
    public Car(){
       
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }
   
   

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getManufactured_year() {
        return manufactured_year;
    }

    public void setManufactured_year(int manufactured_year) {
        this.manufactured_year = manufactured_year;
    }

    public int getMin_seats() {
        return min_seats;
    }

    public void setMin_seats(int min_seats) {
        this.min_seats = min_seats;
    }

    public int getMax_seats() {
        return max_seats;
    }

    public void setMax_seats(int max_seats) {
        this.max_seats = max_seats;
    }

    public int getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(int serial_num) {
        this.serial_num = serial_num;
    }

    public String getModel_num() {
        return model_num;
    }

    public void setModel_num(String model_num) {
        this.model_num = model_num;
    }

    public String getAvailble_city() {
        return availble_city;
    }

    public void setAvailble_city(String availble_city) {
        this.availble_city = availble_city;
    }

    public boolean isMaintenance_certificate() {
        return maintenance_certificate;
    }

    public void setMaintenance_certificate(boolean maintenance_certificate) {
        this.maintenance_certificate = maintenance_certificate;
    }
   
@Override
    public String toString(){
       return this.getBrand();
    }

    public String getCar_Type() {
        return car_Type;
    }

    public void setCar_Type(String car_Type) {
        this.car_Type = car_Type;
    }
    
    
    
}
