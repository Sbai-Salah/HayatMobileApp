package com.android.App.Hayat;

public class Request {
    private int id;
    private String fullName;
    private String phoneNumber;
    private String bloodGroup;
    private String Age;
    private String Adresse;
    private int userId;


    public Request(int id, String fullName, String phoneNumber, String bloodGroup, String age, String adresse,int userId) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.bloodGroup = bloodGroup;
        this.Age = age;
        this.Adresse = adresse;
        this.userId = userId;
    }

    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAge() {
        return Age;
    }
    public void setAge(String age) {
        Age = age;
    }
    public String getAdresse() {
        return Adresse;
    }
    public void setAdresse(String adresse) {
        Adresse = adresse;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
