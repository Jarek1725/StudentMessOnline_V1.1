package studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries;

import studentMessMaybeWork.studentPlatform.databaseQueries.DatabaseConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class isConversationWith {
    public static boolean isConversation(String userId, String secondUserId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM `participants` WHERE conversation_id IN(SELECT conversation_id FROM participants WHERE users_id = (?)) and users_id = (?)");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, secondUserId);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
        return false;
    }
}
