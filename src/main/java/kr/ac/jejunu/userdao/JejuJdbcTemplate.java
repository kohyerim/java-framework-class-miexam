package kr.ac.jejunu.userdao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class JejuJdbcTemplate extends JdbcTemplate {
    public JejuJdbcTemplate(DataSource datasource){
        super(datasource);
    }

    public void insert(String sql, Object[] params, User user){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for(int i=0; i<params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    public User get(String sql, Object[] params) {
        User user = query(sql, params, rs -> {
            User tmp = null;
            if(rs.next()){
                tmp = new User();
                tmp.setId(rs.getInt("id"));
                tmp.setName((rs.getString("name")));
                tmp.setPassword((rs.getString("password")));
                return tmp;
            }
            return tmp;
        });
        return user;
    }
}
