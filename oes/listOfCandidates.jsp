<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="col-md-12">
	<h3>Results: <b>${examName}</b></h3>
	<hr/>
	<%
		if (session.getAttribute("candidateMsg") != null) {
			out.println(session.getAttribute("candidateMsg"));
			session.removeAttribute("candidateMsg");
		}
	%>
	<table class="table table-striped">
	<tr>
	<th class="text-center">Sl.No.</th>
	<th class="text-center">Roll No</th>
	<th class="text-center">Name</th>
	<th class="text-center">Attempt</th>
	<th class="text-center">Right</th>
	<th class="text-center">Wrong</th>
	<th class="text-center">Negative</th>
	<th class="text-center">Mark</th>
	<th class="text-center">Timestamp</th>
	<th class="text-center">Percentage</th>
	<th class="text-center"></th>
	</tr>
	${listOfCandidates}</table>
</div>