package com.example.pc.theapplication.Helpers;

public class UserModel {

    private String id, username, password, phone, branch, country, admin;

    public UserModel(String id, String username, String password, String phone, String branch, String country, String admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.branch = branch;
        this.country = country;
        this.admin = admin;

    }

    public String getID() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }




}
