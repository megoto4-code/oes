<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet"
	href="${serverPath}resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${serverPath}resources/css/examStyle.css">
<link rel="stylesheet"
	href="${serverPath}resources/css/bootstrap-datetimepicker.min.css">
<script src="${serverPath}resources/js/jquery.js"></script>
<script src="${serverPath}resources/js/bootstrap.min.js"></script>
<script src="${serverPath}resources/js/bootstrap-datetimepicker.min.js"></script>
<script src="${serverPath}resources/js/exam.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						setInterval(
								function() {
									$('#timer')
											.load(
													"${serverPath}timer?startTime=${startTime}&totalTime=${totalTime}");
									redirect();
								}, 100);
					});
	function redirect() {
		if ($('#timer').text().replace(/\s+/g, '') == '0:0') {
			$('body')
					.load(
							"${serverPath}exitExam?examSummary=yes&registerId=${registerId}&startTime=${startTime}&totalTime=${totalTime}");
		}
	}

	$(function() {
		$(this).bind("contextmenu", function(e) {
			e.preventDefault();
		});
	});
</script>
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top" id="header">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"> ${examName} </a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				${categoriesMenu}
				<li><a class="navbar-brand">&nbsp;</li>
				<li><a class="navbar-brand">System No: <b>${systemNo}</b></li>
				<li><a class="navbar-brand timer" href="#">&nbsp;<b><span
							id="timer"></span></b></a></li>
				<li><a class="pointer" data-toggle="modal"
					data-target=".help-modal">Help</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container-fluid">
		<div class="row" id="content">
			<div class="col-md-12 text-center" id="questionNos">
				${questionLinks}</div>
			<div class="col-md-12" id="nav">
				<h2 class="pull-left">
					Question No: <b>${questionNo}</b>
				</h2>
				<form action="${serverPath}exam" method="post">
					<span class="pull-left" id="mark">Mark: <b>${mark}</b><br/>
						Negative: <b>${negative}</span></b> <input
						type="hidden" name="form" value="save" /> <input type="hidden"
						name="examId" value="${examId}" /> <input type="hidden"
						name="registerId" value="${registerId}" /> <input type="hidden"
						name="uniqueId" value="${uniqueId}" /> <input type="hidden"
						name="categoryId" value="${categoryId}" /> <input type="hidden"
						name="questionId" value="${questionId}" /> <input type="hidden"
						name="questionNo" value="${questionNo}" /> <input type="hidden"
						name="startTime" value="${startTime}" /> <input type="hidden"
						name="totalTime" value="${totalTime}" /> <input type="submit"
						class="btn btn-primary btn-lg pull-right" value="Save" /> <a
						class="btn btn-info btn-lg pull-right" href="${forwardLink}">
						<span class="glyphicon glyphicon-step-forward" aria-hidden="true"></span>
					</a> <a class="btn btn-info btn-lg pull-right" href="${backwardLink}">
						<span class="glyphicon glyphicon-step-backward" aria-hidden="true"></span>
					</a>
					<div class="btn-group pull-right" data-toggle="buttons">
						${radios}</div>
				</form>
				<form action="${serverPath}exam" method="post">
					<input type="hidden" name="examId" value="${examId}" /> <input
						type="hidden" name="registerId" value="${registerId}" /> <input
						type="hidden" name="uniqueId" value="${uniqueId}" /> <input
						type="hidden" name="categoryId" value="${categoryId}" /> <input
						type="hidden" name="questionId" value="${questionId}" /> <input
						type="hidden" name="questionNo" value="${questionNo}" /> <input
						type="hidden" name="startTime" value="${startTime}" /> <input
						type="hidden" name="totalTime" value="${totalTime}" /> <input
						type="hidden" name="form" value="clear" />
					<button class="btn btn-default btn-lg pull-right">Clear</button>
				</form>
				<a class="btn btn-success btn-lg pull-right" data-toggle="modal"
					data-backdrop="static" data-target=".submit-modal">Submit &amp;
					Exit</a>
			</div>
		</div>
		<div class="row" id="content">${content}</div>
	</div>
	<div id="myModal1" class="modal fade submit-modal" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-info-sign"></span> Do you want to
						submit?
					</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					<a class="btn btn-primary"
						href="${serverPath}exitExam?examSummary=yes&registerId=${registerId}&startTime=${startTime}&totalTime=${totalTime}">Yes</a>
				</div>
			</div>
		</div>
	</div>
	<div id="myModal2" class="modal fade help-modal" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h2 class="modal-title">
						<span class="glyphicon glyphicon-info-sign"></span> Help
					</h2>
				</div>
				<div class="modal-body">
					<h4>Instructions</h4>
					${examInstructions} <br />
					<h4>Question Link Colors Description</h4>
					<ul class="list-group">
						<li class="list-group-item">When the question is neither
							answered nor cleared
							<button type="button" class="btn btn-primary btn-xs pull-right">1</button>
						</li>
						<li class="list-group-item">When the question is answered but
							cleared
							<button type="button" class="btn btn-warning btn-xs pull-right">1</button>
						</li>
						<li class="list-group-item">When the question is answered
							<button type="button" class="btn btn-success btn-xs pull-right">1</button>
						</li>
						<li class="list-group-item">When the particular question is
							currently opened
							<button type="button" class="btn btn-default btn-xs pull-right">1</button>
						</li>
					</ul>
					<h4>Mark distribution</h4>
					${examInfo}
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>