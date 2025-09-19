<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="col-md-12">
	<h3>
		Update Exam named: <b>${examName}</b>
	</h3>
	<hr />
	<form action="${serverPath}updateExam" method="post">
		<div class="form-group">
			<label>Exam Name <span class="label label-success">required</span></label>
			<input value="${examName}" name="examName" type="text"
				class="form-control" placeholder="Exam Name">
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label>Exam Time (In minutes) <span
						class="label label-success">required</span></label> <input
						value="${examTime}" name="examTime" type="text"
						class="form-control" placeholder="Exam Time">
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label>Exam Active</label> <select class="form-control" name="examStatus">
						<option value="0">Not Active</option>
						<option value="1" selected="selected">Active</option>
					</select>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label>Exam Instructions</label>
			<textarea name="examInstructions" class="form-control"
				placeholder="Exam Instructions" rows="15">${examInstructions}</textarea>
		</div>
		<div class="form-group">
			<input type="hidden" value="${examId}" name="examId" /> <input
				type="submit" value="Update" class="btn btn-info pull-right" /> <input
				type="reset" value="Reset" class="btn btn-default" />
			<button type="button" class="btn btn-warning" data-toggle="modal"
				data-target=".delete-exam">Delete</button>

			<div class="modal fade delete-exam" tabindex="-1" role="dialog"
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
							exam? By deleting this exam, all the questions under this exam
							will also be deleted.</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">No</button>
							<a href="${serverPath}deleteExam?examId=${examId}"
								class="btn btn-warning">Yes</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>