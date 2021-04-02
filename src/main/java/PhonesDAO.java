import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhonesDAO {
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");
            con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/PhoneStore","postgres","123");
        }catch(Exception e){System.out.println(e);}
        return con;
    }

    public static int save(Phones e){
        int status=0;
        try{
            Connection con=PhonesDAO.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "insert into phones(name,price,imglink) values (?,?,?)");
            ps.setString(1,e.getName());
            ps.setString(2,e.getPrice());
            ps.setString(3,e.getImgLink());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
    public static int update(Phones e){
        int status=0;
        try{
            Connection con=PhonesDAO.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "update phones set name=?,price=?,imglink=? where id=?");
            ps.setString(1,e.getName());
            ps.setString(2,e.getPrice());
            ps.setString(3,e.getImgLink());
            ps.setInt(4,e.getId());

            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return status;
    }
    public static int delete(int id){
        int status=0;
        try{
            Connection con=PhonesDAO.getConnection();
            PreparedStatement ps=con.prepareStatement("delete from phones where id=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();

            con.close();
        }catch(Exception e){e.printStackTrace();}

        return status;
    }
    public static Phones getPhoneById(int id){
        Phones e=new Phones();

        try{
            Connection con=UserDAO.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from phones where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setPrice(rs.getString(3));
                e.setImgLink(rs.getString(4));
            }
            con.close();
        }catch(Exception ex){ex.printStackTrace();}

        return e;
    }
    public static List<Phones> getAllPhones(){
        List<Phones> list=new ArrayList<Phones>();

        try{
            Connection con=PhonesDAO.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from phones");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Phones e=new Phones();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setPrice(rs.getString(3));
                e.setImgLink(rs.getString(4));
                list.add(e);
            }
            con.close();
        }catch(Exception e){e.printStackTrace();}

        return list;
    }
}
