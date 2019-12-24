/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Trek;

import Business.Role.CustomerRole;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author dedhi
 */
public class Trek {
    private String trekName;
    private String trekLocation;
    private String trekDescription;
    private String trekDifficultyLevel;
    private String trekBasicRequirement;
    private int trekDuration;
    private String trekItinerary;
    private String trekWeather;
    private String trekLeader;
    private String trekPickUpPoint; 
    private String trekBaseCamp;
    private int trekMaxAltitude;
    private int trekMinAge;
    private String trekBestTimeToVisit;
    private String trekFitnessRequired;
    private ArrayList<String> trekImages;
    private ArrayList<TrekSlotAndCost> trekSlotAndCost;
    ArrayList<TrekReview> trekReviewAndRating;
    private String trekVideo;
    private ArrayList<CustomerRole> customerBookedTrekDirectory;

    public ArrayList<TrekSlotAndCost> getTrekSlotAndCost() {
        return trekSlotAndCost;
    }

    public void setTrekSlotAndCost(ArrayList<TrekSlotAndCost> trekSlotAndCost) {
        this.trekSlotAndCost = trekSlotAndCost;
    }

    public Trek(){
        trekImages = new ArrayList<String>();
        trekReviewAndRating = new ArrayList<TrekReview>();
        customerBookedTrekDirectory = new ArrayList<CustomerRole>();
        trekSlotAndCost = new ArrayList<TrekSlotAndCost>();
    }
    
    public String getTrekName() {
        return trekName;
    }

    public void setTrekName(String trekName) {
        this.trekName = trekName;
    }

    public String getTrekLocation() {
        return trekLocation;
    }

    public void setTrekLocation(String trekLocation) {
        this.trekLocation = trekLocation;
    }

    public String getTrekDescription() {
        return trekDescription;
    }

    public void setTrekDescription(String trekDescription) {
        this.trekDescription = trekDescription;
    }

    public String getTrekDifficultyLevel() {
        return trekDifficultyLevel;
    }

    public void setTrekDifficultyLevel(String trekDifficultyLevel) {
        this.trekDifficultyLevel = trekDifficultyLevel;
    }

    public String getTrekBasicRequirement() {
        return trekBasicRequirement;
    }

    public void setTrekBasicRequirement(String trekBasicRequirement) {
        this.trekBasicRequirement = trekBasicRequirement;
    }

    public int getTrekDuration() {
        return trekDuration;
    }

    public void setTrekDuration(int trekDuration) {
        this.trekDuration = trekDuration;
    }

    public String getTrekItinerary() {
        return trekItinerary;
    }

    public void setTrekItinerary(String trekItinerary) {
        this.trekItinerary = trekItinerary;
    }

    public String getTrekWeather() {
        return trekWeather;
    }

    public void setTrekWeather(String trekWeather) {
        this.trekWeather = trekWeather;
    }

    public String getTrekLeader() {
        return trekLeader;
    }

    public void setTrekLeader(String trekLeader) {
        this.trekLeader = trekLeader;
    }

    public String getTrekPickUpPoint() {
        return trekPickUpPoint;
    }

    public void setTrekPickUpPoint(String trekPickUpPoint) {
        this.trekPickUpPoint = trekPickUpPoint;
    }

    public String getTrekBaseCamp() {
        return trekBaseCamp;
    }

    public void setTrekBaseCamp(String trekBaseCamp) {
        this.trekBaseCamp = trekBaseCamp;
    }

    public int getTrekMaxAltitude() {
        return trekMaxAltitude;
    }

    public void setTrekMaxAltitude(int trekMaxAltitude) {
        this.trekMaxAltitude = trekMaxAltitude;
    }

    public int getTrekMinAge() {
        return trekMinAge;
    }

    public void setTrekMinAge(int trekMinAge) {
        this.trekMinAge = trekMinAge;
    }

    public String getTrekBestTimeToVisit() {
        return trekBestTimeToVisit;
    }

    public void setTrekBestTimeToVisit(String trekBestTimeToVisit) {
        this.trekBestTimeToVisit = trekBestTimeToVisit;
    }

    public String getTrekFitnessRequired() {
        return trekFitnessRequired;
    }

    public void setTrekFitnessRequired(String trekFitnessRequired) {
        this.trekFitnessRequired = trekFitnessRequired;
    }

    public ArrayList<String> getTrekImages() {
        return trekImages;
    }

    public void setTrekImages(ArrayList<String> trekImages) {
        this.trekImages = trekImages;
    }

    public ArrayList<TrekReview> getTrekReviewAndRating() {
        return trekReviewAndRating;
    }

    public void setTrekReviewAndRating(ArrayList<TrekReview> trekReviewAndRating) {
        this.trekReviewAndRating = trekReviewAndRating;
    }

    public String getTrekVideo() {
        return trekVideo;
    }

    public void setTrekVideo(String trekVideo) {
        this.trekVideo = trekVideo;
    }

    public ArrayList<CustomerRole> getCustomerBookedTrekDirectory() {
        return customerBookedTrekDirectory;
    }

    public void setCustomerBookedTrekDirectory(ArrayList<CustomerRole> customerBookedTrekDirectory) {
        this.customerBookedTrekDirectory = customerBookedTrekDirectory;
    }
    
    public String toString(){
        return this.trekName;
    }
    
}
