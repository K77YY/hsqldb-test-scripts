package hsql;

import java.sql.*;

public class ConnectDatabase {
	//Erstellen eine Connection Objekts
	Connection connection = null;
	
	public static void main(String[] args) {
		ConnectDatabase cd = new ConnectDatabase();
		cd.selectAll();
	}
	
	//Verbindung zur Datenbank
	public ConnectDatabase() {
		try {
			//laden des JDBC drivers
			Class.forName("org.hsqldb.jdbcDriver");
		}
		catch(ClassNotFoundException e) {
			return;
		}		
		try {
			//Verbindung zu der Datenbank 'testdb'
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//SQL statement wird ausgeführt ("SELECT * FROM Customer" --> id, Vorname, Nachname)
	public void selectAll() {
		try {
			Statement stmt = connection.createStatement();
			//Normaler SQL Befehl als String 
			String sql = "SELECT * FROM Customer";
			ResultSet res = stmt.executeQuery(sql);
			while(res.next()) {
				//ID ist die erste Spalte der Tabelle, FIRSTNAME die zweite Spalte (nicht Spalte 0) usw.
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
