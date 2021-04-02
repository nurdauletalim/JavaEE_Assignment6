import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        request.getRequestDispatcher("Main.jsp").include(request, response);

        HttpSession session=request.getSession(false);

        if(session!=null){
            String name=(String)session.getAttribute("name");
            session.invalidate();

            out.print("You are successfully logged out!");}
        else {
            out.print("Please login first!");
            request.getRequestDispatcher("Login.jsp").include(request, response);
        }
        out.close();
    }
}
