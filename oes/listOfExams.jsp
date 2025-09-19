<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="col-md-12">
	<a class="btn btn-default top-offset pull-right gap-left" role="button"
		data-toggle="collapse" href="#collapseShowCategories"
		aria-expanded="false" aria-controls="collapseExample"><span
		class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Show
		Categories</a> <a href="${serverPath}createExam"
		class="btn btn-info top-offset pull-right"><span
		class="glyphicon glyphicon-file"></span> Create an Exam</a>
	<h3>List of Exams</h3>
	<hr />
	<%
		if (session.getAttribute("msg2") != null) {
			out.println(session.getAttribute("msg2"));
			session.removeAttribute("msg2");
		}
	%>
	<div class="collapse" id="collapseShowCategories">
		<div>
			<%
				if (request.getAttribute("form").equals("add")) {
			%>
			<form action="${serverPath}listOfExams" method="post"
				class="form-inline">
				<input type="submit" value="Add"
					class="btn btn-info pull-right gap-left" /> <input
					name="categoryName" type="text" class="form-control pull-right"
					placeholder="Add category here..."> <input type="hidden"
					value="addCategory" name="formName" />
			</form>
			<%
				} else {
			%>
			<form action="${serverPath}listOfExams" method="post"
				class="form-inline">
				<input type="submit" value="Update"
					class="btn btn-info pull-right gap-left" /> <input
					name="categoryName" type="text" class="form-control pull-right"
					placeholder="Update category here..." value="${categoryName}">
				<input type="hidden" value="updateCategory" name="formName" /><input
					type="hidden" value="${categoryId}" name="categoryId" />
			</form>			
			<%
				}
			%>
			<b>Categories</b> <br /> <br /> <br /> ${listOfCategories}
		</div>
	</div>
	<table class="table table-striped">
		<tr>
			<th>No.</th>
			<th>Name</th>
			<th>Time</th>
			<th>Status</th>
			<th>Instructions</th>
			<th></th>
		</tr>
		${listOfExams}
	</table>
</div>
${customJsScript}