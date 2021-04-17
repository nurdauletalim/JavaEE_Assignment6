import Model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditPostServlet")
public class EditPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postId = (int) request.getSession().getAttribute("postId");
        String theme= request.getParameter("topic");
        String text = request.getParameter("text");
        Post post = new Post();
        HttpSession adminSession = request.getSession(false);

        boolean status = false;
        PrintWriter out = response.getWriter();
        if(!theme.equals("") && !text.equals("") && postId != 0){
            post.setId(postId);
            post.setTopic(theme);
            post.setText(text);
            try {
                status = new PostDAO().editPost(post);
            } catch (Exception e) {
                adminSession.setAttribute("exception",e);
                e.printStackTrace();
            }
            if(status){
                response.sendRedirect("PostsList.jsp");
            }else{
                response.sendRedirect("PostsList.jsp");
            }
        }else{
            out.println("<script>alert('Try again')</script>");
            response.sendRedirect("PostList.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postId = request.getParameter("postId");
        Post post = new Post();

        if(postId != null && !(postId.equals(""))){
            try {
                post = new PostDAO().getPostByID(Integer.parseInt(postId));
            } catch (Exception e) {
                request.getSession().setAttribute("exception",e.getMessage());
            }
            request.getSession().setAttribute("post", post);
            request.getSession().setAttribute("postId", post.getId());
            response.sendRedirect("EditPost.jsp");
        }else{
            response.sendRedirect("PostsList.jsp");
        }
    }
}
