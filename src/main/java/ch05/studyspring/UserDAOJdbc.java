package ch05.studyspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("userDAO")
public class UserDAOJdbc implements UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate; // 템플릿을 이용해 DI

    public UserDAOJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
                    user.setLevels(Level.valueOf(rs.getInt("levels")));
                    user.setLogin(rs.getInt("login"));
                    user.setRecommend(rs.getInt("recommend"));
                    return user;

                }
            };

    @Override
    public void add(User user) {
        this.jdbcTemplate.update("INSERT INTO USERS (ID, NAME, PASSWORD, LEVELS, LOGIN, RECOMMEND) VALUES (?, ?, ?, ?, ?, ?)"
                , user.getId()
                , user.getName()
                , user.getPassword()
                , user.getLevels().intValue()
                , user.getLogin()
                , user.getRecommend());

    }

    @Override
    public User get(String id) {
       // return this.jdbcTemplate.queryForObject("select * from users where id = ?", this.userMapper);
        return this.jdbcTemplate.queryForObject("select * from users where id = ?"
                , new Object[]{id}
                , this.userMapper);

    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users", this.userMapper);

    }

    @Override
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

    @Override
    public int getCount() {
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

    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update("UPDATE USERS SET " +
                "NAME = ?, PASSWORD = ?, LEVELS = ?" +
                ", LOGIN = ?, RECOMMEND = ?" +
                "WHERE ID = ?"
        , user.getName()
        , user.getPassword()
        , user.getLevels().intValue()
        , user.getLogin()
        , user.getRecommend());
    }
}
