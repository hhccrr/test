package com.hcr.activitimodule.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author cayden
 * @date 2020/7/6 18:02
 */
@Configuration
@MapperScan("com.kuiniu.*.mapper")
@ComponentScan("com.hcr.activitimodule")
@Import(value = { DaoBeanConfig.class})
public class AppConfig {



}
