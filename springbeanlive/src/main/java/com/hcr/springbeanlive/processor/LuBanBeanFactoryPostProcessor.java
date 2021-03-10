package com.hcr.springbeanlive.processor;

import com.hcr.springbeanlive.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * bean工厂的后置处理器
 */
@Component
public class LuBanBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        GenericBeanDefinition bd = (GenericBeanDefinition) beanFactory.getBeanDefinition("userService");

        Class<?> beanClass = bd.getBeanClass();

        System.out.println(beanClass);

        bd.setBeanClass(User.class);
    }
}
