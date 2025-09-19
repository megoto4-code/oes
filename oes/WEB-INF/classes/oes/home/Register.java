package oes.home;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.HomeModel;

@WebServlet("/register")
public class Register extends bootstrap {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response, true);
		initApp();
		String uniqueId = UUID.randomUUID().toString();
		System.out.println("Your register id:" + uniqueId);
		request.setAttribute("uniqueId", uniqueId);
		request.setAttribute("examId", request.getParameter("examId"));
		this.render(request, response, "/register.jsp", "/adminPublic.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roll = request.getParameter("roll");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String examId = request.getParameter("examId");
		String systemNo = request.getParameter("systemNo");
		String uniqueId = request.getParameter("uniqueId");
		HomeModel hm = new HomeModel();
		hm.registerCandidate(roll, name, gender, mobile, email, dob, address, examId, systemNo, uniqueId);
		System.out.println("Registration completed.");
		response.sendRedirect(serverPath + "examInfo?uniqueId=" + uniqueId + "&examId=" + examId);
		return;
	}

}
