package com.hcr.springbeanlive;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.hcr.springbeanlive")
@EnableAspectJAutoProxy//开启切面
public class Appconfig {

}
