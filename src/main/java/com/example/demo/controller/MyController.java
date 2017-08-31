package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Weight;
import com.example.demo.myConfig.MyConfig;
import com.example.demo.model.Student;
import com.example.demo.result.Result;
import com.example.demo.service.WeightService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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

    private Logger logger = LoggerFactory.getLogger(MyController.class);

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Resource
    private MyConfig myConfig;

    @Resource
    private Student student2;

    @Resource
    private WeightService weightService;


    @RequestMapping("/test")
    public String test() {
        for (int i = 0; i < 800; i++) {
            logger.info("this is a info message {} ", i);
            logger.warn("this is a warn message {} ", i);
        }
        return myConfig == null ? "null" : myConfig.toString();
    }


    @RequestMapping("/test2")
    public String test2() {
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        executorService.execute(() -> {
            MDC.setContextMap(copyOfContextMap);
            for (int i = 0; i < 5; i++) {
                logger.info("this is the {} times", i);
            }
        });
        return student2 == null ? "null" : student2.toString();
    }


    @RequestMapping("/test3")
    public Result test3(@RequestParam(name = "exception", defaultValue = "2") long exception) {
        Weight weight = new Weight();
        weight.setId(exception);
        weightService.insertWeight(weight);
        PageHelper.startPage(1, 10);
        List<Weight> weights = weightService.selectAll();
        PageInfo<Weight> weightPageInfo = new PageInfo<>(weights);
        HashMap<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("list", weights);
        resultMap.put("total", weightPageInfo.getTotal());
        logger.info("成功的保存了一条记录,Weight={}", JSONObject.toJSONString(weight));
        return new Result("E000000", "ok", true, resultMap);
    }


    @PreDestroy
    public void shutdown() {
        logger.info("shuting down pool................ ");
        executorService.shutdown();
    }

}
