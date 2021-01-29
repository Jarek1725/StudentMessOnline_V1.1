package studentMessMaybeWork.studentPlatform.databaseQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.SchoolAlert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetSchoolAlerts {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentmess?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "";

    public static List<SchoolAlert> getAllSchoolAlerts(){
        List<SchoolAlert> schoolAlertList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM `school_alerts` ORDER BY `school_alerts`.`added_date` DESC";

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int alertId = rs.getInt("id");
                String alertHeader = rs.getString("header");
                String alertDescription = rs.getString("description");
                int teacherAdded = rs.getInt("teacher_added");

                schoolAlertList.add(new SchoolAlert(alertId, alertHeader, alertDescription, teacherAdded));
            }

            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return schoolAlertList;
    }
}
