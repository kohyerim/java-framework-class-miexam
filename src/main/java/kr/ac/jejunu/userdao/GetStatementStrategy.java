package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetStatementStrategy implements StatementStrategy {
    private Integer id;
    public GetStatementStrategy(Integer id){
        this.id = id;
    }
    @Override
    public PreparedStatement makeStrategy(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select id, name, password from userinfo where id = ?");
        preparedStatement.setInt(1, id);

        return preparedStatement;
    }
}
