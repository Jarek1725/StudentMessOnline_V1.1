package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.Grade;
import studentMessMaybeWork.studentPlatform.databaseEntities.UserSubjectGrades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class getUserGrades {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentmess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";

    public static List<UserSubjectGrades> userSubjects(String classId, String userId){
        List<UserSubjectGrades> userSubjectGrades = new ArrayList<>();

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT id, name FROM subject INNER JOIN (SELECT class_subjects.subjectId FROM class_subjects WHERE class_subjects.classId = (?)) s ON subject.id = s.subjectId";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, classId);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                int subjectId = rs.getInt("id");
                String subjectName = rs.getString("name");
                List<Grade> gradeList = userGrades(subjectId, userId);

                userSubjectGrades.add(new UserSubjectGrades(subjectId, subjectName, gradeList));
            }

            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userSubjectGrades;
    };

    private static List<Grade> userGrades(int subject_Id, String userId){
        List<Grade> userGradeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM `grades` WHERE grades.studentId = (?) AND grades.subjectId = (?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, subject_Id);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                int gradeId = rs.getInt("id");
                int teacherId = rs.getInt("teacher_id");
                int studentId = rs.getInt("studentId");
                int subjectId = rs.getInt("subjectId");
                int gradeValue = rs.getInt("grade");
                String gradeDescription = rs.getString("description");

                userGradeList.add(new Grade(gradeId, teacherId, studentId, subjectId, gradeValue, gradeDescription));

            }

            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userGradeList;
    }
}
