package oes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oes.data.jdbcConnection;

@WebServlet("/logout")
public class Logout extends bootstrap {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		jdbcConnection jc = new jdbcConnection();
		if (session != null) {
			if (session.getAttribute("user") != null) {
				if (session.getAttribute("user").equals(jc.getAttributeValue("user", "username", "1"))) {
					session.setAttribute("user", null);
					//session.invalidate();
					this.flashMessage(request, "msg1", "You are successfully logged out.", 1);
					response.sendRedirect(serverPath + "login");
					return;
				} else {
					response.sendRedirect(serverPath + "login");
					return;
				}

			} else {
				response.sendRedirect(serverPath + "login");
				return;
			}
		} else {
			response.sendRedirect(serverPath + "login");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
