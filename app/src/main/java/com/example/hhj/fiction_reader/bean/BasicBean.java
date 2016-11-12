package com.example.hhj.fiction_reader.bean;

/**
 * Created by XX on 2016/10/9.
 */
public class BasicBean {
    private String imgUrl;
    private String textUrl;
    private String title;
    private String brief;
    private String time;


    public BasicBean(String imgUrl,String textUrl,String title){
        this.imgUrl = imgUrl;
        this.textUrl = textUrl;
        this.title = title;

    }
    public BasicBean(String imgUrl,String textUrl,String title,String brief){
        this.imgUrl = imgUrl;
        this.textUrl = textUrl;
        this.title = title;
        this.brief = brief;

    }
    public BasicBean(String imgUrl, String textUrl, String title, String brief, String time){
        this.imgUrl = imgUrl;
        this.textUrl = textUrl;
        this.title = title;
        this.brief = brief;
        this.time = time;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTextUrl() {
        return textUrl;
    }

    public void setTextUrl(String textUrl) {
        this.textUrl = textUrl;
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
}
