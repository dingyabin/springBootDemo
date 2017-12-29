package com.example.demo.aop;

import com.example.demo.datasource.DynamicDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by MrDing
 * Date: 2017/12/29.
 * Time:22:55
 *
 * @author MrDing
 */

@Aspect
@Component
public class ChangeDataSourceAop {


    @Pointcut(value = "execution(* com.example.demo.dao..*.*(..))")
    public void pointcutMethod() {

    }


    @Before("pointcutMethod()")
    public void changeDataSource(JoinPoint joinPoint) {
        Class<?>[] interfaces = joinPoint.getTarget().getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            if (anInterface.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource annotation = anInterface.getAnnotation(TargetDataSource.class);
                DynamicDataSourceHolder.setDataSource(annotation.value().getType());
            }
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(TargetDataSource.class)) {
            TargetDataSource annotation = method.getAnnotation(TargetDataSource.class);
            DynamicDataSourceHolder.setDataSource(annotation.value().getType());
        }
    }


}
