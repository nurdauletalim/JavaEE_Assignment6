import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html");
            PrintWriter out = servletResponse.getWriter();
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            String name=servletRequest.getParameter("name");
            String p=servletRequest.getParameter("password");


            if(p.equals("admin")){
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else if(UserDAO.validate(name, p)){
                HttpSession session = req.getSession();
                session.setMaxInactiveInterval(30*60);
                session.setAttribute("name",name);
                RequestDispatcher rd=servletRequest.getRequestDispatcher("Main.jsp");
                rd.forward(servletRequest,servletResponse);
            }
            else{
                out.print("Sorry username or password error");
                RequestDispatcher rd=servletRequest.getRequestDispatcher("Login.jsp");
                rd.include(servletRequest,servletResponse);
            }
            out.close();
        }


    @Override
    public void destroy() {

    }
}

