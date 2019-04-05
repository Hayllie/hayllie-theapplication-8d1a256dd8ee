package com.example.pc.theapplication.Helpers;

public class NormalSaderModel {

    String ID, name, inPrice, equal, outPrice, BranchIn, BranchOut, date, notes, state, UserIn, UserOut;

    public NormalSaderModel(String ID, String name, String inPrice, String equal, String outPrice, String BranchIn, String BranchOut, String date, String notes, String state, String UserIn, String UserOut) {
        this.ID = ID;
        this.name = name;
        this.inPrice = inPrice;
        this.equal = equal;
        this.outPrice = outPrice;
        this.BranchIn = BranchIn;
        this.BranchOut = BranchOut;
        this.date = date;
        this.notes = notes;
        this.state = state;
        this.UserIn = UserIn;
        this.UserOut = UserOut;
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
        return BranchIn;
    }

    public void setBranchIn(String branchIn) {
        BranchIn = branchIn;
    }

    public String getBranchOut() {
        return BranchOut;
    }

    public void setBranchOut(String branchOut) {
        BranchOut = branchOut;
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
        return UserIn;
    }

    public void setUserIn(String userIn) {
        UserIn = userIn;
    }

    public String getUserOut() {
        return UserOut;
    }

    public void setUserOut(String userOut) {
        UserOut = userOut;
    }
}
