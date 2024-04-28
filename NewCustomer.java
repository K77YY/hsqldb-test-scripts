package hsql;

import java.sql.*;

public class NewCustomer {
	Connection con = null;
	
	public static void main(String[] args) {
		NewCustomer nc = new NewCustomer();
		//Festlegen der Daten für den neuen Kunde
		nc.insertCustomer("Samson", "Rentzsch", "Pestalozzistr. 2", "Konstanz");
		ResultSet r = nc.executeSQL("SELECT * FROM CUSTOMER");
		nc.printResult(r);
		try {
			nc.con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Verbindung zur Datenbank
	public NewCustomer() {
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
	
	public void insertCustomer(String firstname, String lastname, String street, String city) {
		ResultSet r = executeSQL("SELECT * FROM CUSTOMER");
		int cid = 0;
		try {
			//Rechnung für die ID
			while(r.next()) {
				if(r.getInt(1) > cid) {
					cid = r.getInt(1);
				}
			}
			cid++;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			r.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		PreparedStatement prep = null;
		try {
			String sql = "INSERT INTO CUSTOMER VALUES ( ?, ?, ?, ?, ? )";
			prep = con.prepareStatement(sql);
			prep.setInt(1, cid);
			prep.setString(2, firstname);
			prep.setString(3, lastname);
			prep.setString(4, street);
			prep.setString(5, city);
			prep.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				prep.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
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
	
	//ResultSet 
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