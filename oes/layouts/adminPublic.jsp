<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet"
	href="${serverPath}resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${serverPath}resources/css/bootstrap-datetimepicker.min.css">
<style>
body {
	background: #f9f9f9;
}

#login {
	background: #fff;
	border-radius: 5px;
	border: 1px solid #aaa;
}
</style>
</head>
<body>
	<h1 class="text-center">${appName}</h1>
	${content}
	<br />
	<script src="${serverPath}resources/js/jquery.js"></script>
	<script src="${serverPath}resources/js/bootstrap.min.js"></script>
	<script src="${serverPath}resources/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${serverPath}resources/js/main.js"></script>
</body>
</html>