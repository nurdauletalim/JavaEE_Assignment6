import Model.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formUsername = request.getParameter("name");
        String formPassword = request.getParameter("password");
        PrintWriter out = response.getWriter();
        HttpSession userSession = request.getSession(false);


                if (formPassword.equals("admin")){
                    RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                }
                else if (UserDAO.validate(formUsername, formPassword)) {
                    Users user = new UserDAO().getUser(formUsername);
                    userSession = request.getSession(true);
                    userSession.setAttribute("userSession", formUsername);
                    userSession.setAttribute("userStatus", "true");
                    userSession.setAttribute("user", "user");
                    request.getSession().setAttribute("logged_user", user);
                    RequestDispatcher forwardUser = getServletContext().getRequestDispatcher("/PostsList.jsp");
                    forwardUser.include(request, response);
                } else {
                    userSession.setAttribute("status", "false");
                    out.print("Sorry username or password error");
                    RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
                    rd.include(request,response);
                }

            }
        }


