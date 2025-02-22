package com.hzx.conc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.hzx.conc.mapper")
@ComponentScan("com.hzx.conc.*")
@EnableCaching
@EnableScheduling
public class ConcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcApplication.class, args);
    }

}
