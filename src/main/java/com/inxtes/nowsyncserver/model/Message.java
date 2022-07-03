package com.inxtes.nowsyncserver.model;

import org.springframework.stereotype.Component;

@Component
public class Message {
    String senderNumber;
    String getterNumber;
    String content;
    String date;
    String id;
    int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public String getGetterNumber() {
        return getterNumber;
    }

    public void setGetterNumber(String getterNumber) {
        this.getterNumber = getterNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
