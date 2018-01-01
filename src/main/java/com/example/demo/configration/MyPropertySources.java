package com.example.demo.configration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;


/**
 * Created by MrDing
 * Date: 2018/1/1.
 * Time:11:35
 */
public class MyPropertySources extends PropertySourcesPlaceholderConfigurer {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        super.postProcessBeanFactory(beanFactory);
        try {
            WeConfig.reLoad(mergeProperties());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
