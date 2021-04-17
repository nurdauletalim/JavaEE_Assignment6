import Model.Comment;
import Model.Post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");
            con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/PhoneStore","postgres","123");
        }catch(Exception e){System.out.println(e);}
        return con;
    }
    public boolean newPost(Post posts) {
        try {
            Connection con = PostDAO.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "insert into posts(topic,text,likes,dislikes,userID) values (?,?,?,?,?)");
            ps.setString(1, posts.getTopic());
            ps.setString(2, posts.getText());
            ps.setInt(3, posts.getLike());
            ps.setInt(4, posts.getDislike());
            ps.setInt(5, posts.getUserId());
            con.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public Post getPostByID(int id){
        Post post = null;
        try {
            Connection con = PostDAO.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM posts WHERE postid=?");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int postId = rs.getInt(1);
                String topic = rs.getString(2);
                String text = rs.getString(3);
                int like = rs.getInt(4);
                int dislike = rs.getInt(5);
                int userId = rs.getInt(6);
                post = new Post();
                post.setId(postId);
                post.setUserId(userId);
                post.setTopic(topic);
                post.setText(text);
                post.setLike(like);
                post.setDislike(dislike);

            }

        }catch (Exception ex) {
            ex.printStackTrace();}
        return post;
    }

    public ArrayList<Post> getAllPost(){
        ArrayList<Post> posts = new ArrayList<>();
        try {
            Connection con = PostDAO.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM posts");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int postId = rs.getInt(1);
                String topic = rs.getString(2);
                String text = rs.getString(3);
                int like = rs.getInt(4);
                int dislike = rs.getInt(5);
                int userId = rs.getInt(6);
                Post post = new Post();
                post.setId(postId);
                post.setUserId(userId);
                post.setTopic(topic);
                post.setText(text);
                post.setLike(like);
                post.setDislike(dislike);
                posts.add(post);
            }

        }catch (Exception ex) {
            ex.printStackTrace();}
        return posts;
    }

    public List<?> getComments(int id){
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            Connection con = PostDAO.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM comments where postid =" + id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int comId = rs.getInt(1);
                int postId = rs.getInt(2);
                String username = rs.getString(3);
                String comBody = rs.getString(4);
                Comment com = new Comment();
                com.setId(comId);
                com.setComment(comBody);
                com.setPostId(postId);
                com.setUserName(username);
                comments.add(com);
            }

        }catch (Exception ex) {
            ex.printStackTrace();}
        return comments;
    }

    public boolean addComment(Comment com){
        try {
            Connection con = PostDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO comments (postid, comment, username) Values (?, ?, ?)");
            ps.setInt(1, com.getPostId());
            ps.setString(2,com.getComment());
            ps.setString(3,com.getUserName());
            ps.executeUpdate();
            return true;

        }catch (Exception ex){
            System.out.println(ex);
        }
        return false;
    }




    public void updateLikes(Post post) {
        try {
            Connection con = PostDAO.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "update posts set likes=?,dislikes=? where postid=?");
            ps.setInt(1, post.getLike());
            ps.setInt(2, post.getDislike());
            ps.setInt(3, post.getId());
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public boolean delete(int id) {

        try {
            Connection con = PostDAO.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from posts where postid=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean editPost(Post p){

        try{
            Connection con= PostDAO.getConnection();
            PreparedStatement ps=con.prepareStatement("UPDATE posts SET topic = ?, text = ? WHERE postid = ?");
            ps.setString(1,p.getTopic());
            ps.setString(2,p.getText());
            ps.setInt(3,p.getId());
            ps.executeUpdate();
            con.close();
            return true;
        }catch(Exception e){e.printStackTrace();}

        return false;
    }

}
