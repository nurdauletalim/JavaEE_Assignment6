<%@ page import="java.util.List" %>
<%@ page import="Model.Comment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <title>Detail Movie</title>
    <jsp:useBean id="post" class="Model.Post" scope="session"/>
    <% List<?> comments = (List<?>) request.getSession().getAttribute("comments");%>
    <jsp:useBean id="comment" class="Model.Comment" scope="session"/>

</head>
<h2><a href="Login.jsp">Login</a></h2>
<div align="center">
    <h2>Add New Product</h2>
</div>
<div>
    <form action="PostDetailServlet" method="post">
        <table align="center">
            <div class="form-group">
                <label> Post topic </label>
                <jsp:getProperty name="post" property="topic"/>
            </div>
            <div class="form-group">
                <label> Post body </label>
                <jsp:getProperty name="post" property="text"/>
            </div>
            <div class="form-group">
                <label> Likes </label>
                <jsp:getProperty name="like" property="like"/>
            </div>
            <div class="form-group">
                <label> DisLikes </label>
                <jsp:getProperty name="dislike" property="dislike"/>
            </div>
        </table>
    </form>
</div>

