package com.example.asus.deliveryapplication.Bmob;

import cn.bmob.v3.BmobObject;

public class TourTui extends BmobObject {

    private String  pic;
    private String  photo;
    private String  star;
    private String  commentor;
    private String  comment;
    private String  score;
    private String  commentNum;
    private String  rank;
    private String  city;
    private String  price;
    private int type;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getCommentor() {
        return commentor;
    }

    public void setCommentor(String commentor) {
        this.commentor = commentor;
    }

    public String getComment() {
        return comment;
    }

    public String getPrice() {
        return price;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TourTui{" +
                "pic='" + pic + '\'' +
                ", photo='" + photo + '\'' +
                ", star='" + star + '\'' +
                ", commentor='" + commentor + '\'' +
                ", comment='" + comment + '\'' +
                ", score='" + score + '\'' +
                ", commentNum='" + commentNum + '\'' +
                ", rank='" + rank + '\'' +
                ", city='" + city + '\'' +
                ", type=" + type +
                '}';
    }
}
