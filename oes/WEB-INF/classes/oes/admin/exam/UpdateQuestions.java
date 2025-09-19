package oes.admin.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.AdminModel;

@WebServlet("/updateQuestions")
public class UpdateQuestions extends bootstrap {
	private static final long serialVersionUID = 1L;

	public UpdateQuestions() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		String examId = request.getParameter("examId");
		String questionId = request.getParameter("questionId");
		AdminModel am = new AdminModel();
		request.setAttribute("examId", examId);
		request.setAttribute("questionId", questionId);
		request.setAttribute("examName", am.getAttributeValue("exams", "name", examId));
		request.setAttribute("question", am.getAttributeValue("questions", "question", questionId));
		request.setAttribute("option1", am.getAttributeValue("questions", "option1", questionId));
		request.setAttribute("option2", am.getAttributeValue("questions", "option2", questionId));
		request.setAttribute("option3", am.getAttributeValue("questions", "option3", questionId));
		request.setAttribute("option4", am.getAttributeValue("questions", "option4", questionId));
		request.setAttribute("option5", am.getAttributeValue("questions", "option5", questionId));
		request.setAttribute("answer", am.getAttributeValue("questions", "answer", questionId));
		request.setAttribute("mark", am.getAttributeValue("questions", "mark", questionId));
		request.setAttribute("negative", am.getAttributeValue("questions", "negative", questionId));
		request.setAttribute("questionNo", request.getParameter("questionNo"));
		request.setAttribute("categoryOptions",
				am.getCategoryOptions(am.getAttributeValue("questions", "category", questionId)));
		this.render(request, response, "/updateQuestion.jsp", "/adminPrivate.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String questionId = request.getParameter("questionId");
		String examId = request.getParameter("examId");
		String question = request.getParameter("question");
		String option1 = request.getParameter("option1");
		String option2 = request.getParameter("option2");
		String option3 = request.getParameter("option3");
		String option4 = request.getParameter("option4");
		String option5 = request.getParameter("option5");
		String answer = request.getParameter("answer");
		String mark = request.getParameter("mark");
		String negative = request.getParameter("negative");
		String category = request.getParameter("category");
		AdminModel am = new AdminModel();
		am.updateQuestion(examId, questionId, question, option1, option2, option3, option4, option5, answer, mark,
				negative, category);
		this.flashMessage(request, "questionMsg", "One question has been updated.", 0);
		response.sendRedirect(serverPath + "listOfQuestions?examId=" + examId);
		return;
	}

}
