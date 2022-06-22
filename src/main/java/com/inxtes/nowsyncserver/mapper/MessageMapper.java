package com.inxtes.nowsyncserver.mapper;

import com.inxtes.nowsyncserver.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    List<Message> selectAllMsg();

    Integer insertMsg(Message message);
}
