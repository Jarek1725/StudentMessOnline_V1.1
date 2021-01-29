package studentMessMaybeWork.studentPlatform.databaseQueries;

import java.sql.*;

public class AddOrDeletePostLike {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentmess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";


    public static void isUserLikePost(String userId, String postId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT post_likes.id FROM post_likes WHERE post_likes.post_id = (?) and post_likes.autor_id = (?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, postId);
            preparedStatement.setString(2, userId);

            ResultSet rs = preparedStatement.executeQuery();

            String isUserAlreadyLikePost = null;

            while(rs.next()){
                isUserAlreadyLikePost = rs.getString("id");
            }

            PreparedStatement statement = null;
            String addOrDelete = null;
            if(isUserAlreadyLikePost == null){
                statement  = conn.prepareStatement("INSERT INTO post_likes VALUES (NULL, (?), (?))");
            } else{
                statement  = conn.prepareStatement("DELETE FROM post_likes WHERE post_likes.post_id = (?) AND post_likes.autor_id = (?)");
            }

            statement.setString(1, postId);
            statement.setString(2, userId);

            int row = statement.executeUpdate();
//            statement = conn.createStatement();
//            statement.executeUpdate(addOrDelete);

            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
