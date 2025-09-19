package oes.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcConnection {

	String url;
	String db;
	String username;
	String password;
	Connection con;
	Statement stmt;

	public jdbcConnection() {
		url = "jdbc:mysql://localhost:3306/";
		db = "oesDB";
		username = "root";
		password = "password";
	}

	public String getAttributeValue(String table, String col, String id) {
		String result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select " + col + " from " + table + " where id=" + id);
			while (rs.next())
				result = rs.getString(col);
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

	public boolean setAttributeValue(String table, String col, String id, String value) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			String sql = "update " + table + " set " + col + "='" + value + "' where id=" + id;
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
		return result;
	}

	public String getAttributeValue(String table, String column, String value, String rColumn) {
		String result = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select " + rColumn + " from " + table + " where " + column + "='" + value + "'");
			while (rs.next())
				result = rs.getString(rColumn);
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

	public void removeRecord(String table, String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			String sql = "delete from " + table + " where id='" + id + "'";
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

	}

	public void removeRecord(String table, String idName, String idValue) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			String sql = "delete from " + table + " where " + idName + "='" + idValue + "'";
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

	}

	public boolean isDependentOn(String table, String column, String value) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from " + table + " where " + column + "='" + value + "'");
			while (rs.next()) {
				result = true;
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

	public String strLimiter(String str, int length, boolean showPopover) {
		if (str.length() > length) {
			str = str.substring(0, length) + "...<span class=\"label label-default\"";
			if (showPopover == true)
				str = str + "";
			str = str + ">More</span>";
		}
		return str;
	}

	public int getNoOfRows(String table) {
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from " + table);
			while (rs.next()) {
				result++;
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

	public String[] getAllIds(String table, boolean reversed) {
		int size = this.getNoOfRows(table);
		String exams[] = new String[size];
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs;
			if (reversed == false) {
				rs = stmt.executeQuery("select * from " + table);
			} else {
				rs = stmt.executeQuery("select * from " + table + " order by id desc");
			}
			int i = 0;
			while (rs.next()) {
				exams[i] = rs.getString("id");
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
		return exams;
	}

}
