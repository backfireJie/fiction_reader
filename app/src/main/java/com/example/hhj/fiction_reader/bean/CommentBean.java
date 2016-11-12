package com.example.hhj.fiction_reader.bean;

/**
 * Created by XX on 2016/10/10.
 */
public class CommentBean {
    private String imgUrl;
    private String commentUrl;
    private String title;
    private String brief;
    private String time;

    public CommentBean(String imgUrl,String commentUrl,String title){
        this.imgUrl = imgUrl;
        this.commentUrl = commentUrl;
        this.title = title;
    }


    public CommentBean(String imgUrl, String commentUrl, String title, String brief, String time){
        this.imgUrl = imgUrl;
        this.commentUrl = commentUrl;
        this.title = title;
        this.brief = brief;
        this.time = time;

    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCommentUrl() {
        return commentUrl;
    }

    public void setCommentUrl(String commentUrl) {
        this.commentUrl = commentUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
