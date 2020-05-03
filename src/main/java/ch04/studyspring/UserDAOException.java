package ch04.studyspring;

import ch01.studyspring.ConnectionMaker;
import ch03.studyspring.User;
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

public class UserDAOException {
    private JdbcTemplate jdbcTemplate;
    private ConnectionMaker connectionMaker;
    private User user;

   public void add() throws DuplicateUserIdException {
       try {
           Connection conn = connectionMaker.getConnection();

           String sql = "INSERT INTO USERS (ID, NAME, PASSWORD) VALUES (?, ?, ?)";
           PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, user.getId());
           pstmt.setString(2, user.getName());
           pstmt.setString(3, user.getPassword());

           pstmt.executeUpdate();
           pstmt.close();
           conn.close();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           if(e.getErrorCode() == 1212) { // 중복 errorCode
               throw new DuplicateUserIdException(e);
           }
       }
   }

    // 비교: jdbcTemplate 을 사용하는 경우
    // JdbcTemplate 은 템플릿과 콜백 안에서 발생하는 모든 SQLException 을 DataAccessException (RuntimeException)으로 포장하여 throw 한다.
    // 즉, 해당 메소드에서 오류가 날 경우 런타임 예외만 잡아 처리하면 되며, 그 외의 경우는 무시해도 된다.
    public void add(User user) {
       this.jdbcTemplate.update("insert into users (id, name, password) values (?, ?, ?)"
               , user.getId()
               , user.getName()
               , user.getPassword());
   }


}
