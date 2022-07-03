package com.inxtes.nowsyncserver.model;

public class ReturnMessage {
    int code;
    String content;
    String type;

    public ReturnMessage(int code, String content, String type) {
        this.code = code;
        this.content = content;
        this.type = type;
    }
}
