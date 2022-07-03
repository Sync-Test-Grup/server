package com.inxtes.nowsyncserver.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.inxtes.nowsyncserver.service.DataService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataProcess {
    private final Gson gson = new Gson();

    /**
     * @param content 将要储存的数据
     * @param service 对于储存数据相应的service对象
     * @param <T>     储存的内容的对象
     * @param tClass  对象Class
     * @return 未成功储存的数据
     * Note 不可以使用Gson().fromJson(ArrayList<T>()):
     * Exception:com.google.gson.internal.LinkedTreeMap cannot be cast to xxx
     */
    public <T> List<T> insertData(String content, DataService<T> service, Class<T> tClass) {

        JsonArray array = JsonParser.parseString(content).getAsJsonArray();
        List<T> list = new ArrayList<>();

        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, tClass));
        }

        return service.insertData(list);
    }
}
