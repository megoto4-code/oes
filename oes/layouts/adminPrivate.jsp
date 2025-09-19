<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet"
	href="${serverPath}resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${serverPath}resources/css/style.css">
<link rel="stylesheet" href="${serverPath}resources/css/bootstrap-datetimepicker.min.css">
</head>
<body>
	<div class="container">
		<div class="row" id="header">
			<div class="col-md-8">
				<h1>${appName}</h1>
			</div>
			<div class="col-md-4 tb">
				<ul class="nav nav-pills pull-right">
					<li ${page=='home'? ' class="active"':'' }><a
						href="${serverPath}admin">Home <span
							class="glyphicon glyphicon-home"></span></a></li>
					<li ${page=='settings'? ' class="active"':'' }><a
						href="${serverPath}settings">Settings <span
							class="glyphicon glyphicon-wrench"></span></a></li>
					<li ${page=='logout'? ' class="active"':'' }><a
						class="pointer" data-toggle="modal"
						data-target=".bs-example-modal-sm">Logout <span
							class="glyphicon glyphicon-off"></span>
					</a></li>
				</ul>
			</div>
		</div>
		<div class="row" id="content">${content}</div>
		<div class="row" id="footer">
			<div class="col-md-12">${appCopyright}</div>
		</div>
	</div>
	<div id="myModal" class="modal fade bs-example-modal-sm"
		tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-info-sign"></span> Do you want to
						log out?
					</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					<a class="btn btn-primary" href="${serverPath}logout">Yes</a>
				</div>
			</div>
		</div>
	</div>	
	<script src="${serverPath}resources/js/jquery.js"></script>
	<script src="${serverPath}resources/js/bootstrap.min.js"></script>
	<script src="${serverPath}resources/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${serverPath}resources/js/main.js"></script>
</body>
</html>