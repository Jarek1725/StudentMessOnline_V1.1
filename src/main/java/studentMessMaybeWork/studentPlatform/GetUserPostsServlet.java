package studentMessMaybeWork.studentPlatform;

import com.google.gson.Gson;
import studentMessMaybeWork.studentPlatform.databaseEntities.Post;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.databaseEntities.userToReturn;
import studentMessMaybeWork.studentPlatform.databaseQueries.getUserPosts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/corridor/user/posts")
public class GetUserPostsServlet extends HttpServlet {
    
    private Gson gson = new Gson();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("name");
        String start = request.getParameter("start");
        String limit = request.getParameter("limit");


        HttpSession session=request.getSession(false);
        userToReturn userToWatch = (userToReturn) session.getAttribute("watchedUserDetails");
        List<Post> userMorePostsList = getUserPosts.getMoreUserPosts(userToWatch.getUserId(), start, limit);

        String userStringJson = this.gson.toJson(userMorePostsList);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userStringJson);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");

        HttpSession session=request.getSession(false);
        userToReturn userToWatch = (userToReturn) session.getAttribute("watchedUserDetails");
        List<Post> userPostsList = getUserPosts.getUserPosts(userToWatch.getUserId());

        String userStringJson = this.gson.toJson(userPostsList);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userStringJson);
        out.flush();
    }
}
