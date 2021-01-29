package studentMessMaybeWork.studentPlatform;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/messagesCorridor")
public class messagesCorridor extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("UserLogged") == null){
            response.sendRedirect(request.getContextPath());
        }else{
            response.setContentType("text/html");
            RequestDispatcher rd = request.getRequestDispatcher("/messangerForum.jsp");
            rd.include(request, response);
        }
    }
}
