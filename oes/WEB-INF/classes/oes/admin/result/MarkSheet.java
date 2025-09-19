package oes.admin.result;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.ResultModel;

/**
 * Servlet implementation class MarkSheet
 */
@WebServlet("/markSheet")
public class MarkSheet extends bootstrap {
	private static final long serialVersionUID = 1L;

	/**
	 * @see bootstrap#bootstrap()
	 */
	public MarkSheet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		ResultModel rm = new ResultModel();
		if (request.getParameterMap().containsKey("examId") == false
				&& request.getParameterMap().containsKey("registerId") == false) {
			response.sendRedirect(serverPath + "admin");
			return;
		}
		String examId = request.getParameter("examId");
		String registerId = request.getParameter("registerId");
		request.setAttribute("examId", examId);
		request.setAttribute("registerId", registerId);
		request.setAttribute("examName", rm.getAttributeValue("exams", "name", examId));
		request.setAttribute("page", "Marksheet");
		request.setAttribute("name", rm.getAttributeValue("register", "name", registerId));
		request.setAttribute("roll", rm.getAttributeValue("register", "roll", registerId));
		request.setAttribute("gender", rm.getAttributeValue("register", "gender", registerId));
		request.setAttribute("mobile", rm.getAttributeValue("register", "mobile", registerId));
		request.setAttribute("email", rm.getAttributeValue("register", "email", registerId));
		request.setAttribute("dob", rm.getAttributeValue("register", "dob", registerId));
		request.setAttribute("address", rm.getAttributeValue("register", "address", registerId));
		request.setAttribute("systemNo", rm.getAttributeValue("register", "systemNo", registerId));
		request.setAttribute("examCompleted", rm.getAttributeValue("register", "examCompleted", registerId));
		request.setAttribute("created", rm.getAttributeValue("register", "created", registerId));

		request.setAttribute("totalQuestions", rm.getTotalNoOfQuestions(examId));
		request.setAttribute("attempted", rm.getNoOfQuestionsAttempted(registerId));
		int notAttempted = rm.getTotalNoOfQuestions(examId) - rm.getNoOfQuestionsAttempted(registerId);
		request.setAttribute("notAttempted", notAttempted);
		request.setAttribute("rightAnswers", rm.getNoOfRightAnswersAttempted(registerId));
		int wrongAnswers = rm.getNoOfQuestionsAttempted(registerId) - rm.getNoOfRightAnswersAttempted(registerId);
		request.setAttribute("wrongAnswers", wrongAnswers);
		request.setAttribute("negative", rm.getNegative(registerId));
		request.setAttribute("mark", rm.getMark(registerId));
		float percentage = Float.valueOf(rm.getMark(registerId)) / Float.valueOf(rm.getTotalMarks(examId)) * 100;
		if (percentage < 0) {
			percentage = 0.0f;
		}
		request.setAttribute("percentage", percentage);
		this.render(request, response, "/markSheet.jsp", "/adminPrivate.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
