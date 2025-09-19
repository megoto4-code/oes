<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="col-md-12">
	<h3>Answersheet</h3>
	<table class="table table-bordered marksheet">
		<tr>
			<td class="text-center">Exam: <b>${examName}</b></td>
			<td class="text-center">Name: <b>${name}</b></td>
			<td class="text-center">Roll No: <b>${roll}</b></td>
		</tr>
	</table>
	${answerSheet}
</div>