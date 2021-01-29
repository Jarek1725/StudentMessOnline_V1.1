package studentMessMaybeWork.studentPlatform;

import com.google.gson.Gson;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.databaseEntities.UserLogin;
import studentMessMaybeWork.studentPlatform.databaseEntities.userToReturn;
import studentMessMaybeWork.studentPlatform.databaseQueries.GetUserDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/corridor/user")
public class userDetails extends HttpServlet {

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("name"));

        HttpSession session=request.getSession(false);
        User currUser = (User) session.getAttribute("UserLogged");

        String currUserId = currUser.getUserId();
        String currUserClass = currUser.getClassId();

        userToReturn user = GetUserDetails.getToReturnUserDetails(userId, currUserId, currUserClass);

        if(currUser.getUserId().equals(user.getUserId())){
            user.setIsFriend(3);
        }

        session.setAttribute("watchedUserDetails", user);

        String userStringJson = this.gson.toJson(user);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userStringJson);
        out.flush();


    }
}
