package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.School;
import studentMessMaybeWork.studentPlatform.databaseEntities.Teacher;
import studentMessMaybeWork.studentPlatform.databaseEntities.UserClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetClassTeacher {
    public static Teacher getClassTeacher(String teacherId){
        Teacher classTeacher = null;

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM teacher WHERE id = (?)");
            preparedStatement.setString(1,teacherId);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){

                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                classTeacher = new Teacher(teacherId, firstName, lastName);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }

        return classTeacher;
    }
}
