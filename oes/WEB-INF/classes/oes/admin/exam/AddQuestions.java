package oes.admin.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.AdminModel;

@WebServlet("/addQuestions")
public class AddQuestions extends bootstrap {
	private static final long serialVersionUID = 1L;

	public AddQuestions() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		String examId = request.getParameter("examId");
		AdminModel am = new AdminModel();
		request.setAttribute("examName", am.getAttributeValue("exams", "name", examId));
		request.setAttribute("examId", request.getParameter("examId"));
		request.setAttribute("categoryOptions", am.getCategoryOptions());
		this.render(request, response, "/addQuestions.jsp", "/adminPrivate.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String question = request.getParameter("question");
		String option1 = request.getParameter("option1");
		String option2 = request.getParameter("option2");
		String option3 = request.getParameter("option3");
		String option4 = request.getParameter("option4");
		String option5 = request.getParameter("option5");
		String answer = request.getParameter("answer");
		String mark = request.getParameter("mark");
		String negative = request.getParameter("negative");
		String examId = request.getParameter("examId");
		String examName = request.getParameter("examName");
		String category = request.getParameter("category");
		AdminModel am = new AdminModel();
		am.addQuestion(examId, question, option1, option2, option3, option4, option5, answer, mark, negative, category);
		this.flashMessage(request, "questionMsg", "A new question is added to the Exam named <b>" + examName + "</b>.",
				0);
		response.sendRedirect(serverPath + "listOfQuestions?examId=" + examId);
		return;
	}

}
