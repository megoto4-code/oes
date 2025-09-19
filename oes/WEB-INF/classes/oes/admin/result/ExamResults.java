package oes.admin.result;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.ResultModel;

@WebServlet("/examResults")
public class ExamResults extends bootstrap {
	private static final long serialVersionUID = 1L;

	public ExamResults() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		this.autoRefresh(request, response);
		ResultModel rm = new ResultModel();
		request.setAttribute("page", "Mange Results");
		request.setAttribute("listOfExam", rm.getListOfExams());		
		this.render(request, response, "/examResults.jsp", "/adminPrivate.jsp");
	}

}
