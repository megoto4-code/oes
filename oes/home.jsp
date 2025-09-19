<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="container">
	<div class="col-md-6 col-md-offset-3" id="login">
		<h2>Go to Exam
		<a class="btn btn-default pull-right" href="${serverPath}home">
		<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span></a>
		</h2>
		<hr />
		<form action="${serverPath}home" method="post">
			<div class="form-group">
				<label>Select Exam</label> <select name="selectExam"
					class="form-control"> ${examOptions}
				</select>
			</div>
			<div class="form-group">
				<label>System No</label> <input name="systemNo" type="text"
					class="form-control">
			</div>
			<div class="form-group">
				<label>Password</label> <input name="password" type="text"
					class="form-control">
			</div>
			<div class="form-group">
				<input type="submit" value="Go to exam"
					class="btn btn-info pull-right" /> <input type="reset"
					value="Reset" class="btn btn-default" />					
			</div>
		</form>
		<%
			if (session.getAttribute("homeMsg") != null){
				out.println(session.getAttribute("homeMsg"));
				session.removeAttribute("homeMsg");
			}
		%>	
	</div>
</div>