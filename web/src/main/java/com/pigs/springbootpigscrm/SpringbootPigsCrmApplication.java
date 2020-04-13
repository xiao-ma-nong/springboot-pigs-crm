package com.pigs.springbootpigscrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 *
 * @author PIGS
 */
@MapperScan("com.pigs.springbootpigscrm.mapper")
@SpringBootApplication
@EnableSwagger2
public class SpringbootPigsCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPigsCrmApplication.class, args);
    }

}
