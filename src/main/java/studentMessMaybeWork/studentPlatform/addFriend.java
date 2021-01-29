package studentMessMaybeWork.studentPlatform;

import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.databaseEntities.userToReturn;
import studentMessMaybeWork.studentPlatform.databaseQueries.FriendRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/corridor/addFriend")
public class addFriend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession(false);
        userToReturn userToWatch = (userToReturn) session.getAttribute("watchedUserDetails");
        User userMorePostsList = (User) session.getAttribute("UserLogged");

        if(!userToWatch.getUserId().equals(userMorePostsList.getUserId())){
            int accept = FriendRequest.isFriend(userMorePostsList.getUserId(), userToWatch.getUserId());
            FriendRequest.sendRequest(userMorePostsList.getUserId(), userToWatch.getUserId(), accept);
        }
    }
}
