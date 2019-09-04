package org.csu.lovelyhome;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("org.csu.lovelyhome.mapper")
public class LovelyHomeApplication{
//extends SpringBootServletInitializer
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(LovelyHomeApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(LovelyHomeApplication.class, args);
    }

}
