package studentMessMaybeWork.studentPlatform;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/corridor")
public class Corridor extends HttpServlet {
    public static final String POLICY = "default-src 'self' data: gap: https://*.googleapis.com/ https://*.fontawesome.com/ https://*.gstatic.com ; style-src 'self' 'unsafe-inline' https://fonts.googleapis.com/ https://*.fontawesome.com/; media-src *; img-src 'self' data: content:; script-src: 'self' ";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("watchedUserDetails");
        if(session.getAttribute("UserLogged") == null){
            response.sendRedirect("/StudentMessWebsiteV1_war_exploded/");
        }else{
            ((HttpServletResponse)response).setHeader("Content-Security-Policy", POLICY);
            response.setContentType("text/html");
            RequestDispatcher rd = request.getRequestDispatcher("/corridor.jsp");
            rd.include(request, response);
        }
    }
}
