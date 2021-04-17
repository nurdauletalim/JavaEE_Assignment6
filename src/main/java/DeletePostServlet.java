import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postId = request.getParameter("postId");
        if(postId != null && !(postId.equals(""))){
            boolean status = false;
            try {
                status = new PostDAO().delete(Integer.parseInt(postId));
            } catch (Exception e) {
                request.getSession().setAttribute("exception",e.getMessage());
            }
            if(status){
                request.getSession().setAttribute("message", "Selected Movie has been deleted Successfully.");
                response.sendRedirect("PostsList.jsp");
            }else{
                request.getSession().setAttribute("message", "Error !!!! Selected Movie has not been deleted !!!");
            }
        }else{
            request.getSession().setAttribute("message", "Error !!!! Please select one Category!!!!!!");
            response.sendRedirect("PostsList.jsp");
        }
    }
}
