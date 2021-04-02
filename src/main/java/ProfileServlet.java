import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("Main.jsp").include(request, response);

        HttpSession session=request.getSession(false);
        String name=(String)session.getAttribute("name");
        if (name == null){
            out.print("Please login first");
            request.getRequestDispatcher("login.jsp").include(request, response);}
        else if(session!=null){
            out.print("Hello, "+name+" Welcome to Profile");
        }
        out.close();
    }
}  