package com.ajromero;

import com.ajromero.webapp.spring.ShoppingWebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingApplication {


    public static void main(String[] args) {
        SpringApplication.run(new Class[]{ShoppingApplication.class,
                ShoppingWebConfig.class}, args);
    }
}
