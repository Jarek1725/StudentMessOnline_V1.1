package studentMessMaybeWork.studentPlatform.databaseEntities;

import studentMessMaybeWork.studentPlatform.databaseQueries.GetUserClass;

import java.sql.*;

public class UserLogin {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentmess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";

    public boolean userDAO(String login, String password){
        Connection conn = null;
//        PreparedStatement stmt = null;
        boolean access = false;

        try {
            String pasFromDB = null;

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM user WHERE email = (?)";

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE email=?");

            stmt.setString(1,login);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pasFromDB = rs.getString("password");
            }

            if(pasFromDB.equals(password)){
                access = true;
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }finally {
            return access;
        }
    };

    public User getUser(String mail){
        Connection conn = null;
        PreparedStatement stmt = null;
        User user = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM user WHERE email = (?)";
            stmt = conn.prepareStatement("SELECT * FROM user WHERE email=?");
            stmt.setString(1, mail);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
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

                user = new User(id,classId, userClass,phoneNumber,email,fName,lName, birthDate, nationality, profilePhoto);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } ;

        return user;
    };

}
