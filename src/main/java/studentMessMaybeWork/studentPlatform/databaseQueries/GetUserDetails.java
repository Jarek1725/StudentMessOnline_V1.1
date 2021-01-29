package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.School;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.databaseEntities.UserClass;
import studentMessMaybeWork.studentPlatform.databaseEntities.userToReturn;

import java.sql.*;

public class GetUserDetails {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentmess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";

    public static User getUserDetails(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        User user = null;


        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT * FROM user WHERE user_id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String classId = rs.getString("class_id");
                UserClass userClass = GetUserClass.getUserClass(classId);
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("email");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String birthDate = rs.getString("data_urodzenia");
                String nationality = rs.getString("obywatelstwo");
                String profilePhoto = rs.getString("profilePhoto");

                user = new User(String.valueOf(id),classId, userClass,phoneNumber,email,fName,lName, birthDate, nationality, profilePhoto);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } ;

        return user;
    }

    public static userToReturn getToReturnUserDetails(int id, String currUserId, String currUserClass){
        Connection conn = null;
        PreparedStatement stmt = null;
        userToReturn user = null;


        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.prepareStatement("SELECT * FROM user WHERE user_id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String userId = String.valueOf(id);
                String classId = rs.getString("class_id");
                UserClass userClass = GetUserClass.getUserClass(classId);
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("email");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String birthDate = rs.getString("data_urodzenia");
                String nationality = rs.getString("obywatelstwo");
                String profilePhoto = rs.getString("profilePhoto");

                int is_friend = FriendRequest.isFriend(currUserId, userId);
                int isSameClass = isSameClass(userId, currUserId, currUserClass);

                user = new userToReturn(userId,classId, userClass,phoneNumber,email,fName,lName, birthDate, nationality, profilePhoto, is_friend, isSameClass);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

//    private static int isFriend(String userId, String friendId){
//
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
//
//        try{
//            conn = DatabaseConnectionHelper.getConnection();
//            preparedStatement = conn.prepareStatement("SELECT * FROM `user_contact` WHERE user_contact.user_id = (?) and user_contact.contact_id = (?) OR user_contact.contact_id = (?) AND user_contact.user_id = (?)");
//            preparedStatement.setString(1, userId);
//            preparedStatement.setString(2, friendId);
//            preparedStatement.setString(3, userId);
//            preparedStatement.setString(4, friendId);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()){
//                int accept = rs.getInt("accept");
//                if(accept==1){
//                    return 1;
//                } else{
//                    return 2;
//                }
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return 0;
//    }

    public static int isSameClass(String userId, String friendId, String currUserClass){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int counter = 0;


        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM user WHERE user.user_id IN((?),(?)) and user.class_id = (?)");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, friendId);
            preparedStatement.setString(3, currUserClass);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                counter++;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(counter==2){
            return 1;
        } else{
            return 0;
        }

    }

}
