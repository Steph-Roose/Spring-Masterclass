package com.rose.config;

import com.rose.dao.JdbcProductDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Example of setting up a connection pool.
 *
 * */
@Configuration
@PropertySource("classpath:jdbc.properties")
public class AppConfig3 {

    @Value("${jdbc.driver}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(driverClassName);
        bds.setUrl(url);
        bds.setUsername(user);
        bds.setPassword(password);

        bds.setInitialSize(10);     // initial amount of connections
        bds.setMaxTotal(100);       // maximum amount of connections
        bds.setMaxWaitMillis(500);  // max waiting time for a client when there's no connections available
        bds.setMaxIdle(50);         // max amount of connections that can be idle
        bds.setMinIdle(2);          // min amount of connections that can be idle
        return bds;
    }

    @Bean
    public JdbcProductDao jdbcDao() {
        return new JdbcProductDao();
    }
}
