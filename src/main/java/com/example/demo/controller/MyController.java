package com.example.demo.controller;

import com.example.demo.myConfig.MyConfig;
import com.example.demo.model.Student;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:0:31
 */
@RestController
@RequestMapping("/demo")
//向容器中注入MyConfig,用这种方法注入的bean，名字为: <prefix>-<fqn>
@EnableConfigurationProperties(MyConfig.class)
public class MyController {

    @Resource
    private MyConfig myConfig;

    @Resource
    private Student student2;


    @RequestMapping("/test")
    public String test() {
        return myConfig == null ? "null" : myConfig.toString();
    }

    @RequestMapping("/test2")
    public String test2() {
        return student2 == null ? "null" : student2.toString();
    }

}
