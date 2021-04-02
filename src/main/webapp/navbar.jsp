<html>
<head>
    <style>
        header {
        padding: 156px 0 100px;
    }

    section {
        padding: 150px 0;
    }
    </style>
</head>
<body>
<%@ page import="javax.servlet.http.*" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Phone Store</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <form action="LoginFilter">
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="Main.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Phones.jsp">Shop</a>
                </li>
<%--                <%--%>
<%--                    session=request.getSession(false);--%>
<%--                    if(session!=null){--%>
<%--                        String name=(String)session.getAttribute("name");--%>
<%--                %>--%>

<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href=""><%=name%></a>--%>
<%--                </li>--%>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet">Log out</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ProfileServlet">Profile</a>
                </li>

<%--                <%}--%>
<%--                else {%>--%>

                <li class="nav-item">
                    <a class="nav-link" href="Login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Registration.jsp">Register</a>
                </li>
<%--                <%}%>--%>



            </ul>
        </div>
        </form>
    </div>
</nav>
</body>
</html>