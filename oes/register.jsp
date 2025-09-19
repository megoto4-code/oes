<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<h1 class="text-center">
	<b>${examName}</b>
</h1>
<div class="container">
	<div class="col-md-6 col-md-offset-3" id="login">
		<h2>
			<small>Register | System No:</small> <b>${systemNo}</b> <a
				class="btn btn-default pull-right" href="${serverPath}register">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
			</a>
		</h2>
		<hr />
		<form action="${serverPath}register" method="post">
			<div class="form-group">
				<label>Roll No</label> <input name="roll" type="text"
					class="form-control">
			</div>
			<div class="form-group">
				<label>Name</label> <input name="name" type="text"
					class="form-control">
			</div>
			<div class="form-group">
				<label>Gender</label> <select name="gender" class="form-control">
					<option value="male"></option>
					<option value="male">Male</option>
					<option value="female">Female</option>
				</select>
			</div>
			<div class="form-group">
				<label>Mobile No.</label> <input name="mobile" type="text"
					class="form-control">
			</div>
			<div class="form-group">
				<label>Email</label> <input name="email" type="text"
					class="form-control">
			</div>
			<div class="form-group">
				<label>Date of Birth (DD/MM/YYYY)</label>
				<div id="datetimepicker" class="input-append date">
					<input type="text" name="dob" class="form-control"></input> <span
						class="add-on"> <i data-time-icon="icon-time"
						data-date-icon="icon-calendar"></i>
					</span>
				</div>
			</div>
			<div class="form-group">
				<label>Address</label>
				<textarea name="address" type="text" rows="4" cols="50"
					class="form-control"></textarea>
			</div>
			<div class="form-group">
				<input type="hidden" value="${examId}" name="examId" /><input
					type="hidden" value="${uniqueId}" name="uniqueId" /> <input
					type="hidden" value="${systemNo}" name="systemNo" /> <input
					type="submit" value="Go to exam" class="btn btn-info pull-right" />
				<input type="reset" value="Reset" class="btn btn-default" />
			</div>
		</form>
	</div>
</div>