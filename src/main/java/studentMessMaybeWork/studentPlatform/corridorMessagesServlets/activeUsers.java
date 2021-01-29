package studentMessMaybeWork.studentPlatform.corridorMessagesServlets;

import WebSocketServer.ChatServerClass;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.databaseQueries.GetFriendList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/activeUsers")
public class activeUsers extends HttpServlet {

    private Gson gson = new Gson();
    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> activeUsers = ChatServerClass.getActiveUsers();

        HttpSession session=request.getSession(false);
        User userLogged = (User) session.getAttribute("UserLogged");

        List<String> userFriends = GetFriendList.getFriendList(userLogged.getUserId());

        userFriends.retainAll(activeUsers);

        String userStringJson = this.gson.toJson(userFriends);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userStringJson);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> activeUsers = ChatServerClass.getActiveUsers();
        List<String> activeUsersId = new ArrayList<>();
        for (String activeUser : activeUsers) {
            Claims test = decodeJWT(activeUser);
            String userId = (String) test.get("userId");
            activeUsersId.add(userId);
            System.out.println(userId);
        }

        System.out.println(activeUsers);

        HttpSession session=request.getSession(false);
        User userLogged = (User) session.getAttribute("UserLogged");

        List<String> userFriends = GetFriendList.getFriendList(userLogged.getUserId());

        userFriends.retainAll(activeUsersId);

        String userStringJson = this.gson.toJson(userFriends);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userStringJson);
        out.flush();
    }


    public static Claims decodeJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}

