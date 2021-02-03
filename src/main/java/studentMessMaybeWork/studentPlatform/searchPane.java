package studentMessMaybeWork.studentPlatform;

import com.google.gson.Gson;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.databaseEntities.userToReturn;
import studentMessMaybeWork.studentPlatform.databaseQueries.SearchForUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/searchPane")
public class searchPane extends HttpServlet {

    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchUser = request.getParameter("searchString");

        HttpSession session=request.getSession(false);
        User currentUser = (User) session.getAttribute("UserLogged");

        List<userToReturn> userList = SearchForUser.searchForUser(searchUser, currentUser.getUserId(), currentUser.getClassId());

        String usersStringJson = this.gson.toJson(userList);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(usersStringJson);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
