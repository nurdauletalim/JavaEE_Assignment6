import Model.Comment;
import Model.Post;
import Model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "PostDetailServlet")
public class PostDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Comment newComment = new Comment();


        boolean status = false;
        PostDAO dao = new PostDAO();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String comment = request.getParameter("comment");
        Post post = (Post) request.getSession().getAttribute("post");
        HttpSession userSession = request.getSession(false);
        Users user = (Users) request.getSession().getAttribute("logged_user");
        dao.updateLikes(post);
        if(!comment.equals("")){
            newComment.setPostId(post.getId());
            newComment.setComment(comment);
            newComment.setUserName(user.getName());
            try {
                status = dao.addComment(newComment);
            } catch (Exception e) {
                userSession.setAttribute("exception",e);
                e.printStackTrace();
            }
            if(status){
                response.sendRedirect("PostDetailAuth.jsp");
            }else{
                response.sendRedirect("PostsList.jsp");
            }
        }
        else{
            out.println("<script>alert('Try again!')</script>");
            response.sendRedirect("PostDetailAuth.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postId = request.getParameter("postId");
        List<?> comments = null;
        Post post = new Post();

        if(postId != null && !(postId.equals(""))){
            try {
                post = new PostDAO().getPostByID(Integer.parseInt(postId));
                comments = new PostDAO().getComments(post.getId());
            } catch (Exception e) {
                request.getSession().setAttribute("exception",e.getMessage());
            }
            request.getSession().setAttribute("post", post);
            request.getSession().setAttribute("comments",comments);
            response.sendRedirect("PostDetailAuth.jsp");
        }else{
            response.sendRedirect("Posts.jsp");
        }
    }
}
