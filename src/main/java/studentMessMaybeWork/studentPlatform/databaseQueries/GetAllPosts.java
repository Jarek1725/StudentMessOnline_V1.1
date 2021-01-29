package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.Comment;
import studentMessMaybeWork.studentPlatform.databaseEntities.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetAllPosts {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentmess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";

    public static List<Post> getAllPosts(){
        List<Post> allPostsList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT posts.*, CONCAT(user.first_name, ' ' ,user.last_name) as UserName, user.profilePhoto FROM `posts` INNER JOIN user ON user.user_id = posts.creator_id ORDER BY `posts`.`created_at` DESC LIMIT 100";
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                String postId = rs.getString("id");
                int creatorId = rs.getInt("creator_id");
                String postDescription = rs.getString("description");
                String photoPath = rs.getString("photo");
                String forWho = rs.getString("sum_for_who");
                String createdAt_string = rs.getString("created_at");
                String deletedAt_string = rs.getString("deleted_at");
                String postCreatorName = rs.getString("UserName");
                String userProfilePhoto = rs.getString("profilePhoto");
                int postLikes = getPostLikes(postId);
                List<Comment> commentList = getPostComments(postId);
                List<String> userLikePostList = getPostLikesUsers(postId);

                allPostsList.add(new Post(postId, creatorId, postDescription, photoPath, forWho, createdAt_string, deletedAt_string, postCreatorName, userProfilePhoto, postLikes, userLikePostList, commentList));

                System.out.println("HGALO");
                for (String s : userLikePostList) {
                    System.out.println(s);
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Post post : allPostsList) {
            System.out.println(post);
        }
        return allPostsList;
    }



    public static List<Post> getAllPostsInRange(int start, int limit, String date, List<String> friendsList, List<String> classList, List<String> schoolList, String userId){
        List<Post> allPostsList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String friendsString = "";
            String classUsersString = "";
            String schoolUserString = "";

            for (String s : friendsList) {
                friendsString += s+", ";
            }
            for (String s : classList) {
                classUsersString += s+", ";
            }
            for (String s : schoolList) {
                schoolUserString += s+", ";
            }

            friendsString = removeLastCharacter(friendsString);
            classUsersString = removeLastCharacter(classUsersString);
            schoolUserString = removeLastCharacter(schoolUserString);

            String condition_1 = "posts.creator_id IN ("+friendsString+") AND sum_for_who = 1 OR posts.creator_id IN ("+friendsString+") AND sum_for_who = 3  OR posts.creator_id IN ("+friendsString+") AND sum_for_who = 5 OR posts.creator_id IN ("+friendsString+") AND sum_for_who = 7 ";
            String condition_2 = "posts.creator_id IN ("+classUsersString+") AND sum_for_who = 2 OR posts.creator_id IN ("+classUsersString+") AND sum_for_who = 3 OR posts.creator_id IN ("+classUsersString+") AND sum_for_who = 6 OR posts.creator_id IN ("+classUsersString+") AND sum_for_who = 7 ";
            String condition_3 = "posts.creator_id IN ("+schoolUserString+") AND sum_for_who = 4 OR posts.creator_id IN ("+schoolUserString+") AND sum_for_who = 5 OR posts.creator_id IN ("+schoolUserString+") AND sum_for_who = 6 OR posts.creator_id IN ("+schoolUserString+") AND sum_for_who = 7 ";

            String sql = "SELECT posts.*, CONCAT(user.first_name, ' ' ,user.last_name) as UserName, user.profilePhoto FROM `posts` INNER JOIN user ON user.user_id = posts.creator_id WHERE "+condition_1+" OR "+condition_2+" OR "+condition_3+" OR creator_id = "+userId+" AND posts.created_at <= ? ORDER BY `posts`.`created_at` DESC LIMIT ?, ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,date);
            stmt.setInt(2,start);
            stmt.setInt(3, limit);


            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String postId = rs.getString("id");
                int creatorId = rs.getInt("creator_id");
                String postDescription = rs.getString("description");
                String photoPath = rs.getString("photo");
                String forWho = rs.getString("sum_for_who");
                String createdAt_string = rs.getString("created_at");
                String deletedAt_string = rs.getString("deleted_at");
                String postCreatorName = rs.getString("UserName");
                String userProfilePhoto = rs.getString("profilePhoto");
                int postLikes = getPostLikes(postId);
                List<Comment> commentList = getPostComments(postId);
                List<String> userLikePostList = getPostLikesUsers(postId);

                allPostsList.add(new Post(postId, creatorId, postDescription, photoPath, forWho,createdAt_string, deletedAt_string, postCreatorName, userProfilePhoto, postLikes, userLikePostList, commentList));

                System.out.println("HGALO");
                for (String s : userLikePostList) {
                    System.out.println(s);
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Post post : allPostsList) {
            System.out.println(post);
        }
        return allPostsList;
    }




    public static List<String> getPostLikesUsers(String postId){
        List<String> peopleLikedPots = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT autor_id FROM `post_likes` WHERE post_id = (?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,postId);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String userId = rs.getString("autor_id");
                peopleLikedPots.add(userId);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return peopleLikedPots;
    }


    public static int getPostLikes(String postId){
        int postLikes = 0;
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT COUNT(*) as PostLikes FROM `post_likes` WHERE post_id = '"+postId+"'";
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                postLikes = rs.getInt("PostLikes");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return postLikes;
    }


    public static List<Comment> getPostComments(String postId){
        List<Comment> commentList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT post_comment.*, CONCAT(user.first_name, ' ', user.last_name) as userName, user.profilePhoto FROM `post_comment` INNER JOIN user ON user.user_id = post_comment.autor_id WHERE post_id = '"+postId+"' ORDER BY post_comment.created_at DESC";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int commentId = rs.getInt("id");
                String commentText = rs.getString("text");
                int autorId = rs.getInt("autor_id");
                String createdAt = rs.getString("created_at");
                String deletedAt = rs.getString("deleted_at");
                String userName = rs.getString("userName");
                int parentId = rs.getInt("parentId");
                String autorPhoto = rs.getString("profilePhoto");


                commentList.add(new Comment(commentId, commentText, parentId, autorId, createdAt, deletedAt, userName, autorPhoto));
            }

            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return commentList;
    }

    public static String removeLastCharacter(String str) {
        String result = null;
        if ((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 2);
        }
        return result;
    }
}

