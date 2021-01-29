package studentMessMaybeWork.studentPlatform.databaseQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendRequest {

    public static void sendRequest(String userId, String secondUserId, int isRequest){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String sql = null;

        if(isRequest == 0){
            sql = "UPDATE user_contact SET accept = 1 WHERE user_id = (?) AND contact_id = (?)";
        } else if(isRequest == 1){
            sql = "DELETE FROM user_contact WHERE user_id = (?) AND contact_id = (?) OR user_id = (?) AND contact_id = (?)";
        } else if(isRequest == 2){
            sql = "INSERT INTO user_contact VALUES (NULL, (?), (?), 0)";
        }if(isRequest == 4){
            sql = "DELETE FROM user_contact WHERE user_id = (?) AND contact_id = (?)";
        }

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement(sql);

            if(isRequest == 0){
                preparedStatement.setString(1, secondUserId);
                preparedStatement.setString(2, userId);
            } else if(isRequest == 1){
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, secondUserId);
                preparedStatement.setString(3, secondUserId);
                preparedStatement.setString(4, userId);
            } else if(isRequest == 2){
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, secondUserId);
            }else if(isRequest == 4){
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, secondUserId);
            }


            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        DatabaseConnectionHelper.close(conn);
    }

    public static int isFriend(String userId, String secondUserId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        try {

            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM user_contact WHERE user_id = (?) AND contact_id = (?)");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, secondUserId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int accept = rs.getInt("accept");
                if(accept == 1){
                    return 1;
                }else{
                    return 4;
                }
            }

            preparedStatement1 = conn.prepareStatement("SELECT * FROM user_contact WHERE contact_id = (?) AND user_id = (?)");
            preparedStatement1.setString(1, userId);
            preparedStatement1.setString(2, secondUserId);


            ResultSet rs1 = preparedStatement1.executeQuery();

            while (rs1.next()){
                return rs1.getInt("accept");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }

        return 2;
    }
}
