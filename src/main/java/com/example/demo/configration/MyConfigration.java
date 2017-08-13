package com.example.demo.configration;

import com.example.demo.model.Student;
import com.github.pagehelper.PageInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

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
        //此处student()并不会new 一个新的student，而是会用上面的返回值
        //换句话说:(student2()==student())=true
        Student student = student();
        student.setName("update");
        return  student;//student(),student2()的name都是“update”
    }




    /**
     * spring会自动把实现mybatis拦截器的类给注入SqlSessionFactoryBean
     * @return PageInterceptor
     */
    @Bean
    public PageInterceptor getPageInterceptor(){
        PageInterceptor pageInterceptor=new PageInterceptor();
        Properties properties=new Properties();
        properties.put("helperDialect","mysql");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

}
