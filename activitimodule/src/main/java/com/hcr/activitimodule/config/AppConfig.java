package com.hcr.activitimodule.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@MapperScan("com.kuiniu.*.mapper")
@ComponentScan("com.hcr.activitimodule")
@Import(value = { DaoBeanConfig.class})
public class AppConfig {



}
