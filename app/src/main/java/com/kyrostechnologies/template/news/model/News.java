package com.kyrostechnologies.template.news.model;

import java.io.Serializable;

public class News implements Serializable{
    private String title;
    private String date;
    private int image;
    private String content;
    private Channel channel;

    public News() {
    }

    public News(String title, String date, int image, String content, Channel channel) {
        this.title = title;
        this.date = date;
        this.image = image;
        this.content = content;
        this.channel = channel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShort_content() {
        if(content.length()>100){
            return content.substring(0, 80)+"...";
        }
        return content+"...";
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
