package com.bw.edu.ctb.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据库配置
 */
@Configuration
public class DataSourceConfig {
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(name = "basicDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    /**
     * 返回数据库的会话工厂
     * @param ds
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        // 加载MyBatis配置文件
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        // 能加载多个，所以可以配置通配符(如：classpath*:mapper/**/*.xml)
        bean.setMapperLocations(resourcePatternResolver.getResources(mapperLocations));
        // 配置mybatis的config文件
        // sqlSessionFactoryBean.setConfigLocation("mybatis-config.xml");
        return bean.getObject();
    }

    @Bean(name = "basicTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("basicDataSource") DataSource basicTddlDataSource) {
        return new DataSourceTransactionManager(basicTddlDataSource);
    }

    /**
     * 返回数据库的会话模板
     * @param sessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory) throws Exception{
        return new SqlSessionTemplate(sessionFactory);
    }
}
