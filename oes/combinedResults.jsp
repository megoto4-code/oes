<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="col-md-12">
	<h3>Combined Results</h3>
	<hr />
	<h5 class="text-center">
		<ol class="breadcrumb">${examNameList}</ol>
	</h5>
	<table class="table table-striped">
		<tr>
			<th></th>
			<th class="text-center">Roll No</th>
			<th class="text-center">Name</th>
			<th class="text-center">Exam</th>
			<th class="text-center">Total</th>
			<th class="text-center">Mark</th>
			<th class="text-center">Timestamp</th>
			<th class="text-center">Percentage</th>
			<th class="text-center">Rank</th>
			<th></th>
		</tr>
		${combinedResults}
	</table>
</div>