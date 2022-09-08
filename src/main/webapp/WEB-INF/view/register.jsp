<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">

</head>
<body>
<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
    <!--Links-->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="register">Register</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="login">Login</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="index">Home</a>
        </li>
    </ul>
</nav>

<div class="container">
  <h2>Registration form</h2>
  <form class="form-horizontal" action="/register" id="regForm" method="post">
  <div class="form-group">
      <label class="control-label col-sm-2" for="name">Name:</label>
      <div class="col-sm-10">
        <input type="name" class="form-control" id="name" placeholder="Enter Name" name="userName">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-10">
        <input type="email" class="form-control" id="email" placeholder="Enter email" name="emailId" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-10">
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password" minlength="8" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="bDate">Birthdate:</label>
      <div class="col-sm-10">
        <input type="date" class="form-control" id="bDate" name="bDate" required>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <input type="submit"  value="Submit" style="margin-top:10px;" class="btn btn-primary"/>
      </div>
    </div>
  </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
