package com.example.demo.configration;

import com.example.demo.model.Student;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:0:45
 */
@Configuration
public class MyConfigration {


    @Bean
    @ConfigurationProperties("student")
    public Student student(){
        System.out.println("student........................init");
        return  new Student();
    }


    @Bean
    public Student student2(){
        Student student = student();
        student.setName("update");
        return  student;
    }

}
