package com.inxtes.nowsyncserver.service.impl;

import com.inxtes.nowsyncserver.mapper.PhoneMapper;
import com.inxtes.nowsyncserver.model.Phone;
import com.inxtes.nowsyncserver.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final PhoneMapper dao;

    @Autowired
    public PhoneServiceImpl(PhoneMapper dao) {
        this.dao = dao;
    }

    @Override
    public List<Phone> getAllPhone() {
        return dao.selectAllPhone();
    }


    /**
     * @param phones 要插入的数据列表
     * @return 未插入的数据
     */
    @Override
    public List<Phone> insertPhone(List<Phone> phones) {

        //插入失败列表
        List<Phone> unInsert = new ArrayList<>();

        //储存数据
        for (Phone phone : phones) {
            try {
                dao.insertPhone(phone);
            } catch (Exception e) {

                //异常 未插入
                logger.error("Uninsert:" + phone.getName() + ":" + phone.getPhoneNumber() + ":" + e.getMessage());
                unInsert.add(phone);
            }
        }

        return unInsert;

    }

}
