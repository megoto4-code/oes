package oes.home;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.data.HomeModel;
import oes.data.jdbcConnection;

@WebServlet("/home")
public class Home extends bootstrap {
	private static final long serialVersionUID = 1L;

	public Home() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initApp();
		HomeModel hm = new HomeModel();
		request.setAttribute("examOptions", hm.getExamOptions());
		this.render(request, response, "/home.jsp", "/adminPublic.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selectExam = request.getParameter("selectExam");
		String systemNo = request.getParameter("systemNo");
		String password = request.getParameter("password");
		jdbcConnection jc = new jdbcConnection();
		if (password.equals(jc.getAttributeValue("user", "password", "1")) && !selectExam.equals("")
				&& !systemNo.equals("")) {
			//this.flashMessage(request, "homeMsg", "Authenticaion Success. Congratulation.", 1);
			session = request.getSession();
			session.setAttribute("startExam", true);
			request.setAttribute("examId", selectExam);
			session.setAttribute("examName", jc.getAttributeValue("exams", "name", selectExam));
			session.setAttribute("systemNo", "C" + systemNo);
			response.sendRedirect(serverPath + "register?examId=" + selectExam);
			return;
		} else {
			this.flashMessage(request, "homeMsg",
					"Authenticaion Failure or one or more field is empty. Please try again.", 2);
		}
		doGet(request, response);
	}

}
