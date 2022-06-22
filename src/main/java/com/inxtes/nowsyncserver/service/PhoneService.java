package com.inxtes.nowsyncserver.service;

import com.inxtes.nowsyncserver.model.Phone;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PhoneService {
    List<Phone> getAllPhone();

    List<Phone> insertPhone(List<Phone> phones);

}
