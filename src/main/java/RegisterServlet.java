import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/PhoneStore";
    static final String DATABASE_USER = "postgres";
    static final String DATABASE_PASSWORD = "123";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n=request.getParameter("name");
        String p=request.getParameter("password");
        String e=request.getParameter("email");

        try{
            Class.forName(JDBC_DRIVER);
            Connection con= DriverManager.getConnection(
                    DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);

            PreparedStatement ps=con.prepareStatement(
                    "insert into users(name,pass,email) values(?,?,?)");

            ps.setString(1,n);
            ps.setString(2,p);
            ps.setString(3,e);

            int i=ps.executeUpdate();
            if(i>0){
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(30*60);
                session.setAttribute("name",n);
                out.print("You are successfully registered...");
                request.getRequestDispatcher("Main.jsp").include(request,response);}
        }catch (Exception e2) {System.out.println(e2);}

        out.close();
    }

}
