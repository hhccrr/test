package com.hcr.springbeanlive;

import com.hcr.springbeanlive.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Appconfig.class);
        /**
         * bean
         * 这里的userService里user有值（Spring帮我们创建的 实例化（new UserService）-->填充属性 user）
         * 这里是单利，不会每次get都会创建
         */
        UserService userService = context.getBean("userService", UserService.class);
        /**
         * 对象
         * 这里的userService里user没有值
         */
        UserService userService1 = new UserService();
    }
}
