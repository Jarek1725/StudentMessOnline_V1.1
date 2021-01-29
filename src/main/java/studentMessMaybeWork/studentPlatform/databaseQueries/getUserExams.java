package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.Exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class getUserExams {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentmess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";

    public List<Exam> userExams(String classId){
        List<Exam> userExamList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM `exam` WHERE class_id = (?) AND date >= cast(CURRENT_TIMESTAMP as date ) ORDER BY date, hour";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, classId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int teacherId = rs.getInt("teacher_id");
                int subjectId = rs.getInt("subject");
                String description = rs.getString("description");
                String localDate = rs.getString("date");
                String localTime = rs.getString("hour");

                Exam exam = new Exam(teacherId, classId, subjectId, description, localDate, localTime, getSubjectName(subjectId), getTeacherName(teacherId));

                userExamList.add(exam);
            }

            conn.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return userExamList;
    }

    public String getTeacherName(int teacherId){
        String teacherName = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT CONCAT(first_name,\" \", last_name) as teacherFullName FROM `teacher` WHERE id = (?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, teacherId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                teacherName = rs.getString("teacherFullName");
            }

            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return teacherName;
    }

    public String getSubjectName(int subjectId){
        String subjectName = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT name FROM `subject` WHERE id = (?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, subjectId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                subjectName = rs.getString("name");
            }

            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return subjectName;
    }
}
