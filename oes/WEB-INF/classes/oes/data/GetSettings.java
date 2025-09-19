package oes.data;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSettings extends jdbcConnection {

	public String id;
	public String property;
	public String value;
	public String updated;
	
	public GetSettings(String property) {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(this.url + this.db, this.username, this.password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from settings where property='" + property+"'");
			while (rs.next()) {
				this.id = rs.getString("id");
				this.property = rs.getString("property");
				this.value = rs.getString("value");
				this.updated = rs.getString("updated");
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
	}
		

}
