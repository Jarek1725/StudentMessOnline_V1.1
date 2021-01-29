package studentMessMaybeWork.studentPlatform;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import studentMessMaybeWork.studentPlatform.databaseEntities.User;
import studentMessMaybeWork.studentPlatform.databaseQueries.savePost;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;


@WebServlet("/addPost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class addPost extends HttpServlet {

    public static final String UPLOAD_DIR = "img\\userUploadedImages";
    public String dbFileName = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String[] for_who = request.getParameterValues("for_who");
        int sum_for_who = 0;


        if(for_who!=null){
            for (String s : for_who) {
                int n = Integer.parseInt(s);
                sum_for_who +=n;
            }
        }

        Part part = request.getPart("photo");//
        String postDescription = request.getParameter("post_desrciption");

        String fileName = part.getSubmittedFileName();

        String fileNameExt = FilenameUtils.getExtension(fileName);


        if (postDescription.length()>0){
            if(fileName.length()>0){
                if(fileNameExt.equals("jpg")){
                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    uuid+=".jpg";

                    String applicationPath = getServletContext().getRealPath("");

                    String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
                    File fileUploadDirectory = new File(uploadPath);
                    if (!fileUploadDirectory.exists()) {
                        fileUploadDirectory.mkdirs();
                    }
                    String savePath = "C:\\MinePrograms\\Java\\ServletyTestowanie\\StudentMessOnline_V1\\src\\main\\webapp\\img\\userUploadedImages\\" + uuid;
                    String sRootPath = new File(savePath).getAbsolutePath();
                    part.write(savePath + File.separator);
                    File fileSaveDir1 = new File(savePath);
                    dbFileName = UPLOAD_DIR + File.separator + uuid;
                    part.write(savePath + File.separator);

                    User user = (User) session.getAttribute("UserLogged");

                    savePost.savePost(user.getUserId(), postDescription, uuid, sum_for_who);

                }else {
                    System.out.println("ONLY JPG FILE");
                }
            } else{
                String uuid = null;
                postDescription = request.getParameter("post_desrciption");
                User user = (User) session.getAttribute("UserLogged");

                savePost.savePost(user.getUserId(), postDescription, uuid, sum_for_who);
            }
        }

        response.sendRedirect("corridor");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
