package oes.home;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oes.bootstrap;
import oes.data.HomeModel;

/**
 * Servlet implementation class ExitExam
 */
@WebServlet("/exitExam")
public class ExitExam extends bootstrap {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterMap().containsKey("examSummary") && request.getParameterMap().containsKey("registerId")
				&& request.getParameterMap().containsKey("startTime")
				&& request.getParameterMap().containsKey("totalTime")) {
			String examSummary = request.getParameter("examSummary");
			String registerId = request.getParameter("registerId");
			long startTime = Long.parseLong(request.getParameter("startTime"));
			long totalTime = Long.parseLong(request.getParameter("totalTime"));
			if (examSummary.equals("yes")) {
				HomeModel hm = new HomeModel();
				long time = totalTime - this.getTimeMinutes(startTime, totalTime);
				if (hm.getAttributeValue("register", "examCompleted", registerId).equals("0")) {
					hm.setAttributeValue("register", "examCompleted", registerId, time + "");
				}
				String examId = hm.getAttributeValue("register", "examId", registerId);
				request.setAttribute("exam", hm.getAttributeValue("exams", "name", examId));
				request.setAttribute("roll", hm.getAttributeValue("register", "roll", registerId));
				request.setAttribute("name", hm.getAttributeValue("register", "name", registerId));
				request.setAttribute("time", hm.getAttributeValue("register", "examCompleted", registerId));
				request.setAttribute("attempts", hm.getAttempts(registerId, "table table-bordered"));
				this.render(request, response, "/examSummary.jsp", "/adminPublic.jsp");
			}
		} else {
			HttpSession session = request.getSession(false);
			if (session != null) {
				if (session.getAttribute("startExam") != null) {
					if (session.getAttribute("startExam").equals(true)) {
						session.setAttribute("startExam", null);
						session.setAttribute("examName", null);
						session.setAttribute("systemNo", null);
						session.setAttribute("examActivated", null);
						// session.invalidate();
						this.flashMessage(request, "homeMsg", "You have successfully ended your exam.", 1);
						response.sendRedirect(serverPath + "home");
						return;
					} else {
						response.sendRedirect(serverPath + "home");
						return;
					}

				} else {
					response.sendRedirect(serverPath + "home");
					return;
				}
			} else {
				response.sendRedirect(serverPath + "home");
				return;
			}
		}
	}

}
