package com.example.demo.controller;

import com.example.demo.myConfig.MyConfig;
import com.example.demo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    private Logger logger= LoggerFactory.getLogger(MyController.class);

    private ExecutorService executorService=Executors.newSingleThreadExecutor();

    @Resource
    private MyConfig myConfig;

    @Resource
    private Student student2;


    @RequestMapping("/test")
    public String test() {
        logger.info("-------------------------------------------------------------------");
        return myConfig == null ? "null" : myConfig.toString();
    }

    @RequestMapping("/test2")
    public String test2() {
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        executorService.execute(()->{
            MDC.setContextMap(copyOfContextMap);
            for (int i = 0; i <5; i++) {
              logger.info("this is the {} times",i);
            }
        });
        return student2 == null ? "null" : student2.toString();
    }


    @PreDestroy
    public void shutdown(){
        logger.info("shuting down pool................ ");
        executorService.shutdown();
    }

}
