package com.hcr.activitimodule.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author cayden
 * @date 2020/04/03
 * @desc
 */
@Configuration
@EnableTransactionManagement
public class DaoBeanConfig implements EnvironmentAware {

    private Environment env;


    @Bean
    public DataSource createDataSource() {
        String dbDriver = env.getProperty("db.driver");
        String dbUrl = env.getProperty("db.url", String.class);
        String dbUsername = env.getProperty("db.username", String.class);
        String dbPassword = env.getProperty("db.password", String.class);

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        System.out.println();
        return dataSource;
    }


    /**
     * 初始化 jdbcTemplate
     */
    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    /**
     * 指定事务管理器 dataSourceTransactionManager
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(@Autowired DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }



    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        DataSource dataSource = createDataSource();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperLocations = resolver.getResources(env.getProperty("mybatis.xmlLocations"));
        sqlSessionFactoryBean.setMapperLocations(mapperLocations);
        sqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("mybatis.entityPackage"));


        //分页插件
        Properties properties = new Properties();
        //设置默认mysql
        properties.setProperty("helperDialect","mysql");
        //设置rowRound中offset不能当pageNum用
        properties.setProperty("offsetAsPageNum","false");
        //禁用自动统计
        properties.setProperty("rowBoundsWithCount","false");
        //查询默认如果有pageNum为0查询全部
        properties.setProperty("pageSizeZero","false");
        //支持通过mapper传递分页参数
        properties.setProperty("supportMethodsArguments", "true");
        //设置超出范围将返回null
        properties.setProperty("reasonable", "false");
        //配置参数映射
        properties.setProperty("params", "count=countSql");
        //自动获取数据库类型时，会自动获取一个数据库连接， 通过该属性来设置是否关闭获取的这个连接
        properties.setProperty("closeConn","closeConn");
        //控制返回结果可为null
        properties.setProperty("returnPageInfo", "check");

        //声明分页
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(properties);
        // 引入插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});

        return sqlSessionFactoryBean;
    }
}
