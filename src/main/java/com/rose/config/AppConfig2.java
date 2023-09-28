package com.rose.config;

import com.rose.dao.DummyProductDao;
import com.rose.dao.JdbcProductDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Example of manually wiring and autowiring connection to the DB.
 *
 * */
@Configuration
@PropertySource("classpath:jdbc.properties")
public class AppConfig2 {

    @Value("${jdbc.driver}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public Connection connection() throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        return DriverManager.getConnection(url, user, password);
    }

    @Bean
    public JdbcProductDao jdbcDao(Connection connection) {
//    Manual wiring:
//        JdbcProductDao dao = new JdbcProductDao();
//        dao.setConnection(connection);
//        return dao;
        return new JdbcProductDao();
    }
}
