package com.example.asus.deliveryapplication.mycontentprovider;

public class person {

    private int id;
    private String name;
    private String phonenum;

    public person(int id, String name, String phonenum) {
        this.id = id;
        this.name = name;
        this.phonenum = phonenum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
}
