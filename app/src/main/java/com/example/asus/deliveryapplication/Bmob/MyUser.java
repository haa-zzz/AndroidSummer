package com.example.asus.deliveryapplication.Bmob;


import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {

    private String tokenPhoto;
    private String tokenNickName;
    private String nickName;
    private String photo;
    private boolean sex = true;
    private String desc;
    private int age = 0;
    private String birthday;
    private String constellation;
    private String hobby;
    private String status;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

}
