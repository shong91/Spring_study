package ch03.studyspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy {

    @Override
    public PreparedStatement makeStatement(Connection conn) throws SQLException {
       PreparedStatement pstmt;
        String sql = "DELETE FROM USERS";
        return conn.prepareStatement(sql);

    }

}
