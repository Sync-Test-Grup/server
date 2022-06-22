package com.inxtes.nowsyncserver.service.impl;

import com.inxtes.nowsyncserver.mapper.MessageMapper;
import com.inxtes.nowsyncserver.model.Message;
import com.inxtes.nowsyncserver.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    Logger logger = LoggerFactory.getLogger(getClass());


    private final MessageMapper mapper;

    @Autowired
    public MessageServiceImpl(MessageMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Message> getAllMsg() {
        return mapper.selectAllMsg();
    }

    @Override
    public List<Message> insertMsg(List<Message> list) {
        //插入失败列表
        List<Message> unInsert = new ArrayList<>();

        //储存数据
        for (Message msg : list) {
            try {
                mapper.insertMsg(msg);
            } catch (Exception e) {

                //异常 未插入
                logger.error("Uninsert:" + msg.getSenderNumber() + ":" + msg.getGetterNumber() + ":" + e.getMessage());
                unInsert.add(msg);
            }
        }

        return unInsert;
    }


}
