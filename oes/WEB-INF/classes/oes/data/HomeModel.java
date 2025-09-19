package oes.data;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeModel extends AdminModel {

	public String getExamOptions() {
		String result = "";
		result = result + "<option value=\"\">Select Exam</option>";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from exams");
			while (rs.next()) {
				String status = rs.getString("status");
				if (status.equals("1")) {
					result = result + "<option value=\"" + rs.getString("id") + "\">" + rs.getString("name")
							+ "</option>";
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void registerCandidate(String roll, String name, String gender, String mobile, String email, String dob,
			String address, String examId, String systemNo, String uniqueId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			PreparedStatement ps;
			String sql = "insert into register(roll, name, gender, mobile, email, dob, address, examId, systemNo, uniqueId, examCompleted) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, roll);
			ps.setString(2, name);
			ps.setString(3, gender);
			ps.setString(4, mobile);
			ps.setString(5, email);
			ps.setString(6, dob);
			ps.setString(7, address);
			ps.setString(8, examId);
			ps.setString(9, systemNo);
			ps.setString(10, uniqueId);
			ps.setString(11, "0");
			ps.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isAnswerInitialized(String registerId) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from answersheet where registerId=" + registerId);
			int i = 0;
			while (rs.next()) {
				i++;
			}
			if (i > 0) {
				result = true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}

	public void initAnswerSheet(String examId, String registerId) {
		ArrayList<String> questions = new ArrayList<String>();
		questions = this.getQuestionsForExam(examId, "all");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			PreparedStatement ps;
			con.setAutoCommit(false);
			String sql = "insert into answersheet (registerId, questionId, answer) values (?, ?, ?)";
			ps = con.prepareStatement(sql);
			for (int i = 0; i < questions.size(); i++) {
				ps.setString(1, registerId);
				ps.setString(2, questions.get(i));
				ps.setString(3, "");
				ps.executeUpdate();
			}
			con.commit();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	public ArrayList<String> getQuestionsForExam(String examId, String category) {
		ArrayList<String> questions = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs;
			if (category.equals("all")) {
				rs = stmt.executeQuery("select * from questions where examId=" + examId);
			} else {
				rs = stmt.executeQuery("select * from questions where examId=" + examId + " AND category=" + category);
			}
			while (rs.next()) {
				if (questions.contains(rs.getString("id")) == false) {
					questions.add(rs.getString("id"));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return questions;
	}

	public String getFirstQuestionId(String examId, String categoryId) {
		String result = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select * from questions where examId=" + examId + " AND category=" + categoryId);
			int i = 0;
			while (rs.next()) {
				if (i == 0)
					result = rs.getString("id");
				i++;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public String getAnswerId(String registerId, String questionId) {
		String result = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from answersheet where registerId='" + registerId
					+ "' AND questionId='" + questionId + "'");
			while (rs.next()) {
				result = rs.getString("id");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<String> getAnswersForExam(String registerId) {
		ArrayList<String> answers = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("select * from answersheet where registerId=" + registerId);
			while (rs.next()) {
				if (answers.contains(rs.getString("id")) == false) {
					answers.add(rs.getString("id"));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return answers;
	}

	public ArrayList<String> removeBlankAnswers(ArrayList<String> answers) {
		ArrayList<String> answer = new ArrayList<String>();
		for (int i = 0; i < answers.size(); i++) {
			String ans = this.getAttributeValue("answersheet", "answer", answers.get(i)) + "";
			if (ans.equals("1") || ans.equals("2") || ans.equals("3") || ans.equals("4") || ans.equals("5")) {
				if (answer.contains(answers.get(i)) == false) {
					answer.add(answers.get(i));
				}
			}
		}
		return answer;
	}

	public ArrayList<String> getAnswersCategorised(ArrayList<String> answers, String categoryId) {
		ArrayList<String> answer = new ArrayList<String>();
		for (int i = 0; i < answers.size(); i++) {
			String questionId = this.getAttributeValue("answersheet", "questionId", answers.get(i));
			if (categoryId.equals(this.getAttributeValue("questions", "category", questionId))) {
				if (answer.contains(answers.get(i)) == false) {
					answer.add(answers.get(i));
				}
			}
		}
		return answer;
	}

	public String getAttempts(String registerId, String cssClass) {
		String result = "";
		String examId = this.getAttributeValue("register", "examId", registerId);
		ArrayList<String> categories = this.getCategoryForExam(examId);
		int cNo = categories.size();
		int totalQuestions = 0;
		result += "<table class=\"" + cssClass + "\">";
		result += "<tr>";
		result += "<th>Subject Name</th>";
		result += "<th>Total No of questions</th>";
		result += "<th>Total No of Attempted questions</th>";
		result += "<th>Total No of Unattempted questions</th>";
		result += "</tr>";
		for (int i = 0; i < cNo; i++) {
			result += "<tr>";
			ArrayList<String> questions = this.getQuestionsForExam(examId, categories.get(i));
			ArrayList<String> answers = this.getAnswersForExam(registerId);
			answers = this.removeBlankAnswers(answers);
			result += "<td>";
			result += this.getAttributeValue("categories", "name", categories.get(i));
			result += "</td>";
			result += "<td>";
			result += questions.size();
			totalQuestions += questions.size();
			result += "</td>";
			result += "<td>";
			result += this.getAnswersCategorised(answers, categories.get(i)).size();
			result += "</td>";
			result += "<td>";
			int unattempted = questions.size() - this.getAnswersCategorised(answers, categories.get(i)).size();
			result += unattempted;
			result += "</td>";
			result += "</tr>";
		}
		result += "<tr>";
		result += "<th>Total";
		result += "</th>";
		result += "<th>";
		result += totalQuestions;
		result += "</th>";
		result += "<th>";
		result += this.removeBlankAnswers(this.getAnswersForExam(registerId)).size();
		result += "</th>";
		result += "<th>";
		result += totalQuestions - this.removeBlankAnswers(this.getAnswersForExam(registerId)).size();
		result += "</th>";
		result += "</tr>";
		result += "</table>";
		return result;
	}
}
