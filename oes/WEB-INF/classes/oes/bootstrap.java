package oes;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import oes.data.AdminModel;
import oes.data.GetSettings;
import oes.data.jdbcConnection;

/**
 * Servlet implementation class bootstrap
 */
@WebServlet("/index")
public class bootstrap extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public PrintWriter out;
	public String serverPath;
	public HttpSession session;
	public String appName;
	public String appCopyright;

	public bootstrap() {
		super();
		serverPath = "http://localhost:8080/oes/";
		initApp();
	}

	private boolean isUserAutheticated(HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		session = request.getSession(false);
		jdbcConnection jc = new jdbcConnection();
		try {
			if (session != null)
				if (session.getAttribute("user") != null)
					if (session.getAttribute("user").equals(jc.getAttributeValue("user", "username", "1"))) {
						result = true;
					}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return result;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		out = response.getWriter();
		// System.out.println(request.getRequestURL());
		out.println("You are redirected...");
		if (this.isUserAutheticated(request, response) == true) {
			response.sendRedirect(serverPath + "admin");
		} else {
			response.sendRedirect(serverPath + "login");
		}
	}

	public void render(HttpServletRequest request, HttpServletResponse response, String page, String layout) {
		HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
			private final StringWriter sw = new StringWriter();

			@Override
			public PrintWriter getWriter() throws IOException {
				return new PrintWriter(sw);
			}

			@Override
			public String toString() {
				return sw.toString();
			}
		};
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		try {
			if (!response.isCommitted()) {
				// Start of Included Attributes
				request.setAttribute("serverPath", serverPath);
				request.setAttribute("appName", appName);
				request.setAttribute("appCopyright", appCopyright);
				// End of Included Attributes

				request.getRequestDispatcher(page).include(request, responseWrapper);
				String content = responseWrapper.toString();
				request.setAttribute("content", content);
				request.getRequestDispatcher("layouts" + layout).forward(request, response);
				return;
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void flashMessage(HttpServletRequest request, String id, String msg, int type) {
		session = request.getSession();
		String start;
		switch (type) {
		case 1:
			start = "<div class=\"alert alert-success\" role=\"alert\"><span class=\"glyphicon glyphicon-ok-sign\" aria-hidden=\"true\"></span> ";
			break;
		case 2:
			start = "<div class=\"alert alert-warning\" role=\"alert\"><span class=\"glyphicon glyphicon-remove-sign\" aria-hidden=\"true\"></span> ";
			break;
		default:
			start = "<div class=\"alert alert-info\" role=\"alert\"><span class=\"glyphicon glyphicon-info-sign\" aria-hidden=\"true\"></span> ";
		}
		String end = "</div>";
		msg = start + msg + end;
		session.setAttribute(id, msg);
	}

	public void initApp() {
		appName = this.getSetting("appName").value;
		appCopyright = this.getSetting("appCopyright").value;
	}

	public GetSettings getSetting(String property) {
		return new GetSettings(property);
	}

	public String getNegative(String negative) {
		String result = "";
		if (negative.equals("100"))
			result = "nil";
		else
			result = (Float.valueOf(negative) / 100) + "";
		return result;
	}

	public String addJs(String msg) {
		return "<script type=\"text/javascript\">" + msg + "</script>";
	}

	public String jsClassName(String id, String cName) {
		return "document.getElementById(\"" + id + "\").className = \"" + cName + "\";";
	}

	public String getExamInfo(String examId, String cssClass) {
		String result = "";
		AdminModel am = new AdminModel();
		ArrayList<String> categories = am.getCategoryForExam(examId);
		result += "<table class=\"" + cssClass + "\">";
		result += "<tr>";
		result += "<th>Subjects</th>";
		result += "<th>No of Questions</th>";
		result += "</tr>";
		int total = 0;
		for (int i = 0; i < categories.size(); i++) {
			result += "<tr>";
			result += "<td>" + am.getAttributeValue("categories", "name", categories.get(i)) + "</td>";
			result += "<td>" + am.getNoOfCategorisedQuestions(examId, categories.get(i)) + "</td>";
			total += am.getNoOfCategorisedQuestions(examId, categories.get(i));
			result += "</tr>";
		}
		result += "<tr>";
		result += "<td><b>Total</b></td>";
		result += "<td><b>" + total + "</b></td>";
		result += "</tr>";
		result += "<tr>";
		result += "<td><b>Time</b></td>";
		result += "<td><b>" + am.getAttributeValue("exams", "time", examId) + " minutes</b></td>";
		result += "</tr>";
		result += "<tr>";
		result += "<td><b>Total Marks</b></td>";
		result += "<td><b>" + am.getTotalMarks(examId) + "</b></td>";
		result += "</tr>";
		result += "</table>";
		return result;
	}

	public long getTimeMinutes(long startTime, long totalTime) {
		long currentTime = System.currentTimeMillis();
		long remainingTime = (currentTime - startTime) / (1000 * 60);
		remainingTime = totalTime - remainingTime;
		if (remainingTime <= 0) {
			remainingTime = 0;
		}
		return remainingTime;
	}

	public long getTimeSeconds(long startTime, long totalTime) {
		long currentTime = System.currentTimeMillis();
		long time = (currentTime - startTime) / (1000);
		long seconds = time % 60;
		if (time / 60 <= totalTime) {
			seconds = 60 - seconds;
		} else {
			seconds = 0;
		}
		return seconds;
	}

	public void autoRefresh(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameterMap().containsKey("refresh") == true) {
			try {
				if (!request.getParameter("refresh").equals("")) {
					int seconds = Integer.parseInt(request.getParameter("refresh"));
					response.setIntHeader("Refresh", seconds);
				}
			} catch (NumberFormatException E) {
				System.out.println("Refresh value should be a positive number!");
			}
		}
	}

}
