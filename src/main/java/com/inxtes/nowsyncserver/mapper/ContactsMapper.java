package com.inxtes.nowsyncserver.mapper;

import com.inxtes.nowsyncserver.model.Contacts;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContactsMapper {
    List<Contacts> selectAllContacts();

    Integer insertContacts(Contacts phones);

    Contacts selectContactsByNumber(String number);
}
