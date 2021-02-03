package studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries;

import studentMessMaybeWork.studentPlatform.databaseQueries.DatabaseConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StartConversationWith {
    public static void startConversationWith(String currentUserId, String conversationWithId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            String uuid = UUID.randomUUID().toString().replace("-", "");
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("INSERT INTO `conversations` (`id_conversationPrimary`, `title`, `creator_idIndex`, `created_at`) VALUES (?, '', ?, current_timestamp());");
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, currentUserId);
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("INSERT INTO `participants` (`id_participants`, `conversation_id`, `users_id`, `type`) VALUES (NULL, ?, ?, 'single')");
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, currentUserId);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("INSERT INTO `participants` (`id_participants`, `conversation_id`, `users_id`, `type`) VALUES (NULL, ?, ?, 'single')");
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, conversationWithId);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("INSERT INTO `messages` (`id_mess`, `conversation_id`, `sender_id`, `message_type`, `message_text`, `attachment`, `created_at`, `deleted_at`) VALUES (NULL, ?, ?, 'text', 'This user start conversation', NULL, current_timestamp(), NULL)");
            preparedStatement.setString(1,uuid);
            preparedStatement.setString(2,currentUserId);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
    }
}
