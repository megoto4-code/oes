<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="col-md-12">
	<%
		if (session.getAttribute("adminMsg") != null) {
			out.println(session.getAttribute("adminMsg"));
			session.removeAttribute("adminMsg");
		}
	%>
	<div class="jumbotron top-offset">
		<h1>Welcome Administrator</h1>
		<p>This is a simple hero unit, a simple jumbotron-style component
			for calling extra attention to featured content or information</p>
		<p>
			<a href="${serverPath}listOfExams" class="btn btn-info btn-lg">Manage
				Examinations</a> <a href="${serverPath}examResults"
				class="btn btn-primary btn-lg">Manage Results</a>
		</p>
	</div>
	<hr />
</div>
<div class="col-md-4">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<b>Statistics</b>
			</h3>
		</div>
		<table class="table">
			<tr>
				<td>Total Exams</td>
				<td class="text-right"><span class="badge">${totalExams}</span></td>
			</tr>
			<tr>
				<td>Total Questions</td>
				<td class="text-right"><span class="badge">${totalQuestions}</span></td>
			</tr>
			<tr>
				<td>Total Registered</td>
				<td class="text-right"><span class="badge">${totalCandidatesRegistered}</span></td>
			</tr>
			<tr>
				<td>Total Answers given</td>
				<td class="text-right"><span class="badge">${totalAnswersGiven}</span></td>
			</tr>
		</table>
	</div>
</div>
<div class="col-md-8">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">
				<b>Recent Toppers</b>
			</h3>
		</div>
		${toppers}
	</div>
</div>