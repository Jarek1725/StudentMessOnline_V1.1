package studentMessMaybeWork.studentPlatform.databaseQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditPostDesctiption {
    public static void editPostDescription(String postId, String userId, String postNewDescription){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("UPDATE `posts` SET `description` = (?) WHERE `posts`.`id` = (?) AND posts.creator_id = (?)");
            preparedStatement.setString(1,postNewDescription);
            preparedStatement.setString(2,postId);
            preparedStatement.setString(3, userId);

            int i = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
    }
}
