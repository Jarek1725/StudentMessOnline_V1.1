package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.School;
import studentMessMaybeWork.studentPlatform.databaseEntities.Teacher;
import studentMessMaybeWork.studentPlatform.databaseEntities.UserClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserClass {
    public static UserClass getUserClass(String classId){
        UserClass userClass = null;

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM class WHERE id_class = (?)");
            preparedStatement.setString(1,classId);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                School schoolId = GetUserSchool.getUserSchool(rs.getString("school_id"));
                String className = rs.getString("className");

                userClass = new UserClass(classId, schoolId, className, GetClassTeacher.getClassTeacher(rs.getString("classTeacher")));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }

        return userClass;
    }
}
