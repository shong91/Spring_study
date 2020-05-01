package ch03.studyspring.jdbcTemp;

import ch03.studyspring.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

abstract public class UserDAO {
    private JdbcTemplate jdbcTemplate; // 템플릿을 이용해 DI

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userMapper =
            new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int i) throws SQLException {
                        User user = new User();
                        user.setId(rs.getString("id"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));

                        return user;

                    }
            };


    public void add(User user) {
        this.jdbcTemplate.update("insert into users (id, name, password) values (?, ?, ?)"
                                    , user.getId()
                                    , user.getName()
                                    , user.getPassword());
    }

    public User get(String id) {
        this.jdbcTemplate.queryForObject("select * from users where id = ?", this.userMapper);
        return this.jdbcTemplate.queryForObject("select * from users where id = ?"
                                                , new Object[]{id}
                                                , this.userMapper);
    }

    public List<User> getAll(){
        return this.jdbcTemplate.query("select * from users", this.userMapper);
        //List<Map<String, Object>> list = this.jdbcTemplate.queryForList("select * from users");

    }

    public void deleteAll() {
        this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                        return conn.prepareStatement("delete from users");
                    }
                }
        );
    }



    public int getCount() throws ClassNotFoundException, SQLException {
        return this.jdbcTemplate.query(new PreparedStatementCreator() {
                                    @Override
                                    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                                        return conn.prepareStatement("select count(*) from users");
                                    }
                                }
                , new ResultSetExtractor<Integer>() {
                    @Override
                    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                        rs.next();
                        return rs.getInt(1);
                    }
                });

//        return this.jdbcTemplate.queryForInt("select count(*) from users");
    }



}
