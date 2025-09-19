package oes.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oes.bootstrap;
import oes.data.jdbcConnection;

public class Authorize extends bootstrap {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// For Administration
	public Authorize(HttpServletRequest request, HttpServletResponse response) {
		super();
		boolean result = true;
		session = request.getSession(false);
		jdbcConnection jc = new jdbcConnection();
		try {
			if (session == null) {
				response.sendRedirect(serverPath + "index");
				result = false;
			} else {
				if (session.getAttribute("user") == null) {
					response.sendRedirect(serverPath + "index");
					result = false;
				} else if (session.getAttribute("user") != null)
					if (!session.getAttribute("user").equals(jc.getAttributeValue("user", "username", "1"))) {
						response.sendRedirect(serverPath + "index");
						result = false;
					}
			}

		} catch (NullPointerException | IOException | IllegalStateException e) {
			e.printStackTrace();
		}
		if (result == false) {
			System.out.println("Access denied! Must log in.");
		} else {

		}
	}

	// For starting the exam
	public Authorize(HttpServletRequest request, HttpServletResponse response, boolean startExam) {
		super();
		session = request.getSession(false);
		try {
			if (session == null) {
				response.sendRedirect(serverPath + "home");
				startExam = false;
				System.out.println("1D\n");
			} else {
				if (session.getAttribute("startExam") == null) {
					response.sendRedirect(serverPath + "home");
					startExam = false;
					System.out.println("2D\n");
				} else if (session.getAttribute("startExam") != null)
					if (!session.getAttribute("startExam").equals(true)) {
						response.sendRedirect(serverPath + "home");
						startExam = false;
						System.out.println("3D\n");
					}
			}

		} catch (NullPointerException | IOException | IllegalStateException e) {
			e.printStackTrace();
		}
		if (startExam == false) {
			System.out.println("Access denied! You must authenticate yourself first to start the exam.");
		} else {

		}
	}

	public void examActivationCheck(HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		session = request.getSession(false);
		try {
			if (session != null) {
				if (session.getAttribute("examActivated") != null) {
					if (session.getAttribute("examActivated").equals(true)) {
						result = true;
					}
				}
			}
			if (result == false && !response.isCommitted())
				response.sendRedirect(serverPath + "exitExam");
			return;
		} catch (NullPointerException | IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
}
