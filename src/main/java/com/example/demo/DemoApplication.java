package com.example.demo;


import com.example.demo.banner.Mybanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * @author MrDing
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan("com.example.demo.filter")
//启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableTransactionManagement
@MapperScan("com.example.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(DemoApplication.class).banner(new Mybanner()).run(args);
    }

}
