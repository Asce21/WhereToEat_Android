package com.example.wheretoeat;

import java.io.Serializable;

public class Restaurant {
    //Constructors

    public Restaurant(String name, String daysOne, String daysTwo, String hoursOne, String hoursTwo, String addressOne, String addressTwo, String city, String state, String zip,
                      String phone, String website, boolean breakfast, boolean lunch, boolean dinner, boolean locations) {
        this.name = name;
        this.daysOne = daysOne;
        this.daysTwo = daysTwo;
        this.hoursOne = hoursOne;
        this.hoursTwo = hoursTwo;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.website = website;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.locations = locations;
    }//End of constructor

    //Getters
    public String getName() {return name;}
    public String getDaysOne() {return daysOne;}
    public String getDaysTwo() {return daysTwo;}
    public String getHoursOne() {return hoursOne;}
    public String getHoursTwo() {return hoursTwo;}
    public String getAddressOne() {return addressOne;}
    public String getAddressTwo() {return addressTwo;}
    public String getCity() {return city;}
    public String getState() {return state;}
    public String getZip() {return zip;}
    public String getPhone() {return phone;}
    public String getWebsite() {return website;}
    public boolean isBreakfast() {return breakfast;}
    public boolean isLunch() {return lunch;}
    public boolean isDinner() {return dinner;}
    public boolean isLocations() {return locations;}

    //Setters
    public void setName(String name) {this.name = name;}
    public void setDaysOne(String daysOne) {this.daysOne = daysOne;}
    public void setDaysTwo(String daysTwo) {this.daysTwo = daysTwo;}
    public void setHoursOne(String hoursOne) {this.hoursOne = hoursOne;}
    public void setHoursTwo(String hoursTwo) {this.hoursTwo = hoursTwo;}
    public void setAddressOne(String addressOne) {this.addressOne = addressOne;}
    public void setAddressTwo(String addressTwo) {this.addressTwo = addressTwo;}
    public void setCity(String city) {this.city = city;}
    public void setState(String state) {this.state = state;}
    public void setZip(String zip) {this.zip = zip;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setWebsite(String website) {this.website = website;}
    public void setBreakfast(boolean breakfast) {this.breakfast = breakfast;}
    public void setLunch(boolean lunch) {this.lunch = lunch;}
    public void setDinner(boolean dinner) {this.dinner = dinner;}
    public void setLocations(boolean locations) {this.locations = locations;}

    //Variables
    private String name, daysOne, daysTwo, hoursOne, hoursTwo, addressOne, addressTwo, city, state, zip, phone, website;
    private boolean breakfast, lunch, dinner, locations;
}//End of class Restaurant
