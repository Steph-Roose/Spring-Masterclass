package com.rose.config;

import com.rose.dao.DummyProductDao;
import com.rose.dao.JdbcProductDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig1 {

    @Bean
    public DummyProductDao dummyDao() {
        return new DummyProductDao();
    }

    @Bean
    public JdbcProductDao jdbcDao() {
        return new JdbcProductDao();
    }
}
