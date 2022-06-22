package com.inxtes.nowsyncserver.mapper;

import com.inxtes.nowsyncserver.model.Phone;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PhoneMapper {
    List<Phone> selectAllPhone();

    Integer insertPhone(Phone phones);

    Phone selectPhoneByNumber(String number);
}
