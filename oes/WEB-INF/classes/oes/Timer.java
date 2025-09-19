package oes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/timer")
public class Timer extends bootstrap {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterMap().containsKey("startTime") && request.getParameterMap().containsKey("totalTime")) {
			long startTime = Long.parseLong(request.getParameter("startTime"));
			long totalTime = Long.parseLong(request.getParameter("totalTime"));
			long remainingTime = this.getTimeMinutes(startTime, totalTime);
			long seconds = this.getTimeSeconds(startTime, totalTime);
			out = response.getWriter();
			out.println(remainingTime + " : " + seconds);// 1454315120484
		}
	}

}
