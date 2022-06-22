package com.inxtes.nowsyncserver.model;

public class ReturnMessage {
    int code;
    String content;

    public ReturnMessage(int code, String content) {
        this.code = code;
        this.content = content;
    }
}
