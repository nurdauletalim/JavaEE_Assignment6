<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>
<div class="alert alert-success" role="alert">
    Welcome, ADMIN!
</div>
<hr>
<div class="container">
<div class="card w-75">
    <div class="card-body">
        <h5 class="card-title">Users</h5>
        <p class="card-text">Here you can view or edit users.</p>
        <a href="adminUsers.jsp" class="btn btn-primary">Submit</a>
    </div>
</div>
<div class="card w-75">
    <div class="card-body">
        <h5 class="card-title">Phones</h5>
        <p class="card-text">Here you can view or edit users.</p>
        <a href="adminPhones.jsp" class="btn btn-primary">Submit</a>
    </div>
</div></div>
</body>
</html>
