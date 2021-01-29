package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.School;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserSchool {
    public static School getUserSchool(String schoolId){
        School userSchool = null;

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM school WHERE id = (?)");
            preparedStatement.setString(1, schoolId);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                String schoolName = rs.getString("school_name");
                String school_short = rs.getString("school_address");
                String schoolAddress = rs.getString("school_address");

                userSchool = new School(schoolId, schoolName, school_short, schoolAddress);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }

        return userSchool;
    }
}
