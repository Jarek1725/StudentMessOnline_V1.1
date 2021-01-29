package studentMessMaybeWork.studentPlatform;

import studentMessMaybeWork.studentPlatform.databaseEntities.CurrentUser;
import studentMessMaybeWork.studentPlatform.databaseQueries.AddComment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addCommentToPost")
public class addCommentToPost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession(false);

        String post_id = request.getParameter("Comment_details").substring(0, 32);
        String commentText = request.getParameter("Comment_details").substring(32);

        AddComment.addComment(post_id, commentText, String.valueOf(session.getAttribute("UserLoggedId")), 0);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
