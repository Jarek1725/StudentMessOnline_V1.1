package studentMessMaybeWork.studentPlatform;

import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.databaseQueries.DeletePost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deletePost")
public class deletePost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postId = request.getParameter("postId");

        HttpSession session=request.getSession(false);
        User userLogged = (User) session.getAttribute("UserLogged");

        DeletePost.deletePost(postId, userLogged.getUserId());

        response.sendRedirect("corridor");
    }
}
