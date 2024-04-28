package hsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	Connection con = null;
	public static void main(String[] args) {
		Main m = new Main();
		ResultSet r = m.executeSQL("SELECT * FROM Customer");
		m.printResult(r);
	}
	public Main() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		}
		catch(ClassNotFoundException e) {
			return;
		}
		con = null;
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet executeSQL(String sql) {
		try {
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			return res;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void printResult(ResultSet r) {
		try {
			while(r.next()) {
				int  maxColumns = r.getMetaData().getColumnCount();
				String print = "";
				for(int i = 1; i < maxColumns; i++) {
					print += r.getMetaData().getColumnName(i);
					print += " = ";
					print  += r.getString(i);
					print += ", ";
				}
				System.out.println(print);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void selectAll() {
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM Customer";
			ResultSet res = stmt.executeQuery(sql);
			while(res.next()) {
				String id = res.getString(1);
				String firstname = res.getString(2);
				String lastname = res.getString(3);
				System.out.println(id + ", " + firstname + " " + lastname);
			}
			res.close();
			stmt.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
