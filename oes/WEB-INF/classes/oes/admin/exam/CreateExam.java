package oes.admin.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.AdminModel;

@WebServlet("/createExam")
public class CreateExam extends bootstrap {
	private static final long serialVersionUID = 1L;

	public CreateExam() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		this.render(request, response, "/createExam.jsp", "/adminPrivate.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String examName = request.getParameter("examName");
		String examTime = request.getParameter("examTime");
		String examInstructions = request.getParameter("examInstructions");
		AdminModel am = new AdminModel();
		am.createExam(examName, examTime, examInstructions);
		this.flashMessage(request, "msg2", "Exam named " + examName + " has been created.", 0);
		response.sendRedirect(serverPath + "listOfExams");
		return;
	}

}
