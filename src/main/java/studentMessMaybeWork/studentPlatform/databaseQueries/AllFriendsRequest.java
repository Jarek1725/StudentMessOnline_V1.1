package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.UserClass;
import studentMessMaybeWork.studentPlatform.databaseEntities.userToReturn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllFriendsRequest {
    public static List<userToReturn> searchForUser(String currentUserId, String currUserClass){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<userToReturn> userList = null;
        userToReturn user= null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM `user_contact` INNER JOIN user ON user.user_id = user_contact.user_id WHERE contact_id = ? AND accept = 0");
            preparedStatement.setString(1, currentUserId);

            ResultSet rs = preparedStatement.executeQuery();

            System.out.println(preparedStatement);

            userList = new ArrayList<>();
            while(rs.next()){
                String id = rs.getString("user_id");
                String classId = rs.getString("class_id");
                UserClass userClass = GetUserClass.getUserClass(classId);
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("email");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String birthDate = rs.getString("data_urodzenia");
                String nationality = rs.getString("obywatelstwo");
                String profilePhoto = rs.getString("profilePhoto");

                int is_friend = FriendRequest.isFriend(currentUserId, id);
                int isSameClass = GetUserDetails.isSameClass(id, currentUserId, currUserClass);

                user = new userToReturn(id,classId, userClass,phoneNumber,email,fName,lName, birthDate, nationality, profilePhoto, is_friend, isSameClass);
                userList.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
        return userList;
    }
}
