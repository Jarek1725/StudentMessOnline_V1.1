package studentMessMaybeWork.studentPlatform.databaseQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetFriendList {
    public static List<String> getFriendList(String userId){
        List<String> friendList = null;


        Connection conn = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        try {

            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT user_id FROM `user_contact` WHERE contact_id = (?)");
            preparedStatement.setString(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            friendList = new ArrayList<>();
            while (rs.next()){
                friendList.add(rs.getString("user_id"));
            }

            preparedStatement = conn.prepareStatement("SELECT contact_id FROM `user_contact` WHERE user_id = (?)");
            preparedStatement.setString(1, userId);
            while (rs.next()){
                friendList.add(rs.getString("contact_id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
        return friendList;
    }
}
