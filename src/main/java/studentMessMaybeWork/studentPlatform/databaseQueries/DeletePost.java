package studentMessMaybeWork.studentPlatform.databaseQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePost {
    public static void deletePost(String postId, String userId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("DELETE FROM `posts` WHERE `posts`.`id` = (?) AND posts.creator_id = (?)");
            preparedStatement.setString(1,postId);
            preparedStatement.setString(2, userId);

            int i = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
    }
}
