package studentMessMaybeWork.studentPlatform;

import studentMessMaybeWork.studentPlatform.databaseEntities.*;
import studentMessMaybeWork.studentPlatform.databaseQueries.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/studentPlatformServlet")
public class studentPlatformServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userMail = request.getParameter("userMail");
        String password = request.getParameter("userPassword");
        HttpSession session = request.getSession();

        UserLogin userLogin = new UserLogin();
        if(userLogin.userDAO(userMail, password)){
            User currentUser = userLogin.getUser(userMail);

            getUserExams getUserExams = new getUserExams();
            List<Exam> userExams = getUserExams.userExams(currentUser.getClassId());

            List<UserSubjectGrades> userSubjectGradesList = getUserGrades.userSubjects(currentUser.getClassId(), currentUser.getUserId());

            List<LessonPlan> userLessonPlan = getUserLessonPlan.userLessonPlan(currentUser.getClassId());

            List<Homework> userHomeworks = GetUserHomeworks.userHomeworks(currentUser.getClassId());
            Collections.sort(userHomeworks);
            Map<Object, List<Homework>> homeworkDates = userHomeworks.stream().collect(Collectors.groupingBy(Homework::getHomeworkDate));
            HashMap<String, Date> dateNameList = new HashMap<>();


            session.setAttribute("UserLogged", currentUser);
            session.setAttribute("UserLoggedId", currentUser.getUserId());
            session.setAttribute("UserLessonPlan", userLessonPlan);
            session.setAttribute("UserExams", userExams);
            session.setAttribute("UserGrades", userSubjectGradesList);
            session.setAttribute("UserHomeworks", userHomeworks);
            session.setAttribute("UserHomeworksDates", homeworkDates);

            response.sendRedirect("studentLoggedIn");
        } else{
            session.invalidate();
            RequestDispatcher rd = request.getRequestDispatcher("/studentPlatform/studentPlatformLogin.jsp");
            rd.include(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher rd = request.getRequestDispatcher("/studentPlatform/studentPlatformLogin.jsp");
        rd.include(request, response);
    }

}
