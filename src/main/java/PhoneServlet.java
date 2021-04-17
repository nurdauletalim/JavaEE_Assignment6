import Model.Phones;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PhoneServlet")
public class PhoneServlet extends HttpServlet {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/PhoneStore";
    static final String DATABASE_USER = "postgres";
    static final String DATABASE_PASSWORD = "123";
    public PhoneServlet() {
    }
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");
            con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/PhoneStore","postgres","123");
        }catch(Exception e){System.out.println(e);}
        return con;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Phones> phonesList=new ArrayList<Phones>();
        PrintWriter out=response.getWriter();
        try{
            Connection con = PhoneServlet.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * from phones");
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                Phones p=new Phones();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getString(3));
                p.setImgLink(rs.getString(4));
                phonesList.add(p);
            }
            con.close();
        } catch(Exception e){e.printStackTrace();}

        }

    }
