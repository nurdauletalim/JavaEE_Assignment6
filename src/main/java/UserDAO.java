import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/PhoneStore","postgres","123");
        }catch(Exception e){System.out.println(e);}
        return con;
    }
    public static boolean validate(String email,String pass){
        boolean status=false;
        try{
                Class.forName("org.postgresql.Driver");
                Connection con=DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/PhoneStore","postgres","123");
            // getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "select * from users where email=? and pass=?");
            ps.setString(1,email);
            ps.setString(2,pass);

            ResultSet rs=ps.executeQuery();
            status=rs.next();

        }catch(Exception e){System.out.println(e);}
        return status;
    }

    public static int save(Users e){
        int status=0;
        try{
            Connection con=UserDAO.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into users(name,pass,email) values (?,?,?)");
            ps.setString(1,e.getName());
            ps.setString(2,e.getPassword());
            ps.setString(3,e.getEmail());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
    public static int update(Users e){
        int status=0;
        try{
            Connection con=UserDAO.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "update users set name=?,pass=?,email=? where id=?");
            ps.setString(1,e.getName());
            ps.setString(2,e.getPassword());
            ps.setString(3,e.getEmail());
            ps.setInt(4,e.getId());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
    public static int delete(int id){
        int status=0;
        try{
            Connection con=UserDAO.getConnection();
            PreparedStatement ps=con.prepareStatement("delete from users where id=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();

            con.close();
        }catch(Exception e){e.printStackTrace();}

        return status;
    }
    public static Users getEmployeeById(int id){
        Users e=new Users();

        try{
            Connection con=UserDAO.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from users where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setEmail(rs.getString(4));
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return e;
    }
    public static List<Users> getAllEmployees(){
        List<Users> list=new ArrayList<Users>();

        try{
            Connection con=UserDAO.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from users");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Users e=new Users();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setEmail(rs.getString(4));
                list.add(e);
            }
            con.close();
        }catch(Exception e){e.printStackTrace();}

        return list;
    }
}