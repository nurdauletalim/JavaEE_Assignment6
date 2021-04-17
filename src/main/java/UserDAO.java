import Model.Users;

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
    public static boolean validate(String username,String pass){
        ArrayList<Users> users = new ArrayList<>();
        try{
            Connection con = UserDAO.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while (rs.next()){
                int id = rs.getInt(1);
                String username1 = rs.getString(2);
                String password = rs.getString(3);
                String userEmail = rs.getString(4);
                Users user = new Users();
                users.add(user);
                user.setId(id);
                user.setEmail(userEmail);
                user.setPassword(password);
                user.setName(username1);
            }
            for (Users user: users){
                if (user.getEmail().equals(username)
                        && user.getPassword().equals(pass)){
                    return true;
                }
            }


        }catch(Exception e){System.out.println(e);}
        return false;
    }

    public Users getUser(String email) {
        Users us = null;
        try {
            Connection con = UserDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int userId = rs.getInt(1);
                String name = rs.getString(2);
                String pass = rs.getString(3);
                String useremail = rs.getString(4);
                us = new Users();
                us.setId(userId);
                us.setName(name);
                us.setPassword(pass);
                us.setEmail(useremail);
            }

        }catch(Exception e){System.out.println(e);}
        return us;
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