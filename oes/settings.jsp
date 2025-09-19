<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="col-md-12">
	<h3>Settings: Miscellaneous</h3>
	<hr />
	<%
		if (session.getAttribute("settingsMsg") != null) {
			out.println(session.getAttribute("settingsMsg"));
			session.removeAttribute("settingsMsg");
		}
	%>
	<form action="${serverPath}settings" method="post">
		<input type="hidden" name="form" value="settingsMisc" />
		<div class="form-group">
			<label>Application Name</label> <input value="${appName}"
				name="appName" type="text" class="form-control">
		</div>
		<div class="form-group">
			<label>Copyright</label> <input value="${appCopyright}"
				name="appCopyright" type="text" class="form-control">
		</div>
		<div class="form-group">
			<input type="submit" value="Update" class="btn btn-info pull-right" />
			<input type="reset" value="Reset" class="btn btn-default" />
		</div>
	</form>
	<h3>Settings: Profile</h3>
	<hr />
	<form action="${serverPath}settings" method="post">
		<input type="hidden" name="form" value="settingsProfile" />
		<div class="form-group">
			<label>Old Username</label> <input value="" name="oldUsername"
				type="text" class="form-control">
		</div>
		<div class="form-group">
			<label>New Username</label> <input value="" name="newUsername"
				type="text" class="form-control">
		</div>
		<div class="form-group">
			<label>Old Password</label> <input value="" name="oldPassword"
				type="text" class="form-control">
		</div>
		<div class="form-group">
			<label>New Password</label> <input value="" name="newPassword"
				type="text" class="form-control">
		</div>
		<div class="form-group">
			<label>Retype New Password</label> <input value=""
				name="reTypeNewPassword" type="text" class="form-control">
		</div>
		<div class="form-group">
			<input type="submit" value="Update" class="btn btn-info pull-right" />
			<input type="reset" value="Reset" class="btn btn-default" />
		</div>
	</form>
</div>