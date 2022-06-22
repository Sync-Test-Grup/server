package com.inxtes.nowsyncserver.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inxtes.nowsyncserver.model.Message;
import com.inxtes.nowsyncserver.model.Phone;
import com.inxtes.nowsyncserver.model.ReturnMessage;
import com.inxtes.nowsyncserver.service.MessageService;
import com.inxtes.nowsyncserver.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApiController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final PhoneService phoneService;
    private final MessageService messageService;

    private final Gson gson = new Gson();

    @Autowired
    public ApiController(PhoneService phoneService, MessageService messageService) {
        this.phoneService = phoneService;
        this.messageService = messageService;
    }


    /**
     * 接收json化的phones列表，处理后储存存入数据库
     *
     * @param content 客户端推送的数据
     * @return 处理的结果信息
     */
    @RequestMapping(value = "/api/pushPhones", method = RequestMethod.GET)
    @ResponseBody
    public String pushPhones(@RequestParam String content) {

        //未插入的数据
        List<Phone> unInsert = new ArrayList<>();

        try {

            Type type = new TypeToken<ArrayList<Phone>>() {
            }.getType();
            List<Phone> list = gson.fromJson(content, type);
            unInsert = phoneService.insertPhone(list);

        } catch (Exception e) {

            //异常
            logger.error(e.getMessage());
            e.printStackTrace();

            return formatReturnMsg(404, e.getMessage());

        }

        //存在未储存的内容
        logger.info("Uninsert list size: " + unInsert.size());
        if (unInsert.size() != 0) {
            return formatReturnMsg(300, unInsert);
        }

        return formatReturnMsg(200, null);

    }

    @RequestMapping(value = "/api/pullAllPhones", method = RequestMethod.GET)
    @ResponseBody
    public String pullAllPhones() {
        return gson.toJson(phoneService.getAllPhone());
    }

    /**
     * 获取所有短信
     *
     * @return 未插入的短信
     */
    @RequestMapping(value = "/api/pullAllMsg", method = RequestMethod.GET)
    @ResponseBody
    public String pullAllMsg() {
        return gson.toJson(messageService.getAllMsg());
    }

    /**
     * 推送短信到服务端
     *
     * @param msg
     * @return
     */
    @RequestMapping(value = "/api/pushMsg", method = RequestMethod.GET)
    @ResponseBody
    public String pushMsg(@RequestParam String msg) {
        //未插入的数据
        List<Message> unInsert = new ArrayList<>();

        try {

            Type type = new TypeToken<ArrayList<Message>>() {
            }.getType();
            List<Message> list = gson.fromJson(msg, type);
            unInsert = messageService.insertMsg(list);

        } catch (Exception e) {

            //异常
            logger.error(e.getMessage());
            e.printStackTrace();

            return formatReturnMsg(400, e.getMessage());

        }

        //存在未储存的内容
        logger.info("Uninsert list size: " + unInsert.size());
        if (unInsert.size() != 0) {
            return formatReturnMsg(300, unInsert);
        }

        return formatReturnMsg(200, null);
    }

    //格式化返回信息
    private String formatReturnMsg(Integer code, Object obj) {
        Gson gson = new Gson();
        return gson.toJson(new ReturnMessage(code, gson.toJson(obj)));
    }


}
