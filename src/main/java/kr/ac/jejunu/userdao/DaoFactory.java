package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String userName;
    @Value("${db.password}")
    private String password;

    @Bean
    public UserDao userDao() {
        return new UserDao(jdbcContext());
    }

    private JejuJdbcTemplate jdbcContext() {
        return new JejuJdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try{
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        return dataSource;
    }
}