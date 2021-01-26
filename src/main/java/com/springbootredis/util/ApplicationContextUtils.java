package com.springbootredis.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//用来获取springboot创建好的工厂
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    //保留的工厂
    private static ApplicationContext applicationContext;

    //将创建好的工厂以参数传递
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //提供在工厂中获取的方法
    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

}
