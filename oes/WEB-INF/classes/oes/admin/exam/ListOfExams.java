package oes.admin.exam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.AdminModel;

@WebServlet("/listOfExams")
public class ListOfExams extends bootstrap {
	private static final long serialVersionUID = 1L;

	public ListOfExams() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Authorize(request, response);
		initApp();
		AdminModel am = new AdminModel();
		request.setAttribute("listOfExams", am.getListOfExams());
		request.setAttribute("listOfCategories", am.getCategories());
		if (request.getParameterMap().containsKey("updateCategoryId")) {
			request.setAttribute("form", "update");
			request.setAttribute("categoryId", request.getParameter("updateCategoryId"));
			request.setAttribute("categoryName",
					am.getAttributeValue("categories", "name", request.getParameter("updateCategoryId")));
			String str = this.addJs(this.jsClassName("collapseShowCategories", "collapse in"));
			request.setAttribute("customJsScript", str);

		} else {
			request.setAttribute("form", "add");
		}
		this.render(request, response, "/listOfExams.jsp", "/adminPrivate.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("formName").equals("addCategory")) {
			AdminModel am = new AdminModel();
			am.addCategory(request.getParameter("categoryName"));
			this.flashMessage(request, "msg2", "One category has been added.", 1);
		} else if (request.getParameter("formName").equals("updateCategory")) {
			AdminModel am = new AdminModel();
			am.updateCategory(request.getParameter("categoryName"), request.getParameter("categoryId"));
			this.flashMessage(request, "msg2", "One category has been updated.", 1);
			String str = this.addJs(this.jsClassName("collapseShowCategories", "collapse in"));
			request.setAttribute("customJsScript", str);
		}
		response.sendRedirect(serverPath + "listOfExams");
		return;
	}

}
