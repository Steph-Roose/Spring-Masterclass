package com.rose.config;

import com.rose.dao.DummyProductDao;
import com.rose.dao.JdbcProductDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig1 {

    public AppConfig1() {
        System.out.println("AppConfig1 instantiated");
    }

    @Bean
    public DummyProductDao dummyDao() {
        System.out.println("AppConfig1.dummyDao() called");
        return new DummyProductDao();
    }

    @Scope("prototype")
    @Bean
    public JdbcProductDao jdbcDao() {
        System.out.println("AppConfig1.jdbcDao() called");
        JdbcProductDao dao = new JdbcProductDao();
        dao.setDriverClassName("org.h2.Driver");
        dao.setUrl("jdbc:h2:tcp://localhost/~/spring-training");
        dao.setUser("sa");
        dao.setPassword("");
        return dao;
    }
}
