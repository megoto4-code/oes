package oes.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oes.bootstrap;
import oes.data.ResultModel;

@WebServlet("/admin")
public class Admin extends bootstrap {
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		ResultModel rm = new ResultModel();
		this.autoRefresh(request, response);
		request.setAttribute("page", "home");
		request.setAttribute("totalExams", rm.getNoOfRows("exams"));
		request.setAttribute("totalQuestions", rm.getNoOfRows("questions"));
		request.setAttribute("totalCandidatesRegistered", rm.getNoOfRows("register"));
		request.setAttribute("totalAnswersGiven", rm.getNoOfRows("answersheet"));
		request.setAttribute("toppers", rm.getToppers(3));
		this.render(request, response, "/admin.jsp", "/adminPrivate.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
