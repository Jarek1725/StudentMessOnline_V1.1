package studentMessMaybeWork.studentPlatform.databaseQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetUserContacts {

    public static List<String> userFriendList(String userId){
        List<String> friendList = null;

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            String sql = "SELECT contact_id FROM `user_contact` where user_id = (?) and `accept` = 1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);

            ResultSet rs = stmt.executeQuery();

            friendList = new ArrayList<>();

            while (rs.next()){
                friendList.add(rs.getString("contact_id"));
            }

            sql = "SELECT user_id FROM `user_contact` where contact_id = (?) and `accept` = 1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);

            rs = stmt.executeQuery();

            while (rs.next()){
                friendList.add(rs.getString("user_id"));
            }

            DatabaseConnectionHelper.close(conn);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (String s : friendList) {
            System.out.println(s);
        }

        friendList.add(userId);
        return friendList;
    }

    public static List<String> getUserClassFriends(String userClassId, String userId){
        List<String> classUsersId = null;

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            String sql = "SELECT user_id FROM `user` WHERE class_id = (?) AND user_id != (?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userClassId);
            stmt.setString(2, userId);

            ResultSet rs = stmt.executeQuery();

            classUsersId = new ArrayList<>();

            while(rs.next()){
                classUsersId.add(rs.getString("user_id"));
            }

            DatabaseConnectionHelper.close(conn);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        classUsersId.add(userId);

        return classUsersId;
    }

    public static List<String> userFriendsFromSchoolId(String userSchoolId, String userId){
        List<String> userSchoolFriendList = null;

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            String sql = "SELECT user.user_id FROM `user`\n" +
                        "INNER JOIN class ON class.id_class = user.class_id\n" +
                        "INNER JOIN school ON school.id = class.school_id\n" +
                        "WHERE school.id = (?) AND user.user_id != (?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userSchoolId);
            stmt.setString(2, userId);

            ResultSet rs = stmt.executeQuery();

            userSchoolFriendList = new ArrayList<>();

            while(rs.next()){
                userSchoolFriendList.add(rs.getString("user_id"));
            }

            DatabaseConnectionHelper.close(conn);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        userSchoolFriendList.add(userId);

        return userSchoolFriendList;
    }

}
