package oes.data;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import oes.bootstrap;

public class ResultModel extends HomeModel {

	public String getListOfExams() {
		String result = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from exams order by id desc");
			bootstrap b = new bootstrap();
			int i = 1;
			while (rs.next()) {
				result = result + "<tr class=\"text-center\"><td class=\"text-left\">";
				result = result + "<input type=\"checkbox\" value=\"" + rs.getString("id") + "\" name=\"examId\"/>";
				result = result + "</td><td>" + i++ + "</td>";
				result = result + "<td>" + rs.getString("name") + "</td>";
				result = result + "<td>" + rs.getString("time") + " minute</td>";
				Statement stmt1 = (Statement) con.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select * from register where examId=" + rs.getString("id"));
				int total = 0;
				while (rs1.next()) {
					total++;
				}
				result = result + "<td>" + total + "</td>";
				result = result + "<td><a href=\"" + b.serverPath + "listOfCandidates?examId=" + rs.getString("id")
						+ "\" class=\"btn btn-info btn-xs pull-right gap-left\">Open</a>";
				result = result + "</tr>";
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

	public ArrayList<String> getRegister(String examId) {
		ArrayList<String> register = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from register where examId=" + examId);
			while (rs.next()) {
				if (register.contains(rs.getString("id")) == false) {
					register.add(rs.getString("id"));
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
		// System.out.println("Register:" + register);
		return register;
	}

	public ArrayList<String> getRegister(String examId[]) {
		ArrayList<String> register = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			String sql = "select * from register where ";// examId=" + examId);
			for (int i = 0; i < examId.length; i++) {
				sql += "examId=" + examId[i];
				if ((i + 1) < examId.length) {
					sql += " OR ";
				}
			}
			// System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (register.contains(rs.getString("id")) == false) {
					register.add(rs.getString("id"));
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
		// System.out.println("Register:" + register);
		return register;
	}

	public ArrayList<Float> getMarks(String examId) {
		ArrayList<String> register = this.getRegister(examId);
		ArrayList<Float> marks = new ArrayList<Float>();
		float mark, total;
		for (int i = 0; i < register.size(); i++) {
			mark = this.getMark(register.get(i));
			total = this.getTotalMarks(examId);
			mark = mark / total * 100;
			marks.add(mark);
		}
		// System.out.println("Marks:" + marks);
		return marks;
	}

	public ArrayList<Float> getMarks(String examId[]) {
		ArrayList<String> register = this.getRegister(examId);
		ArrayList<Float> marks = new ArrayList<Float>();
		float mark, total;
		for (int i = 0; i < register.size(); i++) {
			mark = this.getMark(register.get(i));
			String examID = this.getAttributeValue("register", "examId", register.get(i));
			total = this.getTotalMarks(examID);
			mark = mark / total * 100;
			marks.add(mark);
		}
		// System.out.println("Marks:" + marks);
		return marks;
	}

	public ArrayList<String> sortCandidateOnMark(String examId) {
		ArrayList<String> register = this.getRegister(examId);
		ArrayList<Float> marks = this.getMarks(examId);
		ArrayList<String> sortedRegister = new ArrayList<String>();
		float g;
		int i, index = 0;
		while (register.size() > 0) {
			for (i = 0, g = -9999.999f; i < register.size(); i++) {
				if (g <= marks.get(i)) {
					g = marks.get(i);
					index = i;
				}
			}
			if (sortedRegister.contains(register.get(index)) == false) {
				sortedRegister.add(register.get(index));
				register.remove(index);
				marks.remove(index);
			}
		}
		return sortedRegister;
	}

	public ArrayList<String> sortCandidateOnMark(String examId[]) {
		ArrayList<String> register = this.getRegister(examId);
		ArrayList<Float> marks = this.getMarks(examId);
		ArrayList<String> sortedRegister = new ArrayList<String>();
		float g;
		int i, index = 0;
		while (register.size() > 0) {
			for (i = 0, g = -9999.999f; i < register.size(); i++) {
				if (g <= marks.get(i)) {
					g = marks.get(i);
					index = i;
				}
			}
			if (sortedRegister.contains(register.get(index)) == false) {
				sortedRegister.add(register.get(index));
				register.remove(index);
				marks.remove(index);
			}
		}
		return sortedRegister;
	}

	public String getListOfCandidates(String examId) {
		ArrayList<String> register = this.sortCandidateOnMark(examId);
		String result = "";
		bootstrap bs = new bootstrap();
		for (int no = 0; no < register.size(); no++) {
			result += "<tr class=\"text-center\">";
			result += "<td>" + (no + 1) + "</td>";
			result += "<td>" + this.getAttributeValue("register", "roll", register.get(no)) + "</td>";
			result += "<td>" + this.getAttributeValue("register", "name", register.get(no)) + "</td>";
			result += "<td>" + this.getNoOfQuestionsAttempted(register.get(no)) + "</td>";
			result += "<td>" + this.getNoOfRightAnswersAttempted(register.get(no)) + "</td>";
			int wrong = this.getNoOfQuestionsAttempted(register.get(no))
					- this.getNoOfRightAnswersAttempted(register.get(no));
			result += "<td>" + wrong + "</td>";
			result += "<td>" + this.getNegative(register.get(no)) + "</td>";
			result += "<td><b>" + this.getMark(register.get(no)) + "</b></td>";
			result += "<td>" + this.getAttributeValue("register", "created", register.get(no)) + "</td>";
			float percentage = this.getMark(register.get(no)) / Float.valueOf(this.getTotalMarks(examId)) * 100;
			if (percentage < 0) {
				percentage = 0.0f;
			}
			result += "<td><b>" + percentage + " %</b></td>";
			result += "<td><a target=\"_blank\" href=\"" + bs.serverPath + "markSheet?examId=" + examId + "&registerId="
					+ register.get(no) + "\" class=\"btn btn-info btn-xs pull-right gap-left\">Open</a></td>";
			result += "</tr>";
		}
		return result;
	}

	public String getCombinedResults(String examId[]) {
		ArrayList<String> register = this.sortCandidateOnMark(examId);
		String result = "";
		bootstrap bs = new bootstrap();
		float percentage = 0.0f, percentageTemp = 0.0f;
		for (int no = 0, rank = 1; no < register.size(); no++, percentageTemp = percentage) {
			String examID = this.getAttributeValue("register", "examId", register.get(no));
			result += "<tr class=\"text-center\">";
			result += "<td>" + (no + 1) + "</td>";
			result += "<td><b>" + this.getAttributeValue("register", "roll", register.get(no)) + "</b></td>";
			result += "<td>" + this.getAttributeValue("register", "name", register.get(no)) + "</td>";
			String examName = this.getAttributeValue("exams", "name", examID);
			result += "<td>" + examName + "</td>";
			result += "<td>" + this.getTotalMarks(examID) + "</td>";
			result += "<td><b>" + this.getMark(register.get(no)) + "</b></td>";
			result += "<td>" + this.getAttributeValue("register", "created", register.get(no)) + "</td>";
			percentage = this.getMark(register.get(no)) / Float.valueOf(this.getTotalMarks(examID)) * 100;
			if (percentage < 0) {
				percentage = 0.0f;
			}
			result += "<td><b>" + percentage + " %</b></td>";
			if (percentageTemp > percentage) {
				rank++;
			}
			result += "<td><b>" + rank + "</b></td>";
			result += "<td><a target=\"_blank\" href=\"" + bs.serverPath + "markSheet?examId=" + examID + "&registerId="
					+ register.get(no) + "\" class=\"btn btn-info btn-xs pull-right gap-left\">Open</a></td>";
			result += "</tr>";
		}
		return result;
	}

	public int getTotalNoOfQuestions(String examId) {
		int total = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from questions where examId=" + examId);
			while (rs.next()) {
				total++;
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
		return total;
	}

	public int getNoOfQuestionsAttempted(String registerId) {
		int total = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from answersheet where registerId=" + registerId);
			while (rs.next()) {
				String answer = rs.getString("answer");
				if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4")
						|| answer.equals("5")) {
					total++;
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
		return total;
	}

	public int getNoOfRightAnswersAttempted(String registerId) {
		int total = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			Statement stmt1 = (Statement) con.createStatement();
			Statement stmt2 = (Statement) con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("select * from answersheet where registerId=" + registerId);
			while (rs1.next()) {
				String answer = rs1.getString("answer");
				ResultSet rs2 = stmt2.executeQuery("select * from questions where id=" + rs1.getString("questionId"));
				while (rs2.next()) {
					if (answer.equals(rs2.getString("answer"))) {
						total++;
					}
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
		return total;
	}

	public float getNegative(String registerId) {
		float total = 0.0f;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			Statement stmt1 = (Statement) con.createStatement();
			Statement stmt2 = (Statement) con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("select * from answersheet where registerId=" + registerId);
			while (rs1.next()) {
				String answer = rs1.getString("answer");
				ResultSet rs2 = stmt2.executeQuery("select * from questions where id=" + rs1.getString("questionId"));
				while (rs2.next()) {
					if (!answer.equals("")) {
						if (!answer.equals(rs2.getString("answer"))) {
							total += Float.valueOf(rs2.getString("mark"))
									* (Float.valueOf(rs2.getString("negative")) / 100.0);
						}
					}
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
		return total;
	}

	public float getMark(String registerId) {
		float total = 0.0f;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			Statement stmt1 = (Statement) con.createStatement();
			Statement stmt2 = (Statement) con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("select * from answersheet where registerId=" + registerId);
			while (rs1.next()) {
				String answer = rs1.getString("answer");
				ResultSet rs2 = stmt2.executeQuery("select * from questions where id=" + rs1.getString("questionId"));
				while (rs2.next()) {
					if (!answer.equals("")) {
						if (answer.equals(rs2.getString("answer"))) {
							total += Float.valueOf(rs2.getString("mark"));
						}
					}
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
		total = total - this.getNegative(registerId);
		return total;
	}

	public String getAnswerSheet(String registerId, String examId) {
		String result = "";
		int qNo = 1;
		ArrayList<String> categories = this.getCategoryForExam(examId);
		for (int i = 0; i < categories.size(); i++) {
			result += "<h4>" + this.getAttributeValue("categories", "name", categories.get(i)) + "</h4><hr/>";
			ArrayList<String> questions = this.getQuestionsForExam(examId, categories.get(i));
			for (int j = 0; j < questions.size(); j++, qNo++) {
				result += "<p>";
				result += "<b>Question NO: </b><span class=\"badge\">" + qNo + "</span>&nbsp;";
				result += this.getAttributeValue("questions", "question", questions.get(j));
				result += "<i> (Mark: <b>" + this.getAttributeValue("questions", "mark", questions.get(j)) + "</b>)";
				result += " (Negative: "
						+ Float.valueOf(this.getAttributeValue("questions", "negative", questions.get(j))) / 100 + ")";
				result += "</i></p>";
				result += "<ol>";
				result += "<li><b>Option:</b> ";
				result += this.getAttributeValue("questions", "option1", questions.get(j));
				result += "</li>";
				result += "<li><b>Option:</b> ";
				result += this.getAttributeValue("questions", "option2", questions.get(j));
				result += "</li>";
				if (!this.getAttributeValue("questions", "option3", questions.get(j)).equals("")) {
					result += "<li><b>Option:</b> ";
					result += this.getAttributeValue("questions", "option3", questions.get(j));
					result += "</li>";
				}
				if (!this.getAttributeValue("questions", "option4", questions.get(j)).equals("")) {
					result += "<li><b>Option:</b> ";
					result += this.getAttributeValue("questions", "option4", questions.get(j));
					result += "</li>";
				}
				if (!this.getAttributeValue("questions", "option5", questions.get(j)).equals("")) {
					result += "<li><b>Option:</b> ";
					result += this.getAttributeValue("questions", "option5", questions.get(j));
					result += "</li>";
				}
				String rAns = this.getAttributeValue("questions", "answer", questions.get(j));
				result += "<li><b>Right Answer: ";
				result += rAns;
				result += "</b></li>";
				result += "<li><b>Candidates Answer: ";
				String cAns = "";
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
					stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from answersheet where registerId=" + registerId
							+ " AND questionId=" + questions.get(j));
					while (rs.next()) {
						cAns = rs.getString("answer");
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
				if (cAns.equals("1") || cAns.equals("2") || cAns.equals("3") || cAns.equals("4") || cAns.equals("5")) {
					if (cAns.equals(rAns)) {
						result += cAns
								+ " <span class=\"text-info\">(Correct)</span> <span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"></span>";
					} else {
						result += cAns
								+ " <span class=\"text-warning\">(Incorrect)</span> <span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span>";
					}
				} else {
					result += "Not Attempted.";
				}
				result += "</b></li>";
				result += "</ol>";
			}
			result += "<br/><br/>";
		}
		return result;
	}

	public String getToppers(int no) {
		String result = "";
		int size = this.getAllIds("exams", true).length;
		String examIds[] = this.getAllIds("exams", true);
		result += "<table class=\"table\">";
		result += "<tr>";
		result += "<th class=\"text-center\">Exam</th>";
		result += "<th class=\"text-center\">Roll No</th>";
		result += "<th class=\"text-center\">Name</th>";
		result += "</tr>";
		for (int i = 0; i < (size < no ? size : no); i++) {
			result += "<tr class=\"text-center\">";
			String toppersId = "";
			ArrayList<String> candidates = this.sortCandidateOnMark(examIds[i]);
			if (candidates.size() > 0) {
				toppersId = candidates.get(0);
				result += "<td>";
				result += this.getAttributeValue("exams", "name", examIds[i]);
				result += "</td>";
				result += "<td>";
				result += this.getAttributeValue("register", "roll", toppersId);
				result += "</td>";
				result += "<td>";
				result += this.getAttributeValue("register", "name", toppersId);
				result += "</td>";
			} else {
				result += "<td>&nbsp;</td>";
			}
			result += "</tr>";
		}
		result += "</table>";
		return result;
	}

}
