<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>

<h1>Add New User</h1>
<div class="container">
<form action="SaveServlet" method="post">

    <div class="form-group">
        <label for="formGroupExampleInput">Enter name</label>
        <input type="text" class="form-control" id="formGroupExampleInput" name="name" placeholder="name">
    </div>
    <div class="form-group">
        <label for="formGroupExampleInput2">Enter password</label>
        <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="pass" name="password">
    </div>
    <div class="form-group">
        <label for="formGroupExampleInput3">email</label>
        <input type="text" class="form-control" id="formGroupExampleInput3" placeholder="email" name="email">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>

</form>

<br/>
<a href="ViewServlet" class="btn btn-primary btn-lg btn-block">View users</a><br><br>
<a href="adminPhones.jsp" class="btn btn-secondary btn-lg btn-block">Phones</a>
</div>
</body>
</html>
