import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetListOfPostServlet")
public class GetListOfPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDAO postDao = new PostDAO();
        List<?> posts = null;
        try {
            posts = postDao.getAllPost();
        } catch (Exception e) {
            request.getSession().setAttribute("exception",e.getMessage());
        }
        request.getSession().setAttribute("posts",posts);
        response.sendRedirect("Posts.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }
}
