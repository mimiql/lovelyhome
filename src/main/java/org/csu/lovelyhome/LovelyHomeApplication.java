package org.csu.lovelyhome;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.csu.lovelyhome.persistence")
public class LovelyHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LovelyHomeApplication.class, args);
    }

}
