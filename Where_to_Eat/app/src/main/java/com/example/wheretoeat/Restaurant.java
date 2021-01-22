package com.example.wheretoeat;

import java.io.Serializable;

public class Restaurant {
    //Constructors

    public Restaurant(String name, String daysOne, String hoursOne, String addressOne, String addressTwo, String city, String state, String zip,
                      String phone, String website, String breakfast, String lunch, String dinner) {
        this.name = name;
        this.daysOne = daysOne;
        this.hoursOne = hoursOne;
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
    }//End of constructor

    //Getters
    public String getName() {return name;}
    public String getDaysOne() {return daysOne;}
    public String getHoursOne() {return hoursOne;}
    public String getAddressOne() {return addressOne;}
    public String getAddressTwo() {return addressTwo;}
    public String getCity() {return city;}
    public String getState() {return state;}
    public String getZip() {return zip;}
    public String getPhone() {return phone;}
    public String getWebsite() {return website;}
    public String getBreakfast() {return breakfast;}
    public String getLunch() {return lunch;}
    public String getDinner() {return dinner;}

    //Setters
    public void setName(String name) {this.name = name;}
    public void setDaysOne(String daysOne) {this.daysOne = daysOne;}
    public void setHoursOne(String hoursOne) {this.hoursOne = hoursOne;}
    public void setAddressOne(String addressOne) {this.addressOne = addressOne;}
    public void setAddressTwo(String addressTwo) {this.addressTwo = addressTwo;}
    public void setCity(String city) {this.city = city;}
    public void setState(String state) {this.state = state;}
    public void setZip(String zip) {this.zip = zip;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setWebsite(String website) {this.website = website;}
    public void setBreakfast(String breakfast) {this.breakfast = breakfast;}
    public void setLunch(String lunch) {this.lunch = lunch;}
    public void setDinner(String dinner) {this.dinner = dinner;}

    //Variables
    private String name, daysOne, hoursOne, addressOne, addressTwo, city, state, zip, phone, website, breakfast, lunch, dinner;
}//End of class Restaurant
