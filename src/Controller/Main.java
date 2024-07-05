package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Admin;
import Model.Client;
import Model.Database;
import Model.User;

public class Main {

	public static void main(String[] args) {

		Database database = new Database();
		Scanner scan = new Scanner(System.in);

		// Login
		System.out.println("Welcome to Car Rental System \n");
		System.out.println("Enter your Email: \n[-1 to create a new account]");
		String email = scan.next();
		if (email.equals("-1")) {
			new AddNewAccount(0).operation(database, scan, null);
			return;
		}

		System.out.println("Enter your Password:");
		String password = scan.next();

		ArrayList<User> users = new ArrayList<>();

		try {
			String select = "SELECT * FROM `users`;";
			ResultSet rs = database.getStatement().executeQuery(select);

			while (rs.next()) {
				User user;
				int ID = rs.getInt("ID");
				String firstname = rs.getString("FirstName");
				String lastname = rs.getString("LastName");
				String em = rs.getString("Email");
				String phonenumber = rs.getString("PhoneNumber");
				String pass = rs.getString("Password");
				int type = rs.getInt("Type");

				// User is a CLient
				if (type == 0) {
					user = new Client();
					user.setID(ID);
					user.setFirstName(firstname);
					user.setLastName(lastname);
					user.setEmail(em);
					user.setPhoneNumber(phonenumber);
					user.setPassword(pass);
					users.add(user);
				} 
				
				//User is an Admin
				else if (type == 1) {
					user = new Admin();
					user.setID(ID);
					user.setFirstName(firstname);
					user.setLastName(lastname);
					user.setEmail(em);
					user.setPhoneNumber(phonenumber);
					user.setPassword(pass);
					users.add(user);
				} 
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		boolean loggedIn = false;
		
		for (User u : users) {
			if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
				System.out.println("\nWELCOME " + u.getFirstName().toUpperCase() + "!");
				loggedIn = true;
				u.showList(database, scan);
			}

		}

		// if flag still false
		if (!loggedIn) {
			System.out.println("\nThe email or password is incorrect, please try again.");
			scan.close();
		}

	}

}
