<html>
<head>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<title>Invalid Credentials</title>
</head>

<body>
<div id="nav-placeholder">

</div>

<h2>invalid credentials</h2>

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<script>
$.get("navbar.html", function(data){
    $("#nav-placeholder").replaceWith(data);
});
</script>
</body>

</html>