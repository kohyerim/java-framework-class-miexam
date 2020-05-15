package kr.ac.jejunu.userdao;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class UserDao {

    private final JdbcContext jdbcContext;

    protected UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select id, name, password from userinfo where id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement;
        };
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public void insert(User user) {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            return preparedStatement;
        };
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("update userinfo set name = ?, password = ? where id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdateAndDelete(statementStrategy);
    }

    public void delete(Integer id) {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from userinfo where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdateAndDelete(statementStrategy);
    }
}
