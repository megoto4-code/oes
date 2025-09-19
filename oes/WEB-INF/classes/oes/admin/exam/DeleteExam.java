package oes.admin.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.data.jdbcConnection;

@WebServlet("/deleteExam")
public class DeleteExam extends bootstrap {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jdbcConnection jc = new jdbcConnection();
		String examId = request.getParameter("examId");
		String examName = jc.getAttributeValue("exams", "id", examId, "name");
		if (jc.isDependentOn("questions", "examId", examId) == false) {
			jc.removeRecord("exams", examId);
			this.flashMessage(request, "msg2", "Exam named <b>" + examName + "</b> has been deleted.", 0);
		} else {
			this.flashMessage(request, "msg2", "Exam named <b>" + examName
					+ "</b> is cannot be deleted since one or more question is/are dependant on it.", 2);
		}
		response.sendRedirect(serverPath + "listOfExams");
		return;
	}

}
