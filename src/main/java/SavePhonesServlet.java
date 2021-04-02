import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SavePhonesServlet")
public class SavePhonesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String name=request.getParameter("name");
        String price=request.getParameter("price");
        String imgLink=request.getParameter("imgLink");

        Phones e=new Phones();
        e.setName(name);
        e.setPrice(price);
        e.setImgLink(imgLink);

        int status=PhonesDAO.save(e);
        if(status>0){
            out.println("<p>Record saved successfully!</p><br>");
            out.println("<a href=\"ViewPhonesServlet\">view phones</a>");
        }else{
            out.println("Sorry! unable to save record");
        }

        out.close();
    }

}  