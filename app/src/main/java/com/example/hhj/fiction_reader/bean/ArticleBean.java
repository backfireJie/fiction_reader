package com.example.hhj.fiction_reader.bean;

/**
 * Created by XX on 2016/10/9.
 */
public class ArticleBean {

    private String headLine;
    private String subheading;
    private String text;
    private String lineFeed;



    public ArticleBean( String subheading, String text,String lineFeed){

        this.subheading = subheading;
        this.text = text;
        this.lineFeed = lineFeed;
    }
    public String getLineFeed() {
        return lineFeed;
    }

    public void setLineFeed(String lineFeed) {
        this.lineFeed = lineFeed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }
}
