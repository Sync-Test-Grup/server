package com.inxtes.nowsyncserver.service.impl;

import com.inxtes.nowsyncserver.mapper.ContactsMapper;
import com.inxtes.nowsyncserver.model.Contacts;
import com.inxtes.nowsyncserver.service.ContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactsServiceImpl implements ContactsService {
    private final ContactsMapper dao;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public ContactsServiceImpl(ContactsMapper dao) {
        this.dao = dao;
    }

    @Override
    public List<Contacts> getAllPhone() {
        return dao.selectAllContacts();
    }


    /**
     * @param list 要插入的数据列表
     * @return 未插入的数据
     */

    @Override
    public List<Contacts> insertData(List<Contacts> list) {

        //插入失败列表
        List<Contacts> unInsert = new ArrayList<>();

        //储存数据
        for (Contacts contacts : list) {
            try {
                dao.insertContacts(contacts);
            } catch (Exception e) {

                //异常 未插入
                logger.error("Uninsert:" + contacts.getName() + ":" + contacts.getPhoneNumber() + ":" + e.getMessage());
                unInsert.add(contacts);
            }
        }

        return unInsert;
    }
}
