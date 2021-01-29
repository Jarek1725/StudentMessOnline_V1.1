package studentMessMaybeWork.studentPlatform.databaseQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class savePost {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentmess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";
    public static void savePost(String creatorId, String description, String photoPath, int sum_for_who) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String uuidForPost = UUID.randomUUID().toString().replace("-", "");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO `posts` (`id`, `creator_id`, `description`, `photo`, `sum_for_who`, `created_at`, `deleted_at`) VALUES ((?), (?), (?), (?), (?), NOW(), NULL);";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString((1), uuidForPost);
            preparedStatement.setString(2, creatorId);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, photoPath);
            preparedStatement.setInt(5, sum_for_who);

            int row = preparedStatement.executeUpdate();

            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
