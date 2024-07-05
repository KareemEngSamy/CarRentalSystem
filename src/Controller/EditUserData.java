package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class EditUserData implements Operation {

	@Override
	public void operation(Database database, Scanner scan, User user) {

		System.out.println("Enter First Name: \n[If " + user.getFirstName() + " --> Enter -1]");
		String firstname = scan.next();
		if (firstname.equals("-1"))
			firstname = user.getFirstName();

		System.out.println("Enter Last Name: \n[If " + user.getLastName() + " --> Enter -1]");
		String lastname = scan.next();
		if (lastname.equals("-1"))
			lastname = user.getLastName();

		System.out.println("Enter Phone Number: \n[If " + user.getPhoneNumber() + " --> Enter -1]");
		String phonenumber = scan.next();
		if (phonenumber.equals("-1"))
			phonenumber = user.getPhoneNumber();

		System.out.println("Enter Email: \n[If " + user.getEmail() + " --> Enter -1]");
		String email = scan.next();
		if (email.equals("-1")) {
			email = user.getEmail();
		}

		
		String update = "UPDATE `users` SET `FirstName`='" + firstname + "',`LastName`='" + lastname + "',"
				+ "`Email`='" + email + "',`PhoneNumber`='" + phonenumber + "' WHERE `ID` = '" + user.getID() + "';";

		try {
			database.getStatement().execute(update);
			System.out.println("\nData Updated successfully ");
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setPhoneNumber(phonenumber);
			user.setEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
