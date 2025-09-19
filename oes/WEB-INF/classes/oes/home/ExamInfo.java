package oes.home;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.AdminModel;
import oes.data.HomeModel;

@WebServlet("/examInfo")
public class ExamInfo extends bootstrap {
	private static final long serialVersionUID = 1L;

	public ExamInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response, true);
		initApp();
		AdminModel am = new AdminModel();
		String examId = request.getParameter("examId");
		request.setAttribute("examInstructions", am.getAttributeValue("exams", "instructions", examId));
		request.setAttribute("examInfo", this.getExamInfo(examId, "table table-striped"));
		request.setAttribute("examId", request.getParameter("examId"));
		request.setAttribute("uniqueId", request.getParameter("uniqueId"));
		this.render(request, response, "/examInfo.jsp", "/adminPublic.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uniqueId = request.getParameter("uniqueId");
		String examId = request.getParameter("examId");
		if (!request.getParameterMap().containsKey("confirm")) {
			this.flashMessage(request, "confirmationMsg", "You cannot proceed without agreeing.", 2);
			response.sendRedirect(serverPath + "examInfo?uniqueId=" + uniqueId + "&examId=" + examId);
		} else if (!request.getParameter("confirm").equals("yes")) {
			this.flashMessage(request, "confirmationMsg", "You cannot proceed without agreeing.", 2);
			response.sendRedirect(serverPath + "examInfo?uniqueId=" + uniqueId + "&examId=" + examId);
		} else {
			HomeModel hm = new HomeModel();
			session = request.getSession(false);
			session.setAttribute("examActivated", true);
			session.setAttribute("startTime", System.currentTimeMillis());			
			String registerId = hm.getAttributeValue("register", "uniqueId", uniqueId, "id");
			if (hm.isAnswerInitialized(registerId) == false)
				hm.initAnswerSheet(examId, registerId);
			ArrayList<String> categories = hm.getCategoryForExam(examId);
			int minId = 0;
			for (int i = 0; i < categories.size(); i++) {
				if (minId <= Integer.parseInt(categories.get(i)))
					minId = Integer.parseInt(categories.get(i));
			}
			String questionId = hm.getFirstQuestionId(examId, minId + "");
			response.sendRedirect(serverPath + "exam?uniqueId=" + uniqueId + "&examId=" + examId + "&categoryId="
					+ minId + "&questionId=" + questionId + "&questionNo=1");
		}
		return;
	}

}
