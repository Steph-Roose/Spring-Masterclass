package com.rose.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

//@Component("jdbcDao")
@Repository("jdbcDao")
@NoArgsConstructor
@Getter
@Setter
public class JdbcProductDao implements ProductDao {

    private String driverClassName;
    private String url;
    private String user;
    private String password;

    @Autowired(required = false)
    private Connection connection;

    @Autowired(required = false)
    private DataSource dataSource;

    private Connection createConnection() throws ClassNotFoundException, SQLException {

        if(dataSource != null) {
            return dataSource.getConnection();
        }
        if(connection != null && !connection.isClosed()) {
            return connection;
        }

        Class.forName(driverClassName);
        return DriverManager.getConnection(url, user, password);
    }
    @Override
    public long count() {
        try(
                Connection conn = createConnection();
                PreparedStatement stmt = conn.prepareStatement("select count(*) from products");
                ResultSet rs = stmt.executeQuery();
        ) {
            rs.next();
            return rs.getLong(1);
        }
        catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
