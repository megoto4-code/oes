<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<h1 class="text-center">
	<b>${examName}</b>
</h1>
<div class="container">
	<div class="col-md-12" id="login">
		<h2>Instructions</h2>
		<hr />
		<%
			if (session.getAttribute("confirmationMsg") != null) {
				out.println(session.getAttribute("confirmationMsg"));
				session.removeAttribute("confirmationMsg");
			}
		%>
		${examInstructions} <br /> <br /> ${examInfo}
		<form action="${serverPath}examInfo" method="post">
			<div class="form-group">
				<input type="checkbox" name="confirm" value="yes"> Click
				here to indicate that you have read and agree to the terms presented
				in the Terms and Conditions agreement.
			</div>
			<div class="form-group">
				<input type="hidden" value="${examId}" name="examId" /><input
					type="hidden" value="${uniqueId}" name="uniqueId" /> <input
					type="submit" value="Confirm &amp; Start Exam"
					class="btn btn-info pull-right" /><br /> <br />
			</div>
		</form>
	</div>
</div>