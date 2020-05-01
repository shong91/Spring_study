package ch03.studyspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetStatement implements StatementStrategy {
    private String id;

    public GetStatement(String id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection conn) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        return pstmt;
    }
}
