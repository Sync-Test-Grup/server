package com.inxtes.nowsyncserver.service;

import com.inxtes.nowsyncserver.model.Message;

import java.util.List;

public interface MessageService extends DataService<Message> {
    List<Message> getAllMsg();

}
