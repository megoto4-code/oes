<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="col-md-12">
	<a href="${serverPath}addQuestions?examId=${examId}"
		class="btn btn-info top-offset pull-right gap-left"><span
		class="glyphicon glyphicon-plus"></span> Add Question</a> <a
		class="btn btn-default top-offset pull-right" role="button"
		data-toggle="collapse" href="#collapseInstructions"
		aria-expanded="false" aria-controls="collapseExample"><span
		class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Show
		Instructions</a>
	<h3>
		List of Questions: <b>${examName}</b> (Time: <b>${examTime}</b>
		minutes | Total Questions: <b>${totalNoOfQuestions}</b> | Total Marks: <b>${examTotalMarks}</b>)
	</h3>
	<hr />
	<div class="collapse" id="collapseInstructions">
		<div>
			${examInstructions} <b><a
				href="${serverPath}updateExam?examId=${examId}">[Edit]</a></b> <br /> <br />
		</div>
	</div>
	<%
		if (session.getAttribute("questionMsg") != null) {
			out.println(session.getAttribute("questionMsg"));
			session.removeAttribute("questionMsg");
		}
	%>
	${listOfQuestions}
</div>