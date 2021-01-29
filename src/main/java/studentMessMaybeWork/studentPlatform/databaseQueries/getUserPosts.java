package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.Comment;
import studentMessMaybeWork.studentPlatform.databaseEntities.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class getUserPosts {

    public static List<Post> getUserPosts(String userId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<Post> postList = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT posts.*, CONCAT(user.first_name, ' ' ,user.last_name) as UserName, user.profilePhoto FROM `posts` INNER JOIN user ON user.user_id = posts.creator_id WHERE posts.creator_id = (?) ORDER BY `posts`.`created_at` DESC LIMIT 5");
            preparedStatement.setString(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            postList = new ArrayList<>();

            while(rs.next()){
                int creatorId = rs.getInt("creator_id");
                String postDescription = rs.getString("description");
                String postId = rs.getString("id");
                String photoPath = rs.getString("photo");
                String forWho = rs.getString("sum_for_who");
                String createdAt_string = rs.getString("created_at");
                String deletedAt_string = rs.getString("deleted_at");
                String postCreatorName = rs.getString("UserName");
                String userProfilePhoto = rs.getString("profilePhoto");
                int postLikes = GetAllPosts.getPostLikes(postId);
                List<Comment> commentList = GetAllPosts.getPostComments(postId);
                List<String> userLikePostList = GetAllPosts.getPostLikesUsers(postId);

                postList.add(new Post(postId, creatorId, postDescription, photoPath, forWho,createdAt_string, deletedAt_string, postCreatorName, userProfilePhoto, postLikes, userLikePostList, commentList));
            }

            DatabaseConnectionHelper.close(conn);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return postList;
        }

    }


    public static List<Post> getMoreUserPosts(String userId, String start, String limit){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<Post> postList = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT posts.*, CONCAT(user.first_name, ' ' ,user.last_name) as UserName, user.profilePhoto FROM `posts` INNER JOIN user ON user.user_id = posts.creator_id WHERE posts.creator_id = (?) ORDER BY `posts`.`created_at` DESC LIMIT "+start+", "+limit+" ");
            preparedStatement.setString(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            postList = new ArrayList<>();

            while(rs.next()){
                int creatorId = rs.getInt("creator_id");
                String postDescription = rs.getString("description");
                String postId = rs.getString("id");
                String photoPath = rs.getString("photo");
                String forWho = rs.getString("sum_for_who");
                String createdAt_string = rs.getString("created_at");
                String deletedAt_string = rs.getString("deleted_at");
                String postCreatorName = rs.getString("UserName");
                String userProfilePhoto = rs.getString("profilePhoto");
                int postLikes = GetAllPosts.getPostLikes(postId);
                List<Comment> commentList = GetAllPosts.getPostComments(postId);
                List<String> userLikePostList = GetAllPosts.getPostLikesUsers(postId);

                postList.add(new Post(postId, creatorId, postDescription, photoPath, forWho,createdAt_string, deletedAt_string, postCreatorName, userProfilePhoto, postLikes, userLikePostList, commentList));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return postList;
        }

    }







}
