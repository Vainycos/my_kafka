package com.vainycos.mykafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MyKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyKafkaApplication.class, args);
    }

}
