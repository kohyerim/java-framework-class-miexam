package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStrategy(Object object, Connection connection) throws SQLException {
        Integer id = (Integer) object;
        PreparedStatement preparedStatement = connection.prepareStatement("select id, name, password from userinfo where id = ?");
        preparedStatement.setInt(1, id);

        return preparedStatement;
    }
}
