package com.xiaomi.producter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // 启用异步支持
public class ProducterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducterApplication.class, args);
    }
}