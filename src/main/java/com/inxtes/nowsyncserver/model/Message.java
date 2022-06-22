package com.inxtes.nowsyncserver.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Message {
    String senderNumber;
    String getterNumber;
    String content;
    Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
