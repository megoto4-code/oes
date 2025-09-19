package oes.admin.result;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.ResultModel;

@WebServlet("/listOfCandidates")
public class ListOfCandidates extends bootstrap {
	private static final long serialVersionUID = 1L;

	public ListOfCandidates() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		this.autoRefresh(request, response);
		ResultModel rm = new ResultModel();
		if (request.getParameterMap().containsKey("examId") == false) {
			response.sendRedirect(serverPath + "admin");
			return;
		}
		String examId = request.getParameter("examId");
		request.setAttribute("examName", rm.getAttributeValue("exams", "name", examId));
		request.setAttribute("page", "List Of Candidates");
		request.setAttribute("listOfCandidates", rm.getListOfCandidates(examId));
		this.render(request, response, "/listOfCandidates.jsp", "/adminPrivate.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
