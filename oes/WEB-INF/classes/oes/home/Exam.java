package oes.home;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oes.bootstrap;
import oes.admin.Authorize;
import oes.data.HomeModel;

@WebServlet("/exam")
public class Exam extends bootstrap {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Authorize a = new Authorize(request, response, true);
		a.examActivationCheck(request, response);
		initApp();
		session = request.getSession(false);
		HomeModel hm = new HomeModel();
		String examId = request.getParameter("examId");
		long startTime = Long.parseLong(session.getAttribute("startTime").toString());
		long totalTime = Long.parseLong(hm.getAttributeValue("exams", "time", examId)) - 1;
		String uniqueId = request.getParameter("uniqueId");
		String categoryId = request.getParameter("categoryId");
		String questionId = request.getParameter("questionId");
		String questionNo = request.getParameter("questionNo");
		String mark = hm.getAttributeValue("questions", "mark", questionId);
		String negative = hm.getAttributeValue("questions", "negative", questionId);
		String registerId = hm.getAttributeValue("register", "uniqueId", uniqueId, "id");
		if (this.getTimeMinutes(startTime, totalTime) == 0 && this.getTimeSeconds(startTime, totalTime) == 0) {
			if (response.isCommitted() == false)
				response.sendRedirect(serverPath + "exitExam?examSummary=yes&registerId=" + registerId + "&startTime="
						+ startTime + "&totalTime=" + (totalTime + 1));
		}
		request.setAttribute("categoriesMenu", this.setCategoriesMenu(uniqueId, examId, categoryId));
		request.setAttribute("registerId", registerId);
		request.setAttribute("examId", examId);
		request.setAttribute("uniqueId", uniqueId);
		request.setAttribute("mark", mark);
		request.setAttribute("negative", Float.valueOf(negative) / 100);
		request.setAttribute("questionLinks",
				this.getQuestionLinks(examId, uniqueId, categoryId, registerId, questionId));
		request.setAttribute("questionNo", questionNo);
		request.setAttribute("questionId", questionId);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("question", hm.getAttributeValue("questions", "question", questionId));
		request.setAttribute("totalTime", totalTime);
		request.setAttribute("options", this.getOptions(registerId, questionId));
		request.setAttribute("radios", this.setRadios(registerId, questionId));
		request.setAttribute("startTime", startTime);
		request.setAttribute("examInstructions", hm.getAttributeValue("exams", "instructions", examId));
		request.setAttribute("examInfo", this.getExamInfo(examId, "table table-striped"));
		request.setAttribute("forwardLink", this.getForwardLink(uniqueId, examId, categoryId, questionId, questionNo));
		request.setAttribute("backwardLink",
				this.getBackwardLink(uniqueId, examId, categoryId, questionId, questionNo));

		this.render(request, response, "/exam.jsp", "/examLayout.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HomeModel hm = new HomeModel();
		String examId = request.getParameter("examId");
		String uniqueId = request.getParameter("uniqueId");
		String categoryId = request.getParameter("categoryId");
		String questionId = request.getParameter("questionId");
		String questionNo = request.getParameter("questionNo");
		String answer = request.getParameter("option") + "";
		String form = request.getParameter("form");
		long startTime = Long.parseLong(request.getParameter("startTime"));
		long totalTime = Long.parseLong(request.getParameter("totalTime"));
		String registerId = hm.getAttributeValue("register", "uniqueId", uniqueId, "id");
		if (this.getTimeMinutes(startTime, totalTime) == 0 && this.getTimeSeconds(startTime, totalTime) == 0) {
			if (response.isCommitted() == false)
				response.sendRedirect(serverPath + "exitExam?examSummary=yes&registerId=" + registerId + "&startTime="
						+ startTime + "&totalTime=" + (totalTime + 1));
		}

		String answerId = hm.getAnswerId(registerId, questionId);
		if (form.equals("save")) {
			hm.setAttributeValue("answersheet", "answer", answerId, answer);
		} else if (form.equals("clear")) {
			hm.setAttributeValue("answersheet", "answer", answerId, "-1");
		}
		String path = this.serverPath + "exam?uniqueId=" + uniqueId + "&examId=" + examId + "&categoryId=" + categoryId
				+ "&questionId=" + questionId + "&questionNo=" + questionNo;
		response.sendRedirect(path);
	}

	public int getQuestionPosition(ArrayList<String> questions, String questionNo) {
		int result = 0;
		for (int i = 0; i < questions.size(); i++) {
			if (Integer.parseInt(questionNo) == i + 1) {
				result = Integer.parseInt(questions.get(i));
			}
		}
		return result;
	}

	public String getForwardLink(String uniqueId, String examId, String categoryId, String questionId,
			String questionNo) {
		String result = "";
		HomeModel hm = new HomeModel();
		ArrayList<String> questions = hm.getQuestionsForExam(examId, categoryId);
		int totalQ = questions.size();
		if (Integer.parseInt(questionNo) == totalQ) {
			questionNo = "1";
		} else {
			questionNo = (Integer.parseInt(questionNo) + 1) + "";
		}
		questionId = this.getQuestionPosition(questions, questionNo) + "";
		result = this.serverPath + "exam?uniqueId=" + uniqueId + "&examId=" + examId + "&categoryId=" + categoryId
				+ "&questionId=" + questionId + "&questionNo=" + questionNo;
		return result;
	}

	public String getBackwardLink(String uniqueId, String examId, String categoryId, String questionId,
			String questionNo) {
		String result = "";
		HomeModel hm = new HomeModel();
		ArrayList<String> questions = hm.getQuestionsForExam(examId, categoryId);
		int totalQ = questions.size();
		if (Integer.parseInt(questionNo) == 1) {
			questionNo = totalQ + "";
		} else {
			questionNo = (Integer.parseInt(questionNo) - 1) + "";
		}
		questionId = this.getQuestionPosition(questions, questionNo) + "";
		result = this.serverPath + "exam?uniqueId=" + uniqueId + "&examId=" + examId + "&categoryId=" + categoryId
				+ "&questionId=" + questionId + "&questionNo=" + questionNo;
		return result;
	}

	public String setCategoriesMenu(String uniqueId, String examId, String categoryId) {
		String result = "";
		HomeModel hm = new HomeModel();
		ArrayList<String> categories = hm.getCategoryForExam(examId);
		for (int i = 0; i < categories.size(); i++) {
			String selected = categories.get(i);
			selected = (selected.equals(categoryId) ? " class=\"active\"" : "");
			result += "<li" + selected + ">";
			String path = this.serverPath + "exam?uniqueId=" + uniqueId + "&examId=" + examId + "&categoryId="
					+ categories.get(i) + "&questionId=" + hm.getFirstQuestionId(examId, categories.get(i))
					+ "&questionNo=1";
			result += "<a href=\"" + path + "\">" + hm.getAttributeValue("categories", "name", categories.get(i))
					+ "</a>";
			result += "</li>";
		}
		return result;
	}

	public String getQuestionLinks(String examId, String uniqueId, String categoryId, String registerId,
			String questionId) {
		String result = "";
		HomeModel hm = new HomeModel();
		ArrayList<String> questions = hm.getQuestionsForExam(examId, categoryId);
		for (int i = 0; i < questions.size(); i++) {
			String answerId = hm.getAnswerId(registerId, questions.get(i));
			String choice = hm.getAttributeValue("answersheet", "answer", answerId) + "";
			String linkType = "btn-primary";
			if (questions.get(i).equals(questionId)) {
				linkType = "btn-default";
			} else {
				if (choice != null && choice != "") {
					if (choice.equals("-1")) {
						linkType = "btn-warning";
					} else if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")
							|| choice.equals("5")) {
						linkType = "btn-success";
					}
				}
			}
			String path = this.serverPath + "exam?uniqueId=" + uniqueId + "&examId=" + examId + "&categoryId="
					+ categoryId + "&questionId=" + questions.get(i) + "&questionNo=" + (i + 1);
			result += "<a type=\"button\" class=\"btn " + linkType + " btn-sm bottom-gap1\" href=\"" + path + "\"><b>";
			result += (i + 1);
			result += "</b></a>";
		}
		return result;
	}

	public String getOptions(String registerId, String questionId) {
		String result = "";
		HomeModel hm = new HomeModel();
		String answerId = hm.getAnswerId(registerId, questionId);
		String choice = hm.getAttributeValue("answersheet", "answer", answerId) + "";

		String option1 = hm.getAttributeValue("questions", "option1", questionId) + "";
		String option2 = hm.getAttributeValue("questions", "option2", questionId) + "";
		String option3 = hm.getAttributeValue("questions", "option3", questionId) + "";
		String option4 = hm.getAttributeValue("questions", "option4", questionId) + "";
		String option5 = hm.getAttributeValue("questions", "option5", questionId) + "";
		result += "<ul class=\"list-group\">";
		result += "<li class=\"list-group-item" + (choice.equals("1") ? " active" : "") + "\">Option A: <b>";
		result += option1;
		result += "</b></li>";
		result += "<li class=\"list-group-item" + (choice.equals("2") ? " active" : "") + "\">Option B: <b>";
		result += option2;
		result += "</b></li>";
		if (!option3.equals("")) {
			result += "<li class=\"list-group-item" + (choice.equals("3") ? " active" : "") + "\">Option C: <b>";
			result += option3;
			result += "</b></li>";
		}
		if (!option4.equals("")) {
			result += "<li class=\"list-group-item" + (choice.equals("4") ? " active" : "") + "\">Option D: <b>";
			result += option4;
			result += "</b></li>";
		}
		if (!option5.equals("")) {
			result += "<li class=\"list-group-item" + (choice.equals("5") ? " active" : "") + "\">Option E: <b>";
			result += option5;
			result += "</b></li>";
		}
		result += "</ul>";
		return result;
	}

	public String setRadios(String registerId, String questionId) {
		/*
		 * <label class="btn btn-primary"> <input type="radio" name="option"
		 * value="1" id="option1" autocomplete="off" checked> Option A </label>
		 * <label class="btn btn-primary"> <input type="radio" name="option"
		 * value="2" id="option2" autocomplete="off"> Option B </label> <label
		 * class="btn btn-primary"> <input type="radio" name="option" value="3"
		 * id="option3" autocomplete="off"> Option C </label>
		 */

		String result = "";
		HomeModel hm = new HomeModel();
		String answerId = hm.getAnswerId(registerId, questionId);
		String choice = hm.getAttributeValue("answersheet", "answer", answerId) + "";

		// String option1 = hm.getAttributeValue("questions", "option1",
		// questionId) + "";
		// String option2 = hm.getAttributeValue("questions", "option2",
		// questionId) + "";
		String option3 = hm.getAttributeValue("questions", "option3", questionId) + "";
		String option4 = hm.getAttributeValue("questions", "option4", questionId) + "";
		String option5 = hm.getAttributeValue("questions", "option5", questionId) + "";
		result += "<label class=\"btn btn-primary\">";
		result += "<input type=\"radio\" name=\"option\" value=\"1\" id=\"option1\" autocomplete=\"off\""
				+ (choice.equals("1") ? " checked" : "") + ">";
		result += "Option A";
		result += "</label>";
		result += "<label class=\"btn btn-primary\">";
		result += "<input type=\"radio\" name=\"option\" value=\"2\" id=\"option2\" autocomplete=\"off\""
				+ (choice.equals("2") ? " checked" : "") + ">";
		result += "Option B";
		result += "</label>";
		if (!option3.equals("")) {
			result += "<label class=\"btn btn-primary\">";
			result += "<input type=\"radio\" name=\"option\" value=\"3\" id=\"option3\" autocomplete=\"off\""
					+ (choice.equals("3") ? " checked" : "") + ">";
			result += "Option C";
			result += "</label>";
		}
		if (!option4.equals("")) {
			result += "<label class=\"btn btn-primary\">";
			result += "<input type=\"radio\" name=\"option\" value=\"4\" id=\"option4\" autocomplete=\"off\""
					+ (choice.equals("4") ? " checked" : "") + ">";
			result += "Option D";
			result += "</label>";
		}
		if (!option5.equals("")) {
			result += "<label class=\"btn btn-primary\">";
			result += "<input type=\"radio\" name=\"option\" value=\"5\" id=\"option5\" autocomplete=\"off\""
					+ (choice.equals("5") ? " checked" : "") + ">";
			result += "Option E";
			result += "</label>";
		}
		return result;
	}

}
