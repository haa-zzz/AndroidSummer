package com.example.asus.deliveryapplication.YiqingFragment;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

public class High_list extends LitePalSupport {

    private String type;
    private String province;
    private String city;
    private String county;

    public High_list() {
    }

    public High_list(String type, String province, String city, String county){
        setType(type);
        setProvince(province);
        setCity(city);
        setCounty(county);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

}
