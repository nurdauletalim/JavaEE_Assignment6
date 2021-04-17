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
    <title>List of posts</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"
    />
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.3.0/mdb.min.css" rel="stylesheet"
    />
</head>
<body id="page-top">
<%@ include file="/navbar.jsp" %>
<div class = "mb-5"></div>
<div class="container">
    <div class="row">
        <div class="col-lg-8 mt-3">
            <%
                Connection con=null;
                try{
                    Class.forName("org.postgresql.Driver");
                    con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/PhoneStore","postgres","123");
                    PreparedStatement ps = con.prepareStatement("SELECT * from posts");
                    ResultSet rs=ps.executeQuery();
            %>
            <%while(rs.next()){%>
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title"><%=rs.getString("topic")%></h5>
                    <p class="card-text"><%=rs.getString("text")%></p>
                    <p class="card-text"><small class="text-muted"><i class="fa fa-thumbs-up" aria-hidden="true"></i> <%=rs.getString("likes")%></small> | <small class="text-muted"><i class="fa fa-thumbs-down" aria-hidden="true"></i> <%=rs.getString("dislikes")%></small></p>
                    <a href="PostDetailServlet?postId=<%=rs.getInt("userid")%>">
                        <button type="button" class="btn btn-primary">Comment</button>
                    </a>
                </div>
            </div>
            <%
                }
            %>

        <a href="PostsList.jsp"></a>
        <%

                con.close();
            }catch(Exception e){System.out.println(e);}

        %>
        </div>
    </div>
</div>
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Alim N 2021</p>
    </div>
    <!-- /.container -->
</footer>
</body>
</html>
