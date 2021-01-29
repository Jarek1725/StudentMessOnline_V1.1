package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.Comment;
import studentMessMaybeWork.studentPlatform.databaseEntities.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetSinglePost {
    public static Post getSinglePost(String postId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        Post post = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT posts.*, CONCAT(user.first_name, ' ' ,user.last_name) as UserName, user.profilePhoto FROM `posts` INNER JOIN user ON user.user_id = posts.creator_id WHERE id = (?)");
            preparedStatement.setString(1, postId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int creatorId = rs.getInt("creator_id");
                String postDescription = rs.getString("description");
                String photoPath = rs.getString("photo");
                String forWho = rs.getString("sum_for_who");
                String createdAt_string = rs.getString("created_at");
                String deletedAt_string = rs.getString("deleted_at");
                String postCreatorName = rs.getString("UserName");
                String userProfilePhoto = rs.getString("profilePhoto");
                int postLikes = GetAllPosts.getPostLikes(postId);
                List<Comment> commentList = GetAllPosts.getPostComments(postId);
                List<String> userLikePostList = GetAllPosts.getPostLikesUsers(postId);


                post = new Post(postId, creatorId, postDescription, photoPath, forWho,createdAt_string, deletedAt_string, postCreatorName, userProfilePhoto, postLikes, userLikePostList, commentList);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }
}
