package com.inxtes.nowsyncserver.model;

import org.springframework.stereotype.Component;

@Component
public class Contacts {
    String phoneNumber;
    String name;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
