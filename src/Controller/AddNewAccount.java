package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

//import Model.Admin;
import Model.Client;
import Model.Database;
import Model.Operation;
import Model.User;

//:(

public class AddNewAccount implements Operation {

	private int accType;

	public AddNewAccount(int accType) {
		this.accType = accType;
	}

	@Override
	public void operation(Database database, Scanner scan, User user) {

		System.out.println("Enter First Name: ");
		String firstName = scan.next();
		System.out.println("Enter Last Name: ");
		String lastName = scan.next();
		System.out.println("Enter Phone Number: ");
		String phoneNumber = scan.next();
		System.out.println("Enter Email: ");
		String email = scan.next();
		System.out.println("Enter Password: ");
		String password = scan.next();
		System.out.println("Confirm Password: ");
		String confirmPassword = scan.next();

		// check if password is confirmed
		while (!password.equals(confirmPassword)) {
			System.out.println("Password doesn't match!! ");
			System.out.println("Enter Password: ");
			password = scan.next();
			System.out.println("Confirm Password: ");
			confirmPassword = scan.next();
		}

		try {

			// Check if Email is used before
			ArrayList<String> emails = new ArrayList<>();
			ResultSet rs0 = database.getStatement().executeQuery("SELECT `Email` FROM `users`;");
			while (rs0.next()) {
				emails.add(rs0.getString("Email"));
			}
			if (emails.contains(email)) {
				System.out.println("\nThis Email is already used!\nPlease try again with a new Email.");
				return;
			}

			// increment ID in the DataBase
			ResultSet rs = database.getStatement().executeQuery("SELECT COUNT(*) FROM `users`;");
			rs.next();
			int ID = rs.getInt("COUNT(*)");

			String insert = "INSERT INTO `users`(`ID`, `FirstName`, `LastName`, `Email`, `PhoneNumber`, `Password`, `Type`) "
					+ "VALUES" + " ('" + ID + "','" + firstName + "','" + lastName + "','" + email + "','" + phoneNumber
					+ "','" + password + "','" + accType + "');";

			database.getStatement().execute(insert);
			System.out.println("\nAccount created successfully \n");

			if (accType == 0) {
				user = new Client();
				user.setID(ID);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setPhoneNumber(phoneNumber);
				user.setPassword(password);
				user.showList(database, scan);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
