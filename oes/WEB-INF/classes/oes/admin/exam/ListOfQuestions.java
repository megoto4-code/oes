package oes.admin.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.AdminModel;

@WebServlet("/listOfQuestions")
public class ListOfQuestions extends bootstrap {
	private static final long serialVersionUID = 1L;

	public ListOfQuestions() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		AdminModel am = new AdminModel();
		initApp();
		String examId = request.getParameter("examId");
		request.setAttribute("examId", examId);
		request.setAttribute("examName", am.getAttributeValue("exams", "name", examId));
		request.setAttribute("examTime", am.getAttributeValue("exams", "time", examId));
		request.setAttribute("examTotalMarks", am.getTotalMarks(examId));
		request.setAttribute("examInstructions", am.getAttributeValue("exams", "instructions", examId));
		if (request.getParameterMap().containsKey("category")) {
			request.setAttribute("listOfQuestions", am.getListOfQuestions(examId, request.getParameter("category")));
		} else {
			request.setAttribute("listOfQuestions", am.getListOfQuestions(examId, "all"));
		}
		request.setAttribute("totalNoOfQuestions", am.getTotalNoOfQuestions(examId));

		this.render(request, response, "/listOfQuestions.jsp", "/adminPrivate.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
