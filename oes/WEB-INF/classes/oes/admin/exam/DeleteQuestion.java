package oes.admin.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.data.jdbcConnection;

@WebServlet("/deleteQuestion")
public class DeleteQuestion extends bootstrap {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jdbcConnection jc = new jdbcConnection();
		String questionId = request.getParameter("questionId");
		if (jc.isDependentOn("answersheet", "questionId", questionId) == false) {
			jc.removeRecord("questions", questionId);
			this.flashMessage(request, "questionMsg", "One question has been deleted.", 0);
		} else {
			this.flashMessage(request, "questionMsg",
					"Cannot delete since one or more candidate has answered this question.", 2);
		}
		response.sendRedirect(serverPath + "listOfQuestions?examId=" + request.getParameter("examId"));
		return;
	}

}
