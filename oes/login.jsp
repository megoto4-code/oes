<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="container">
	<div class="col-md-6 col-md-offset-3" id="login">
		<h2>
		Login
		<a class="btn btn-default pull-right" href="${serverPath}login">
		<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span></a></h2>
		<hr />
		<form action="${serverPath}login" method="post">
			<p>
				<label>Username:</label> 
				<input type="text" name="username" class="form-control" />
			</p>
			<p>
				<label>password:</label> <input type="text" name="password"
					class="form-control" />
			</p>
			<p>
				<input type="reset" value="Reset" class="btn btn-default" /> <input
					type="submit" value="Login" class="btn btn-info pull-right" />
					<a class="btn btn-success" onclick="goToExam()">Go to Exam</a>
			</p>
		</form>
		<%
			if (session.getAttribute("msg1") != null){
				out.println(session.getAttribute("msg1"));
				session.removeAttribute("msg1");
			}
		%>	
		
	</div>
</div>
<script>
function goToExam() {
    var myWindow = window.open("${serverPath}home", "", "width=800, height=600");
}
</script>