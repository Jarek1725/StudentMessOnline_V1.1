package studentMessMaybeWork.studentPlatform;

import com.google.gson.Gson;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.databaseQueries.AllFriendsRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/checkFriendsRequest")
public class checkFriendsRequest extends HttpServlet {
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        User currentUser = (User) session.getAttribute("UserLogged");

        String userStringJson = this.gson.toJson(AllFriendsRequest.searchForUser(currentUser.getUserId(), currentUser.getClassId()));

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userStringJson);
        out.flush();
    }
}
