package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.Homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetUserHomeworks {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentmess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";

    public static List<Homework> userHomeworks(String classId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<Homework> userHomeworksToReturn = new ArrayList<>();
        getUserExams getUserExams = new getUserExams();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM `homework` WHERE class_id = (?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, classId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int homewordId = rs.getInt("id");
                int teacherId = rs.getInt("teacher_id");
                String homeworkDescription = rs.getString("description");
                Date homeworkDate = rs.getDate("date");
                int subjectId = rs.getInt("subject_id");
                String subjectName = getUserExams.getSubjectName(subjectId);


                userHomeworksToReturn.add(new Homework(homewordId, teacherId, classId, homeworkDescription, homeworkDate, subjectId, subjectName));
            }

            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userHomeworksToReturn;
    }

}
