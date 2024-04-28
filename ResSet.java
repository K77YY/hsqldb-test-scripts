package hsql;

import java.sql.*;

public class ResSet {
	Connection con = null;
	
	public static void main(String[] args) {
		ResSet rs = new ResSet();
		ResultSet r = rs.executeSQL("SELECT * FROM CUSTOMER");
		rs.printResult(r);
	}
	
	//Verbindung zur Datenbank
	public ResSet() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		}
		catch(ClassNotFoundException e) {
			return;
		}
		
		con = null;
		
		try {
			//verbindungsinfos : Ort der DB, Benutzername (SA), Password (in diesem Fall keins)
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
	//ResultSet passt sich automatisch an die Spalten an; keine spezifikationen benötigt
	public void printResult(ResultSet r) {
		try {
			while(r.next()) {
				int  maxColumns = r.getMetaData().getColumnCount();
				String print = "";
				for(int i = 1; i < maxColumns; i++) {
					print += r.getMetaData().getColumnName(i);
					print += " = ";
					print += r.getString(i);
					print += ", ";
				}
				System.out.println(print);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}