package studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries;

import studentMessMaybeWork.studentPlatform.databaseQueries.DatabaseConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddMsgToDatabase {
    public static void addMsgToDatabase(String msg, String convId, String senderId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("INSERT INTO `messages` (`id_mess`, `conversation_id`, `sender_id`, `message_type`, `message_text`, `attachment`, `created_at`, `deleted_at`) VALUES (NULL, ?, ?, 'text', ?, NULL, current_timestamp(), NULL);");
            preparedStatement.setString(1, convId);
            preparedStatement.setString(2, senderId);
            preparedStatement.setString(3, msg);

            preparedStatement.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
    }
}
