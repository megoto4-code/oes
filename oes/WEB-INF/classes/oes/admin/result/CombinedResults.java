package oes.admin.result;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.ResultModel;

@WebServlet("/combinedResults")
public class CombinedResults extends bootstrap {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		this.autoRefresh(request, response);
		if (request.getParameterMap().containsKey("examId")) {
			String examId[] = request.getParameterValues("examId");
			ResultModel rm = new ResultModel();
			String examNameList = "";
			for (int i = 0; i < examId.length; i++) {
				examNameList += "<li>";
				examNameList += rm.getAttributeValue("exams", "name", examId[i]);
				examNameList += "</li>";
			}
			request.setAttribute("page", "Combined Results");
			request.setAttribute("examNameList", examNameList);
			request.setAttribute("combinedResults", rm.getCombinedResults(examId));
			this.render(request, response, "/combinedResults.jsp", "/adminPrivate.jsp");
		} else {
			this.flashMessage(request, "examResultsMsg", "Select at least one item", 2);
			response.sendRedirect(serverPath + "examResults");
		}
	}

}
