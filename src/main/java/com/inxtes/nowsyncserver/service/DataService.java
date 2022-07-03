package com.inxtes.nowsyncserver.service;

import java.util.List;

public interface DataService<T> {
    List<T> insertData(List<T> list);

}
