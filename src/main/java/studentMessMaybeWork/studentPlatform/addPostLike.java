package studentMessMaybeWork.studentPlatform;

import studentMessMaybeWork.studentPlatform.databaseEntities.CurrentUser;
import studentMessMaybeWork.studentPlatform.databaseQueries.AddOrDeletePostLike;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addPostLike")
public class addPostLike extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        String postId = request.getParameter("post_id");
        String userId = (String) session.getAttribute("UserLoggedId");

        AddOrDeletePostLike.isUserLikePost(userId, postId);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
