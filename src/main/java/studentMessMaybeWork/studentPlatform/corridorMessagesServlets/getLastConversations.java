package studentMessMaybeWork.studentPlatform.corridorMessagesServlets;

import com.google.gson.Gson;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageEntities.lastConversation;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries.GetUserLastConversations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getLastConversations")
public class getLastConversations extends HttpServlet {

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        User loggedUser = (User) session.getAttribute("UserLogged");

        List<lastConversation> userList = GetUserLastConversations.getLastConversations(loggedUser.getUserId(), loggedUser.getClassId());

        String usersLastConversations = this.gson.toJson(userList);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(usersLastConversations);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
