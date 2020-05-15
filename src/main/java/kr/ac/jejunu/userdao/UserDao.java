package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private final JdbcContext jdbcContext;

    protected UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) {
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        //리턴
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) throws SQLException{
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = dataSource.getConnection();
//            StatementStrategy statementStrategy = new UpdateStatementStrategy();
//            preparedStatement = statementStrategy.makeStrategy(user, connection);
//        } finally {
//            try {
//                preparedStatement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdateAndDelete(statementStrategy);
    }

    public void delete(Integer id) {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdateAndDelete(statementStrategy);
    }
}
