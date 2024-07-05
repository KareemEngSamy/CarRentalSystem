package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class DeleteCar implements Operation {

	@Override
	public void operation(Database database, Scanner scan, User user) {

		System.out.println("\nEnter Car ID: \n[-1 to Show all Cars]");
		int ID = scan.nextInt();

		while (ID == -1) {
			new ViewCars().operation(database, scan, user);
			System.out.println("Enter Car ID: \n[-1 to Show all Cars]");
			ID = scan.nextInt();
		}
		
		try {
			
			String delete = "UPDATE `cars` SET `available`='2' WHERE `ID`= '" + ID + "';";
			database.getStatement().execute(delete);
			System.out.println("Car Deleted Successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
