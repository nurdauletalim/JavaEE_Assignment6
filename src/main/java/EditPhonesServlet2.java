import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EditPhonesServlet2")
public class EditPhonesServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String sid=request.getParameter("id");
        int id=Integer.parseInt(sid);
        String name=request.getParameter("name");
        String price=request.getParameter("price");
        String imgLink=request.getParameter("imgLink");

        Phones e=new Phones();
        e.setId(id);
        e.setName(name);
        e.setPrice(price);
        e.setImgLink(imgLink);

        int status=PhonesDAO.update(e);
        if(status>0){
            response.sendRedirect("ViewPhonesServlet");
        }else{
            out.println("Sorry! unable to update record");
        }

        out.close();
    }

}  