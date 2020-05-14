package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HallaConnectionMaker implements ConnectionMaker {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Halla에 맞는 db사용
        return DriverManager.getConnection("jdbc:mysql://localhost/mydb?serverTimezone=UTC", "hyerim", "1234");
    }
}
