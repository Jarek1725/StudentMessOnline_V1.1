package studentMessMaybeWork.studentPlatform;

import com.google.gson.Gson;
import studentMessMaybeWork.studentPlatform.databaseEntities.Post;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.databaseQueries.GetUserContacts;
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

@WebServlet("/test123")
public class test123 extends HttpServlet {

    private Gson gson = new Gson();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession(false);
        User userMorePostsList = (User) session.getAttribute("UserLogged");
//
//        List<String> userList = GetUserContacts.userFriendsFromSchoolId(userMorePostsList.getSchoolId(), userMorePostsList.getUserId());
//
//        String userStringJson = this.gson.toJson(userList);
//
//        PrintWriter out = response.getWriter();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        out.print(userStringJson);
//        out.flush();
    }
}
