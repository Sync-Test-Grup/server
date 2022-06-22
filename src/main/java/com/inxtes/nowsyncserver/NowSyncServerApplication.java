package com.inxtes.nowsyncserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NowSyncServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NowSyncServerApplication.class, args);
    }

}
