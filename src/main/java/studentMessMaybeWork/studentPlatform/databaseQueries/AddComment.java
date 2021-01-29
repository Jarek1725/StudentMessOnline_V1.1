package studentMessMaybeWork.studentPlatform.databaseQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddComment {
    public static boolean addComment(String postId, String commentText, String autorId, int parentId){
        if(IsPostInDB.isPostInDB(postId)){
            Connection con = null;
            PreparedStatement preparedStatement = null;
            int row = 0;
            try{
                con=DatabaseConnectionHelper.getConnection();
                preparedStatement = con.prepareStatement("INSERT INTO `post_comment` (`id`, `post_id`, `text`, `autor_id`, `parentId`, `created_at`, `deleted_at`) VALUES (NULL, (?), (?), (?), (?), NOW(), NULL);");
                preparedStatement.setString(1, postId);
                preparedStatement.setString(2, commentText);
                preparedStatement.setString(3, autorId);
                preparedStatement.setInt(4, parentId);

                row = preparedStatement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return (row!=0);
        }
        return false;
    }
}
