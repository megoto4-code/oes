<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="col-md-12">
	<h3>Create a new Exam</h3>
	<hr />
	<form action="${serverPath}createExam" method="post">
		<div class="form-group">
			<label>Exam Name <span class="label label-success">required</span></label>
			<input name="examName" type="text" class="form-control"
				placeholder="Exam Name">
		</div>
		<div class="form-group">
			<label>Exam Time (In minutes) <span class="label label-success">required</span></label>
			<input name="examTime" type="text" class="form-control"
				placeholder="Exam Time">
		</div>
		<div class="form-group">
			<label>Exam Instructions</label>
			<textarea name="examInstructions" class="form-control"
				placeholder="Exam Instructions" rows="5"></textarea>
		</div>
		<div class="form-group">
			<input type="submit" value="Create" class="btn btn-info pull-right" />
			<input type="reset" value="Reset" class="btn btn-default" />
		</div>
	</form>
</div>