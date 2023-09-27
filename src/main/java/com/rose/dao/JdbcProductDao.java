package com.rose.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;

@NoArgsConstructor
@Getter
@Setter
public class JdbcProductDao implements ProductDao {

    private String driverClassName;
    private String url;
    private String user;
    private String password;

    private Connection createConnection() throws ClassNotFoundException, SQLException {
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
