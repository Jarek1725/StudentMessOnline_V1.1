package studentMessMaybeWork.studentPlatform;

import com.google.gson.Gson;
import studentMessMaybeWork.studentPlatform.databaseEntities.CurrentUser;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/studentLoggedIn")
public class studentLoggedIn extends HttpServlet {

    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        String userStringJson = this.gson.toJson(session.getAttribute("UserLogged"));
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userStringJson);
        out.flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("UserLogged") == null){
            response.sendRedirect(request.getContextPath());
        }else{
            response.setContentType("text/html");
            RequestDispatcher rd = request.getRequestDispatcher("/studentPlatform/LoggedStudent.jsp");
            rd.include(request, response);
        }
    }
}
