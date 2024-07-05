package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//:(

public class Database {
 
	private String user = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/carrentalsystem";
	private Statement statement;
	
	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}
		catch(SQLException e) {
			e.printStackTrace();
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Statement getStatement() {
		return statement;	
	}
		
}
