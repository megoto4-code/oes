package oes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oes.data.jdbcConnection;

@WebServlet("/login")
public class Login extends bootstrap {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession(false);
		//response.addHeader("Refresh","20;./home");
		jdbcConnection jc = new jdbcConnection();
		try {
			if (session != null)
				if (session.getAttribute("user") != null)
					if (session.getAttribute("user").equals(jc.getAttributeValue("user", "username", "1"))) {
						response.sendRedirect(serverPath + "index");
						return;
					}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		this.render(request, response, "/login.jsp", "/adminPublic.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		jdbcConnection jc = new jdbcConnection();
		if (username.equals(jc.getAttributeValue("user", "username", "1"))
				&& password.equals(jc.getAttributeValue("user", "password", "1"))) {
			this.flashMessage(request, "msg1", "Authenticaion Success. Congratulation.", 1);
			session = request.getSession();
			session.setAttribute("user", username);
			response.sendRedirect(serverPath + "admin");
			return;
		} else {
			this.flashMessage(request, "msg1", "Authenticaion Failure. Please try again.", 2);
		}
		doGet(request, response);
	}

}
