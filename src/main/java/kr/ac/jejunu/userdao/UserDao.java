package kr.ac.jejunu.userdao;

public class UserDao {

    private final JejuJdbcTemplate jdbcTemplate;

    protected UserDao(JejuJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User get(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "select id, name, password from userinfo where id = ?";
        return jdbcTemplate.get(sql, params);
    }

    public void insert(User user) {
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        String sql = "insert into userinfo (name, password) value (?, ?)";
        jdbcTemplate.insert(sql, params, user);
    }

    public void update(User user) {
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "delete from userinfo where id = ?";
        jdbcTemplate.update(sql, params);
    }
}
