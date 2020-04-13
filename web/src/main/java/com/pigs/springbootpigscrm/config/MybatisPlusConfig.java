package com.pigs.springbootpigscrm.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author PIGS-猪农·杨
 * @version 1.0
 * @date 2020/3/7 11:34
 * @effect
 * 设置mybatis plus 分页配置
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        //设置方言类型
        page.setDialectType("mysql");
        return page;
    }
}
