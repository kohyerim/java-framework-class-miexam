package kr.ac.jejunu.userdao;

public class UserDao {

    private final JdbcContext jdbcContext;

    protected UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) {
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public void insert(User user) {
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdateAndDelete(statementStrategy);
    }

    public void delete(Integer id) {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdateAndDelete(statementStrategy);
    }
}
