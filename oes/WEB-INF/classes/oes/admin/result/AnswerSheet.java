package oes.admin.result;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.ResultModel;

@WebServlet("/answerSheet")
public class AnswerSheet extends bootstrap {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		if (request.getParameterMap().containsKey("examId") == false
				&& request.getParameterMap().containsKey("registerId") == false) {
			response.sendRedirect(serverPath + "admin");
			return;
		}
		ResultModel rm = new ResultModel();
		String examId = request.getParameter("examId");
		String registerId = request.getParameter("registerId");
		String name = rm.getAttributeValue("register", "name", registerId);
		request.setAttribute("examName", rm.getAttributeValue("exams", "name", examId));
		request.setAttribute("name", name);
		request.setAttribute("roll", rm.getAttributeValue("register", "roll", registerId));
		request.setAttribute("page", "List Of Candidates");
		request.setAttribute("answerSheet", rm.getAnswerSheet(registerId, examId));
		this.render(request, response, "/answerSheet.jsp", "/adminPrivate.jsp");
	}

}
