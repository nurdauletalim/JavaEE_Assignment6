<%@ page import="Model.Post" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Login Page</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <!-- Google Fonts -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
    <!-- Bootstrap core CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/css/mdb.min.css" rel="stylesheet">

    <!-- JQuery -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/js/mdb.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title>Product Details</title>
    <%! Post post; %>
    <%
        post = (Post) request.getSession().getAttribute("post");
        if (post == null) {
            request.getSession().setAttribute("message",
                    "Error!!!!!!!! Select Product First.");
            response.sendRedirect("PostsList.jsp");
        }
        assert post != null;%>
</head>
<a href="LogoutServlet">
    <button type="button">Logout</button>
</a>
<div align="center">
    <h2>Add New Product</h2>
</div>
<div>
    <form action="EditPostServlet" method="post">
        <table align="center">
            <div class="form-group">
                <label for="topic">Post topic: </label>
                <input type="text" id="topic" name="topic" required="true  value="<%=post.getTopic()%>">
            </div>
            <div class="form-group">
                <label for="body">Post body: </label>
                <input type="text" id="body" name="body" required="true  value="<%=post.getText()%>">
            </div>
            <div class="form-group">
                <label for="like">Likes: </label>
                <input type="text" id="like" name="like" required="true  value="<%=post.getLike()%>">
            </div>
            <div class="form-group">
                <label for="like">Dislikes: </label>
                <input type="text" id="DisLikes" name="disLike" required="true  value="<%=post.getDislike()%>">
            </div
            <button type="submit" class="btn btn-primary" value="Update Post">Submit</button>
        </table>
    </form>
</div>




