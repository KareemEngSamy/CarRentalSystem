package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class ChangePassword implements Operation {

	@Override
	public void operation(Database database, Scanner scan, User user) {

		System.out.println("Enter Old Password");
		String oldpassword = scan.next();
		if (!oldpassword.equals(user.getPassword())) {
			System.out.println("Wrong Password!!");
			return;
		}

		System.out.println("Enter New Password");
		String newpassword = scan.next();
		System.out.println("Confirm password Password");
		String confirmpassword = scan.next();
		while (!newpassword.equals(confirmpassword)) {
			System.out.println("Password doesn't match!!\n");
			System.out.println("Enter New Password");
			newpassword = scan.next();
			System.out.println("Confirm password Password");
			confirmpassword = scan.next();
		}

		try {
			String update = "UPDATE `users` SET `Password`='" + newpassword + "' WHERE `ID` = '" + user.getID() + "' ;";
			database.getStatement().execute(update);
			System.out.println("\nPassword Updated successfully ");
			user.setPassword(newpassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
