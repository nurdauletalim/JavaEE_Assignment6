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

@WebServlet(name = "AddNewPostServlet")
public class AddNewPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Post post = new Post();
        boolean status = false;
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String topic = request.getParameter("topic");
        String text = request.getParameter("text");
        HttpSession adminSession = request.getSession(false);
        Users user = (Users) request.getSession().getAttribute("logged_user");

        if(!topic.equals("") && !text.equals("")){
            post.setTopic(topic);
            post.setText(text);
            post.setLike(0);
            post.setDislike(0);
            post.setUserId(user.getId());
            status = new PostDAO().newPost(post);
            if(status){
                out.println("<script>alert('Model.Post successfully added!')</script>");
                adminSession.setAttribute("message","Model.Post successfully added!" );
                response.sendRedirect("PostsList.jsp");
            }else{
                out.println("<script>alert('Error ! This kind of post already exists in the base')</script>");
                adminSession.setAttribute("message","Error ! Try again!" );
                response.sendRedirect("PostsList.jsp");
            }
        }else{
            out.println("<script>alert('Try again !')</script>");
            response.sendRedirect("PostsList.jsp");
        }
    }

}
