<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="col-md-12">
	<a class="btn btn-primary pull-right top-offset gap-left"
		href="${serverPath}answerSheet?examId=${examId}&registerId=${registerId}">View
		Answers</a>
	<button type="button" class="btn btn-warning top-offset pull-right"
		data-toggle="modal" data-target=".delete-candidate">Delete
		Candidate</button>
	<h3>
		Mark Sheet: <b>${examName}</b>
	</h3>
	<hr />
	<table class="table table-bordered marksheet">
		<tr>
			<td>System No:</td>
			<td>${systemNo}</td>
		</tr>
		<tr>
			<td>Time Stamp:</td>
			<td>${created}</td>
		</tr>
		<tr>
			<td>Name:</td>
			<td>${name}</td>
		</tr>
		<tr>
			<td>Roll No:</td>
			<td>${roll}</td>
		</tr>
		<tr>
			<td>Mark:</td>
			<td><b>${mark}</b></td>
		</tr>
		<tr>
			<td>Total Questions:</td>
			<td>${totalQuestions}</td>
		</tr>
		<tr>
			<td>Attempted:</td>
			<td>${attempted}</td>
		</tr>
		<tr>
			<td>Not attempted:</td>
			<td>${notAttempted}</td>
		</tr>
		<tr>
			<td>Right Answers:</td>
			<td>${rightAnswers}</td>
		</tr>
		<tr>
			<td>Wrong Answers:</td>
			<td>${wrongAnswers}</td>
		</tr>
		<tr>
			<td>Negative:</td>
			<td>${negative}</td>
		</tr>
		<tr>
			<td>Percentage:</td>
			<td>${percentage}%</td>
		</tr>
		<tr>
			<td>Gender:</td>
			<td>${gender}</td>
		</tr>
		<tr>
			<td>Mobile:</td>
			<td>${mobile}</td>
		</tr>
		<tr>
			<td>Email:</td>
			<td>${email}</td>
		</tr>
		<tr>
			<td>Date of Birth:</td>
			<td>${dob}</td>
		</tr>
		<tr>
			<td>Exam Completed:</td>
			<td>In ${examCompleted} minutes</td>
		</tr>
		<tr>
			<td>Address:</td>
			<td>${address}</td>
		</tr>
	</table>
	<div class="modal fade delete-candidate" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-trash"></span> Warning
					</h4>
				</div>
				<div class="modal-body">Are you sure you want to delete this
					candidate?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
					<a
						href="${serverPath}deleteCandidate?examId=${examId}&registerId=${registerId}"
						class="btn btn-warning">Yes</a>
				</div>
			</div>
		</div>
	</div>
</div>