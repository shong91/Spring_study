package ch03.studyspring;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract public class UserDAO {
    private DataSource dataSource;
    private JdbcContext jdbcContext;

    // UserDAO가 직접 DataSource 를 DI 하는 것이 아니라,
    // 내부적으로 JdbcContext 를 통해 간접적으로 DataSource 를 DI 주입
    public void setJdbcContext(DataSource dataSource) {
        this.jdbcContext = new JdbcContext();
        this.jdbcContext.setDataSource(dataSource); // DI 주입
        this.dataSource = dataSource;
    }

    public void add(User user) {
        StatementStrategy stmt = new AddStatement(user);
        this.jdbcContext.workWithStatementStrategy(stmt);

    }

    public User get(String id) {
        StatementStrategy stmt = new GetStatement(id);

        this.jdbcContext.workWithStatementStrategy(stmt);
        User user = null;
        return user;
    }

    public void deleteAll() {
        this.jdbcContext.executeSQL("delete from users");

    }



    public int getCount() throws ClassNotFoundException, SQLException {
        StatementStrategy stmt = null; // getCountStement();
        this.jdbcContext.workWithStatementStrategy(stmt);
//        String sql = "SELECT COUNT(*) FROM USERS";
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//
//        ResultSet rs = pstmt.executeQuery();
//        rs.next();
//        int cnt = rs.getInt(1);
//
//        rs.close();
//        pstmt.close();
//        conn.close();

        return 0;

    }



}
