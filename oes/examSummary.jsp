<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<style>
<!--
th {
	text-align: center;
}
-->
</style>
<div class="container">
	<div class="col-md-12 text-center" id="login">
		<h3>${exam}| Exam Summary</h3>
		<hr />
		<h4>
			Congratulation <span class="text-primary">${name}</span> (Roll No: <span
				class="text-primary">${roll}</span>), your exam is completed after
			${time} minutes.
		</h4>
		<br /> ${attempts}
		<p>
			<a href="${serverPath}exitExam" class="btn btn-primary">Close</a>
		</p>
	</div>
</div>