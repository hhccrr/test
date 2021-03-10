package com.hcr.springbeanlive.processor;

import com.hcr.springbeanlive.dao.UserMapper;
import com.hcr.springbeanlive.entity.A;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class LuBanFatoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        UserMapper o = (UserMapper) Proxy.newProxyInstance(LuBanFatoryBean.class.getClassLoader(), new Class[]{UserMapper.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        return o;
    }

    @Override
    public Class<?> getObjectType() {
        return UserMapper.class;
    }
}
