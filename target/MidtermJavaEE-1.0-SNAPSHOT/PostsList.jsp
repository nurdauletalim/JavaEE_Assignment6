<%@page import="java.util.List"%>
<%@ page import="java.io.*" %>
<%@ page import="Model.Users" %>
<%@ page import="Model.Post" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet"/>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.3.0/mdb.min.css" rel="stylesheet" />
    <title>List Of Posts</title>
    <%
        List<?> posts = (List<?>) request.getSession().getAttribute("posts");
        Users user = (Users) request.getSession().getAttribute("logged_user");
    %>
    <%! Post post; %>
</head>
<body>
<%@ include file="/navbar.jsp" %>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Topic</th>
        <th scope="col">Text</th>
        <th scope="col">Like</th>
        <th scope="col">Dislike</th>
        <th scope="col">Edit</th>
        <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>
    <%
        Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");
            con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/PhoneStore","postgres","123");
            PreparedStatement ps = con.prepareStatement("SELECT * from posts");
            ResultSet rs=ps.executeQuery();
    %>
    <%while(rs.next()){%>
    <tr>
        <th scope="row">1</th>
        <td><%=rs.getString("topic")%></td>
        <td><%=rs.getString("text")%></td>
        <td><%=rs.getInt("likes")%></td>
        <td><%=rs.getInt("dislikes")%></td>
        <td><button type="button" class="btn btn-primary">
            <a href="AuthPostDetailServlet?postId=<%=rs.getInt("userid")%>"></a>PostDetail</button></td>
            <%
                if (rs.getInt("userid") == user.getId()){
            %>
            <td><a
                    href="EditPostServlet?postId=<%=rs.getInt("userid")%>">
                <button type="button" class="btn btn-primary">Edit</button>
            </a></td>
        <td><a
                href="DeletePostServlet?postId=<%=rs.getInt("userid")%>">
            <button type="button" class="btn btn-primary">Delete</button>
        </a></td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
    <%

        con.close();
        }catch(Exception e){System.out.println(e);}

%>
</table>
    </table>
<div align="center mt-3">
    <a
            href="NewPost.jsp">
        <button type="button" class="btn btn-primary">New Post</button>
    </a>
</div>
</body>
</html>
