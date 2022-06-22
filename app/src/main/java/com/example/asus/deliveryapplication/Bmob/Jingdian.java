package com.example.asus.deliveryapplication.Bmob;

import androidx.annotation.NonNull;

import cn.bmob.v3.BmobObject;

public class Jingdian extends BmobObject {
    private int type;
    private String city;
    private String img;
    private String des;
    private String address;
    private String phone;
    private String opentime;
    private String ticket;
    private String summary;
    private int country;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Jingdian{" +
                "type=" + type +
                ", city='" + city + '\'' +
                ", img='" + img + '\'' +
                ", des='" + des + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", opentime='" + opentime + '\'' +
                ", ticket='" + ticket + '\'' +
                ", summary='" + summary + '\'' +
                ", country=" + country +
                '}';
    }
}
