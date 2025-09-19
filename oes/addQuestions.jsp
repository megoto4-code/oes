<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="col-md-12">
	<h3>
		Add questions for the exam <b>${examName}</b>
	</h3>
	<hr />
	<form action="${serverPath}addQuestions" method="post">
		<div class="form-group">
			<label>Question: <span class="label label-success">required</span></label>
			<textarea name="question" class="form-control"
				placeholder="Write the Question here" rows="5"></textarea>
		</div>
		<div class="form-group">
			<label>Option 1: <span class="label label-success">required</span></label>
			<input value="" name="option1" type="text" class="form-control"
				placeholder="Write Option 1 here">
		</div>
		<div class="form-group">
			<label>Option 2: <span class="label label-success">required</span></label>
			<input value="" name="option2" type="text" class="form-control"
				placeholder="Write Option 2 here">
		</div>
		<div class="form-group">
			<label>Option 3:</label> <input value="" name="option3" type="text"
				class="form-control" placeholder="Write Option 3 here">
		</div>
		<div class="form-group">
			<label>Option 4:</label> <input value="" name="option4" type="text"
				class="form-control" placeholder="Write Option 4 here">
		</div>
		<div class="form-group">
			<label>Option 5:</label> <input value="" name="option5" type="text"
				class="form-control" placeholder="Write Option 5 here">
		</div>

		<div class="row">
			<div class="col-md-3">
				<label>Answer (Select right option): <span
					class="label label-success">required</span></label> <select name="answer"
					class="form-control">
					<option>Select</option>
					<option value="1">Option 1</option>
					<option value="2">Option 2</option>
					<option value="3">Option 3</option>
					<option value="4">Option 4</option>
					<option value="5">Option 5</option>
				</select>
			</div>
			<div class="col-md-3">
				<label>Mark: <span class="label label-success">required</span></label> <input value="" name="mark" type="text"
					class="form-control" placeholder="Enter Mark">
			</div>
			<div class="col-md-3">
				<label>Negative Mark:</label> <select name="negative"
					class="form-control">
					<option>Select</option>
					<option value="100">No negative mark</option>
					<option value="25">1/4 negative mark</option>
					<option value="33">1/3 negative mark</option>
					<option value="50">1/2 negative mark</option>
				</select>
			</div>
			<div class="col-md-3">
				<label>Category:</label> <select name="category"
					class="form-control">
					<option value="">Select</option>
					${categoryOptions}
				</select>
			</div>
		</div>
		<br />
		<div class="form-group">
			<input type="hidden" value="${examId}" name="examId" /><input
				type="hidden" value="${examName}" name="examName" /> <input
				type="submit" value="Add" class="btn btn-info pull-right" /> <input
				type="reset" value="Reset" class="btn btn-default" />
		</div>
	</form>
</div>