package com.example.asus.deliveryapplication.HomeFragement;

public class PhoneModel {
    private String name;
    private String telPhone;
    private String photo;
    private String objectId;
    private boolean isSelected;
    private String tokenPhoto;
    private String tokenNickName;
    private boolean sex = true;
    private String desc;
    private int age = 0;
    private String birthday;
    private String constellation;
    private String hobby;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getTokenPhoto() {
        return tokenPhoto;
    }

    public void setTokenPhoto(String tokenPhoto) {
        this.tokenPhoto = tokenPhoto;
    }

    public String getTokenNickName() {
        return tokenNickName;
    }

    public void setTokenNickName(String tokenNickName) {
        this.tokenNickName = tokenNickName;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PhoneModel{" +
                "name='" + name + '\'' +
                ", telPhone='" + telPhone + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
