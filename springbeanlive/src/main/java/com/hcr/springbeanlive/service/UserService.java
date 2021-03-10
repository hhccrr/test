package com.hcr.springbeanlive.service;

import com.hcr.springbeanlive.dao.UserMapper;
import com.hcr.springbeanlive.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService implements BeanNameAware, BeanFactoryAware, InitializingBean {

    @Autowired
    private UserMapper userMapper;//mybatis代理对象

//    @Autowired
//    private User user;

    private String beanName;

    public void test(){

    }

    /**
     * BeanNameAware
     * name是spring帮我们生成对象的名字
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    /**
     * BeanFactoryAware
     * 告诉我们这个对象（UserService）是哪个bean工厂创建的
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    /**
     * InitializingBean
     * 初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //这里是根据我们业务进行初始化 执行我们定义的逻辑
    }
}
