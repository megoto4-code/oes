package oes.admin.result;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.data.jdbcConnection;

@WebServlet("/deleteCandidate")
public class DeleteCandidate extends bootstrap {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jdbcConnection jc = new jdbcConnection();
		jc.removeRecord("register", request.getParameter("registerId"));
		jc.removeRecord("answersheet", "registerId", request.getParameter("registerId"));
		this.flashMessage(request, "candidateMsg", "One candidate has been deleted.", 0);
		response.sendRedirect(serverPath + "listOfCandidates?examId=" + request.getParameter("examId"));
		return;
	}

}
