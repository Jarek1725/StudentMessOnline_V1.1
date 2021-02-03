package studentMessMaybeWork.studentPlatform.corridorMessagesServlets;

import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries.StartConversationWith;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries.isConversationWith;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/startConversation")
public class startConversation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        User currentUser = (User) session.getAttribute("UserLogged");
        String startConversationWith = request.getParameter("userId");

        if(!isConversationWith.isConversation(currentUser.getUserId(), startConversationWith)){
            System.out.println(startConversationWith);
            StartConversationWith.startConversationWith(currentUser.getUserId(), startConversationWith);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
