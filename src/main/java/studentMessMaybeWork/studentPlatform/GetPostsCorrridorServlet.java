package studentMessMaybeWork.studentPlatform;

import com.google.gson.Gson;
import studentMessMaybeWork.studentPlatform.databaseEntities.Post;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.databaseEntities.UserClass;
import studentMessMaybeWork.studentPlatform.databaseQueries.GetAllPosts;
import studentMessMaybeWork.studentPlatform.databaseQueries.GetUserContacts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/corridor/posts")
public class GetPostsCorrridorServlet extends HttpServlet {

    private static LocalDateTime loggedTime = null;

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = loggedTime.format(formatter);

        int start = Integer.parseInt(request.getParameter("start"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        HttpSession session=request.getSession(false);
        User currentUser = (User) session.getAttribute("UserLogged");

        List<Post> allPosts = GetAllPosts.getAllPostsInRange(start,limit, formatDateTime, GetUserContacts.userFriendList(currentUser.getUserId()), GetUserContacts.getUserClassFriends(currentUser.getClassId(), currentUser.getUserId()), GetUserContacts.userFriendsFromSchoolId(currentUser.getUserClass().getClassSchool().getSchoolId(), currentUser.getUserId()), currentUser.getUserId());

        String allPostJson = gson.toJson(allPosts);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(allPostJson);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loggedTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = loggedTime.format(formatter);

        HttpSession session=request.getSession(false);
        User currentUser = (User) session.getAttribute("UserLogged");



        List<Post> allPosts = GetAllPosts.getAllPostsInRange(0,5, formatDateTime, GetUserContacts.userFriendList(currentUser.getUserId()), GetUserContacts.getUserClassFriends(currentUser.getClassId(), currentUser.getUserId()), GetUserContacts.userFriendsFromSchoolId(currentUser.getUserClass().getClassSchool().getSchoolId(), currentUser.getUserId()), currentUser.getUserId());

        String allPostJson = gson.toJson(allPosts);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(allPostJson);
        out.flush();

    }
}
