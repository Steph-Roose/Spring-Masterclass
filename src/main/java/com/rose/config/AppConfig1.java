package com.rose.config;

import com.rose.dao.DummyProductDao;
import com.rose.dao.JdbcProductDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

/**
 * Example of manually setting up connection to the DB.
 *
 * */
@Configuration
@PropertySource("classpath:jdbc.properties")
public class AppConfig1 {

    @Value("${jdbc.driver}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;


    public AppConfig1() {
        System.out.println("AppConfig1 instantiated");
    }

    @Bean
    public DummyProductDao dummyDao() {
        System.out.println("AppConfig1.dummyDao() called");
        return new DummyProductDao();
    }

    @Scope("singleton")
    @Bean
    public JdbcProductDao jdbcDao() {
        System.out.println("AppConfig1.jdbcDao() called");
        JdbcProductDao dao = new JdbcProductDao();
        dao.setDriverClassName(driverClassName);
        dao.setUrl(url);
        dao.setUser(user);
        dao.setPassword(password);
        return dao;
    }
}
