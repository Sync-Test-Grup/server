package com.inxtes.nowsyncserver.service;

import com.inxtes.nowsyncserver.model.Contacts;

import java.util.List;

public interface ContactsService extends DataService<Contacts> {
    List<Contacts> getAllPhone();

}
