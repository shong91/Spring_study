package ch01.studyspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private ConnectionMaker connectionMaker;

    public UserDAO(ConnectionMaker connectionMaker){
        DAOFactory daoFactory = new DAOFactory();
        this.connectionMaker = daoFactory.connectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.getConnection();

        String sql = "INSERT INTO USERS (ID, NAME, PASSWORD) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException{
        Connection conn = connectionMaker.getConnection();

        String sql = "SELECT * FROM USERS WHERE ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }

    public void deleteAll() throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.getConnection();
        String sql = "DELETE FROM USERS";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public int getCount() throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.getConnection();
        String sql = "SELECT COUNT(*) FROM USERS";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int cnt = rs.getInt(1);

        rs.close();
        pstmt.close();
        conn.close();

        return cnt;

    }
}
