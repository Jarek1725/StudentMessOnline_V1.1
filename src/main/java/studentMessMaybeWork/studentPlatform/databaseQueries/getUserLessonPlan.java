package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.LessonPlan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class getUserLessonPlan {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentmess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";

    public static List<LessonPlan> userLessonPlan(String classId){
        String idofClass = String.valueOf(classId);
        List<LessonPlan> userLessons = new ArrayList<>();

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

//            String sql = "SELECT * FROM `lesson_plan` HAVING class_id = '"+idofClass+"' ";
            String sql = "SELECT * FROM `lesson_plan` HAVING class_id = (?) ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idofClass);
//            preparedStatement.setInt(1, classId);
            ResultSet rs = preparedStatement.executeQuery();

            getUserExams getUserExams = new getUserExams();

            while(rs.next()){
                int subjectId = rs.getInt("subject_id");
                int day = rs.getInt("day");
                int lessonNumber = rs.getInt("lesson_number");
                int teacherId = rs.getInt("teacher_id");
                String teacherName = getUserExams.getTeacherName(teacherId);
                String subjectName = getUserExams.getSubjectName(subjectId);


                userLessons.add(new LessonPlan(subjectId, classId, day, lessonNumber, teacherId, teacherName, subjectName));
            }

            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userLessons;
    }
}
