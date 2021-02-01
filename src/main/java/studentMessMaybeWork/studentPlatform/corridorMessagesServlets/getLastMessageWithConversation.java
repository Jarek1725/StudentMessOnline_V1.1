package studentMessMaybeWork.studentPlatform.corridorMessagesServlets;

import com.google.gson.Gson;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageEntities.MessageEntity;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries.GetLastMessagesInConversation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getLastMessageWithConversation")
public class getLastMessageWithConversation extends HttpServlet {
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        User user = (User) session.getAttribute("UserLogged");
        String secondUserId = request.getParameter("userId");
        String lastPosition = request.getParameter("message_position");
        if(user!=null){
            System.out.println(secondUserId);
            System.out.println(lastPosition);

            List<MessageEntity> lastMessage = GetLastMessagesInConversation.getLastMessagesInConversation(user.getUserId(), secondUserId, Integer.parseInt(lastPosition));

            String userStringJson = this.gson.toJson(lastMessage);

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(userStringJson);
            out.flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        User user = (User) session.getAttribute("UserLogged");
        String secondUserId = request.getParameter("userId");
        String lastPosition = request.getParameter("message_position");
        if(user!=null){

            System.out.println(secondUserId);
            System.out.println(lastPosition);

            List<MessageEntity> lastMessage = GetLastMessagesInConversation.getLastMessagesInConversation(user.getUserId(), secondUserId, Integer.parseInt(lastPosition));

            String userStringJson = this.gson.toJson(lastMessage);

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(userStringJson);
            out.flush();
        }
    }
}
