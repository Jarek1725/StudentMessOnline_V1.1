package studentMessMaybeWork.studentPlatform.databaseQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsPostInDB {
    public static boolean isPostInDB(String postId){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try{
            con=DatabaseConnectionHelper.getConnection();
            preparedStatement = con.prepareStatement("SELECT * FROM `posts` WHERE posts.id = (?)");
            preparedStatement.setString(1, postId);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                row = 1;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        DatabaseConnectionHelper.close(con);

        return row==1;
    }
}
