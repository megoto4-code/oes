package oes.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.data.jdbcConnection;

@WebServlet("/settings")
public class Settings extends bootstrap {
	private static final long serialVersionUID = 1L;

	public Settings() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		request.setAttribute("page", "settings");
		this.render(request, response, "/settings.jsp", "/adminPrivate.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jdbcConnection jc = new jdbcConnection();
		if (request.getParameterMap().containsKey("form")) {
			if (request.getParameter("form").equals("settingsProfile")) {
				String oldUsername = request.getParameter("oldUsername");
				String oldPassword = request.getParameter("oldPassword");
				String newUsername = request.getParameter("newUsername");
				String newPassword = request.getParameter("newPassword");
				String reTypeNewPassword = request.getParameter("reTypeNewPassword");
				if (oldUsername.equals(jc.getAttributeValue("user", "username", "1"))
						&& oldPassword.equals(jc.getAttributeValue("user", "password", "1"))) {
					if (newPassword.equals(reTypeNewPassword)) {
						jc.setAttributeValue("user", "username", "1", newUsername);
						jc.setAttributeValue("user", "password", "1", newPassword);
						this.flashMessage(request, "settingsMsg", "Your username/password is/are updated.", 1);
					} else {
						this.flashMessage(request, "settingsMsg",
								"Your settings are not updated. Password mismatch occured.", 2);
					}
				} else {
					this.flashMessage(request, "settingsMsg",
							"Your settings are not updated. Old Username/Password is incorrect", 2);
				}
			} else {
				String id = jc.getAttributeValue("settings", "property", "appName", "id");
				jc.setAttributeValue("settings", "value", id, request.getParameter("appName"));
				id = jc.getAttributeValue("settings", "property", "appCopyright", "id");
				jc.setAttributeValue("settings", "value", id, request.getParameter("appCopyright"));
				this.flashMessage(request, "settingsMsg", "Your settings are updated.", 0);
			}
		}
		response.sendRedirect(serverPath + "settings");
		return;
	}

}
