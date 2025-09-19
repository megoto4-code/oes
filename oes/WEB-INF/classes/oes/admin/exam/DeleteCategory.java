package oes.admin.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oes.bootstrap;
import oes.data.jdbcConnection;

@WebServlet("/deleteCategory")
public class DeleteCategory extends bootstrap {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jdbcConnection jc = new jdbcConnection();
		String categoryId = request.getParameter("categoryId");
		if (jc.isDependentOn("questions", "category", categoryId) == false) {
			jc.removeRecord("categories", categoryId);
			this.flashMessage(request, "msg2", "One category has been deleted.", 1);
		} else {
			this.flashMessage(request, "msg2",
					"This category is cannot be deleted since one or more question is/are dependant on it.", 2);
		}
		response.sendRedirect(serverPath + "listOfExams");
		return;
	}

}
