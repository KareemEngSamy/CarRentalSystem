package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class ShowSpecUserRents implements Operation{

	private void printUsers(Database database) {
		try {
			String select = "SELECT * FROM `users`;";
			ResultSet rs = database.getStatement().executeQuery(select);
			while (rs.next()) {
				int accType = rs.getInt("Type");
				if(accType==0) {
					String name = rs.getString("FirstName") + " " + rs.getString("LastName");
					System.out.println("User ID: " + rs.getInt("ID"));
					System.out.println("Name: " + name);
					System.out.println("Email: " + rs.getString("Email"));
					System.out.println("Phone Number: " + rs.getString("PhoneNumber"));
					System.out.println("-------------------");
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	
	@Override
	public void operation(Database database, Scanner scan, User user) {
		
		System.out.println("\nEnter User ID: \n[-1 to Show all Users]");
		int userID = scan.nextInt();
		
		while (userID == -1) {
			printUsers(database);
			System.out.println("\nEnter User ID: \n[-1 to Show all Users]");
			userID = scan.nextInt();
		}
		
		new ShowUserRents(userID).operation(database, scan, user);
		
	}
	
	

}
