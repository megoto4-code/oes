<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="col-md-12">
	<form method="get" action="${serverPath}combinedResults">
		<input type="submit" value="Show Combined Results"
			class="btn btn-info pull-right gap-bottom" />
		<h3>Manage Results</h3>
		<hr />
		<%
			if (session.getAttribute("examResultsMsg") != null) {
				out.println(session.getAttribute("examResultsMsg"));
				session.removeAttribute("examResultsMsg");
			}
		%>
		<table class="table table-striped">
			<tr>
				<th><input type="checkbox" onClick="toggle(this)" /></th>
				<th class="text-center">No</th>
				<th class="text-center">Name</th>
				<th class="text-center">Time</th>
				<th class="text-center">Candidates</th>
				<th></th>
			</tr>
			${listOfExam}
		</table>
	</form>
</div>
<script type="text/javascript">
	function toggle(source) {
		checkboxes = document.getElementsByName('examId');
		for (var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>