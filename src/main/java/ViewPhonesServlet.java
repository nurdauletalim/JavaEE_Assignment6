import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ViewPhonesServlet")
public class ViewPhonesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<a href='adminPhones.jsp'>Add New Phone</a>");
        out.println("<h1>Phones List</h1>");

        List<Phones> list=PhonesDAO.getAllPhones();

        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Name</th><th>Price</th><th>Image</th><th>Edit</th><th>Delete</th></tr>");
        for(Phones e:list){
            out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getPrice()+"</td> <td><img src= '"+e.getImgLink()+"' width=100 height=100 ></td><td><a href='EditPhonesServlet?id="+e.getId()+"'>edit</a></td> <td><a href='DeletePhonesServlet?id="+e.getId()+"'>delete</a></td></tr>");
        }
        out.print("</table>");

        out.close();
    }
}  