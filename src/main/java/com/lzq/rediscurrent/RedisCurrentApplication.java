package com.lzq.rediscurrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
public class RedisCurrentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisCurrentApplication.class, args);
    }

}
