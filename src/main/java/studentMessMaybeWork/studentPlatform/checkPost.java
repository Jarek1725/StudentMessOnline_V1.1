package studentMessMaybeWork.studentPlatform;

import com.google.gson.Gson;
import studentMessMaybeWork.studentPlatform.databaseEntities.Post;
import studentMessMaybeWork.studentPlatform.databaseQueries.GetSinglePost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/corridor/post")
public class checkPost extends HttpServlet {

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        Post post = GetSinglePost.getSinglePost(name);

        String postStringJson = this.gson.toJson(post);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(postStringJson);
        out.flush();


//        Post post1 = gson.fromJson(postStringJson, Post.class);
//
//        request.setAttribute("PostDetails", post);
//        RequestDispatcher rd = request.getRequestDispatcher("../post.jsp");
//        rd.include(request, response);

    }
}
