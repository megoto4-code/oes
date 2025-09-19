package oes.data;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oes.bootstrap;

public class AdminModel extends jdbcConnection {
	public void createExam(String examName, String examTime, String examInstructions) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			String sql = "insert into exams(name,time,instructions) values ('" + examName + "','" + examTime + "','"
					+ examInstructions + "')";
			stmt.executeUpdate(sql);
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
		return;
	}

	public void updateExam(String examName, String examTime, String examInstructions, String examId, String status) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();

			String sql = "update exams set name='" + examName + "', time='" + examTime + "', instructions='"
					+ examInstructions + "', status=" + status + " where id=" + examId;
			stmt.executeUpdate(sql);
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
		return;
	}

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
				result = result + "<tr>";
				result = result + "<td>" + i++ + "</td>";
				result = result + "<td>" + rs.getString("name") + "</td>";
				result = result + "<td>" + rs.getString("time") + "</td>";
				String status = (rs.getString("status").equals("1") ? "<b>Active</b>" : "Not Active");
				result = result + "<td>" + status + "</td>";
				result = result + "<td>" + strLimiter(rs.getString("instructions"), 60, true) + "</td>";
				result = result + "<td><a href=\"" + b.serverPath + "listOfQuestions?examId=" + rs.getString("id")
						+ "\" class=\"btn btn-info btn-xs pull-right gap-left\" target=\"_blank\">View Questions</a>";
				result = result + "<a href=\"" + b.serverPath + "updateExam?examId=" + rs.getString("id")
						+ "\" class=\"btn btn-default btn-xs pull-right\">Update</a></td>";
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

	public void addQuestion(String examId, String question, String option1, String option2, String option3,
			String option4, String option5, String answer, String mark, String negative, String category) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();

			PreparedStatement ps;
			String sql = "insert into questions(question ,option1 ,option2 ,option3 ,option4 ,option5 ,answer, mark, negative, examId, category) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, question);
			ps.setString(2, option1);
			ps.setString(3, option2);
			ps.setString(4, option3);
			ps.setString(5, option4);
			ps.setString(6, option5);
			ps.setString(7, answer);
			ps.setString(8, mark);
			ps.setString(9, negative);
			ps.setString(10, examId);
			ps.setString(11, category);
			ps.executeUpdate();
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
		return;
	}

	public void updateQuestion(String examId, String questionId, String question, String option1, String option2,
			String option3, String option4, String option5, String answer, String mark, String negative,
			String category) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();

			PreparedStatement ps;
			ps = con.prepareStatement(
					"update questions set question=?, option1=?, option2=?, option3=?, option4=?, option5=?, answer=?, mark=?, negative=?, category=? where examId=? and id=?");
			ps.setString(1, question);
			ps.setString(2, option1);
			ps.setString(3, option2);
			ps.setString(4, option3);
			ps.setString(5, option4);
			ps.setString(6, option5);
			ps.setString(7, answer);
			ps.setString(8, mark);
			ps.setString(9, negative);
			ps.setString(10, category);
			ps.setString(11, examId);
			ps.setString(12, questionId);
			ps.executeUpdate();

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
		return;
	}

	public String getListOfQuestions(String examId, String category) {
		String result = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs;
			if (category.equals("all"))
				rs = stmt.executeQuery("select * from questions where examId=" + examId);
			else
				rs = stmt.executeQuery("select * from questions where examId=" + examId + " AND category=" + category);
			// order by rand() limit 5
			int i = 1;
			bootstrap bs = new bootstrap();
			while (rs.next()) {
				String btnDelete = "<a class=\"btn btn-warning btn-xs pull-right gap-left\" href=\"#\" data-toggle=\"modal\" data-target=\".bs-example-modal-sm"
						+ i + "\">Delete</a>";
				String btnUpdate = "<a class=\"btn btn-primary btn-xs pull-right gap-left\" href=\"" + bs.serverPath
						+ "updateQuestions?examId=" + rs.getString("examId") + "&questionId=" + rs.getString("id")
						+ "&questionNo=" + i + "\">Update</a>";
				String answer = rs.getString("answer");
				result = result + "<div class=\"panel panel-default\">";
				result = result + "<div class=\"panel-heading\">Question No: <span class=\"label label-success\">" + i++
						+ "</span> Category: <b>"
						+ this.getAttributeValue("categories", "name", rs.getString("category")) + "</b> " + btnDelete
						+ btnUpdate + "<span class=\"pull-right\">Mark:<span class=\"badge\">" + rs.getString("mark")
						+ "</span> Negative: <span class=\"badge\">" + bs.getNegative(rs.getString("negative"))
						+ "</span></span></div>";
				result = result + "<div class=\"panel-body\"><b>" + rs.getString("question") + "</b></div>";
				result = result + "<ul class=\"list-group\">";
				result = result + "<li class=\"list-group-item" + (answer.equals("1") ? " active" : "")
						+ "\"><b>Option A:</b> " + rs.getString("option1") + "</li>";
				result = result + "<li class=\"list-group-item" + (answer.equals("2") ? " active" : "")
						+ "\"><b>Option B:</b> " + rs.getString("option2") + "</li>";
				if (rs.getString("option3") != "")
					result = result + "<li class=\"list-group-item" + (answer.equals("3") ? " active" : "")
							+ "\"><b>Option C:</b> " + rs.getString("option3") + "</li>";
				if (rs.getString("option4") != "")
					result = result + "<li class=\"list-group-item" + (answer.equals("4") ? " active" : "")
							+ "\"><b>Option D:</b> " + rs.getString("option4") + "</li>";
				if (rs.getString("option5") != "")
					result = result + "<li class=\"list-group-item" + (answer.equals("5") ? " active" : "")
							+ "\"><b>Option E:</b> " + rs.getString("option5") + "</li>";
				result = result + "</ul>";
				result = result + "</div>";

				result = result + "<div class=\"modal fade bs-example-modal-sm" + (i - 1)
						+ "\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"mySmallModalLabel\"> <div class=\"modal-dialog modal-sm\"> <div class=\"modal-content\"><div class=\"modal-header\"> <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span> </button> <h4 class=\"modal-title\"><span class=\"glyphicon glyphicon-trash\"></span> Delete question no:"
						+ (i - 1)
						+ "?</h4> </div> <div class=\"modal-footer\"> <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">No</button> <a class=\"btn btn-warning\" href=\""
						+ bs.serverPath + "deleteQuestion?examId=" + rs.getString("examId") + "&questionId="
						+ rs.getString("id") + "\">Yes</a> </div> </div> </div></div>";

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

	public int getTotalNoOfQuestions(String examId) {
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from questions where examId=" + examId);
			while (rs.next()) {
				result++;
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

	public int getTotalMarks(String examId) {
		int total = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from questions where examId=" + examId);
			while (rs.next()) {
				total = total + Integer.parseInt(rs.getString("mark"));
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
		return total;
	}

	public String getCategories() {
		String result = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from categories");
			result += "<ul class=\"list-group\">";
			bootstrap bs = new bootstrap();
			int i = 1;
			while (rs.next()) {
				result += "<li class=\"list-group-item\"><span class=\"badge pull-left\">" + (i++)
						+ "</span>&nbsp;&nbsp;" + rs.getString("name");
				result += "<a class=\"pull-right gap-left\" href=\"" + bs.serverPath + "listOfExams?updateCategoryId="
						+ rs.getString("id") + "\">Update</a>";
				result += "<a class=\"pull-right text-danger\" href=\"" + bs.serverPath + "deleteCategory?categoryId="
						+ rs.getString("id") + "\">Delete</a>";
				result += "</li>";
			}
			result += "</ul>";
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

	public void addCategory(String name) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			String sql = "insert into categories (name) values ('" + name + "')";
			stmt.executeUpdate(sql);
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
		return;
	}

	public void updateCategory(String name, String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			String sql = "update categories set name='" + name + "' where id=" + id;
			stmt.executeUpdate(sql);
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

	public String getCategoryOptions() {
		String result = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from categories");
			while (rs.next()) {
				result += "<option value=\"" + rs.getString("id") + "\">" + rs.getString("name");
				result += "</option>";
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

	public String getCategoryOptions(String selected) {
		String result = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from categories");
			while (rs.next()) {
				if (rs.getString("id").equals(selected))
					result += "<option value=\"" + rs.getString("id") + "\" selected=\"selected\">"
							+ rs.getString("name");
				else
					result += "<option value=\"" + rs.getString("id") + "\">" + rs.getString("name");
				result += "</option>";
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

	public ArrayList<String> getCategoryForExam(String examId) {
		ArrayList<String> categories = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select * from questions where examId=" + examId + " order by category desc");
			while (rs.next()) {
				if (categories.contains(rs.getString("category")) == false) {
					categories.add(rs.getString("category"));
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
		return categories;
	}

	public int getNoOfCategorisedQuestions(String examId, String categoryId) {
		int no = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select * from questions where examId=" + examId + " AND category=" + categoryId);
			while (rs.next()) {
				no++;
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
		return no;
	}

}
