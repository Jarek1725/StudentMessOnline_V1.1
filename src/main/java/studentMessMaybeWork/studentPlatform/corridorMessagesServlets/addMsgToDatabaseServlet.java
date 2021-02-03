package studentMessMaybeWork.studentPlatform.corridorMessagesServlets;

import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries.AddMsgToDatabase;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries.isConversationWith;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addMsgToDatabaseServlet")
public class addMsgToDatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        User userLogged = (User) session.getAttribute("UserLogged");
        System.out.println("ASDFGRWD");
        if(userLogged.getUserId()!=null){
            System.out.println("PISGPJOKLEW");
            String secondUserId = request.getParameter("userId");
            if(isConversationWith.isConversation(userLogged.getUserId(), secondUserId)){
                String message = request.getParameter("message");
                System.out.println(message);
                System.out.println(secondUserId);
                AddMsgToDatabase.addMsgToDatabase(message, isConversationWith.getConvNumber(secondUserId, userLogged.getUserId()), userLogged.getUserId());
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
