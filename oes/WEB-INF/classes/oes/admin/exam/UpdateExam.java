package oes.admin.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.AdminModel;
import oes.data.jdbcConnection;

@WebServlet("/updateExam")
public class UpdateExam extends bootstrap {
	private static final long serialVersionUID = 1L;

	public UpdateExam() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		String examId = request.getParameter("examId");
		jdbcConnection jc = new jdbcConnection();
		request.setAttribute("examId", examId);
		request.setAttribute("examName", jc.getAttributeValue("exams", "name", examId));
		request.setAttribute("examTime", jc.getAttributeValue("exams", "time", examId));
		request.setAttribute("examInstructions", jc.getAttributeValue("exams", "instructions", examId));
		this.render(request, response, "/updateExam.jsp", "/adminPrivate.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String examName = request.getParameter("examName");
		String examTime = request.getParameter("examTime");
		String examInstructions = request.getParameter("examInstructions");
		String examId = request.getParameter("examId");
		String examStatus = request.getParameter("examStatus");
		AdminModel am = new AdminModel();
		am.updateExam(examName, examTime, examInstructions, examId, examStatus);
		this.flashMessage(request, "msg2", "Exam named <b>" + examName + "</b> has been updated.", 0);
		response.sendRedirect(serverPath + "listOfExams");
	}

}
