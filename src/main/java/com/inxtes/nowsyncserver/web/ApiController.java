package com.inxtes.nowsyncserver.web;

import com.google.gson.Gson;
import com.inxtes.nowsyncserver.model.Contacts;
import com.inxtes.nowsyncserver.model.Message;
import com.inxtes.nowsyncserver.model.ReturnMessage;
import com.inxtes.nowsyncserver.service.ContactsService;
import com.inxtes.nowsyncserver.service.MessageService;
import com.inxtes.nowsyncserver.utils.DataProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApiController {

    private final ContactsService contactsService;
    private final MessageService messageService;
    private final DataProcess dataProcess;
    private final Gson gson = new Gson();
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public ApiController(ContactsService contactsService, MessageService messageService, DataProcess dataProcess) {
        this.contactsService = contactsService;
        this.messageService = messageService;
        this.dataProcess = dataProcess;
    }

    /**
     * 接收json化的phones列表，处理后储存存入数据库
     *
     * @param contacts 客户端推送的数据
     * @return 处理的结果信息
     */
    @RequestMapping(value = "/api/pushContacts", method = RequestMethod.POST)
    @ResponseBody
    public String pushContacts(@RequestParam("content") String contacts) {
        logger.error(contacts);
        // 未插入的数据
        List<Contacts> unInsert = new ArrayList<>();

        try {
            unInsert = dataProcess.insertData(contacts, contactsService, Contacts.class);

        } catch (Exception e) {

            // 异常
            logger.error(e.getMessage());

            return formatReturnMsg(404, e.getMessage(), "phone");
        }

        // 存在未储存的内容
        logger.info("Uninsert list size: " + unInsert.size());
        if (unInsert.size() != 0) {
            return formatReturnMsg(300, unInsert, "phone");
        }

        return formatReturnMsg(200, null, "phone");
    }

    @RequestMapping(value = "/api/pullContacts", method = RequestMethod.GET)
    @ResponseBody
    public String pullAllPhones() {
        return gson.toJson(contactsService.getAllPhone());
    }

    /**
     * 获取所有短信
     *
     * @return 未插入的短信
     */
    @RequestMapping(value = "/api/pullMessage", method = RequestMethod.GET)
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
    @PostMapping("/api/pushMessage")
    @ResponseBody
    public String pushMsg(@RequestParam("content") String msg) {
        // 未插入的数据
        List<Message> unInsert = new ArrayList<>();
        logger.error(msg);
        try {

            unInsert = dataProcess.insertData(msg, messageService, Message.class);

        } catch (Exception e) {

            // 异常
            logger.error(e.getMessage());
            e.printStackTrace();

            return formatReturnMsg(400, e.getMessage(), "sms");
        }

        // 存在未储存的内容
        logger.info("Uninsert list size: " + unInsert.size());
        if (unInsert.size() != 0) {
            return formatReturnMsg(300, unInsert, "sms");
        }

        return formatReturnMsg(200, null, "sms");
    }

    @GetMapping("/error")
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String error() {
        return formatReturnMsg(0, null, "error");
    }


    // 格式化返回信息
    private String formatReturnMsg(Integer code, Object obj, String type) {
        Gson gson = new Gson();
        return gson.toJson(new ReturnMessage(code, gson.toJson(obj), type));
    }
}
