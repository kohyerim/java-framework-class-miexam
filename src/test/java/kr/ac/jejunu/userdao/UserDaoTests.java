package kr.ac.jejunu.userdao;


import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    String name = "hulk";
    String password = "1234";
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        UserDao userDao = new UserDao() {
            @Override
            public Connection getConnection() throws SQLException, ClassNotFoundException {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection("jdbc:mysql://localhost/mydb?serverTimezone=UTC", "hyerim", "1234");
            }
        };
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void createUser() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        UserDao userDao = new UserDao() {
            @Override
            public Connection getConnection() throws SQLException, ClassNotFoundException {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection("jdbc:mysql://localhost/mydb?serverTimezone=UTC", "hyerim", "1234");
            }
        };
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(0));

        User insertedUser = userDao.get(user.getId());
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));

    }
}
