package com.example.pc.theapplication.Helpers;

public class PostalSaderModel {
    private String ID, name, national_id, phone, inPrice, equal, outPrice, branchIn, branchOut, date, notes, state, userIn, userOut ;


    public PostalSaderModel(String ID, String name, String national_id, String phone, String inPrice, String equal, String outPrice, String branchIn, String branchOut, String date, String notes, String state, String userIn, String userOut ) {
        this.ID = ID;
        this.name = name;
        this.national_id = national_id;
        this.phone= phone;
        this.inPrice = inPrice;
        this.equal = equal;
        this.outPrice = outPrice;
        this.branchIn = branchIn;
        this.branchOut = branchOut;
        this.date = date;
        this.notes = notes;
        this.state= state;
        this.userIn = userIn;
        this.userOut = userOut;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInPrice() {
        return inPrice;
    }

    public void setInPrice(String inPrice) {
        this.inPrice = inPrice;
    }

    public String getEqual() {
        return equal;
    }

    public void setEqual(String equal) {
        this.equal = equal;
    }

    public String getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(String outPrice) {
        this.outPrice = outPrice;
    }

    public String getBranchIn() {
        return branchIn;
    }

    public void setBranchIn(String branchIn) {
        this.branchIn = branchIn;
    }

    public String getBranchOut() {
        return branchOut;
    }

    public void setBranchOut(String branchOut) {
        this.branchOut = branchOut;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserIn() {
        return userIn;
    }

    public void setUserIn(String userIn) {
        this.userIn = userIn;
    }

    public String getUserOut() {
        return userOut;
    }

    public void setUserOut(String userOut) {
        this.userOut = userOut;
    }

}
