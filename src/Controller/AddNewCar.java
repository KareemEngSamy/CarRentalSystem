package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class AddNewCar implements Operation {

	@Override
	public void operation(Database database, Scanner scan, User user) {

		System.out.println("Enter Brand: ");
		String brand = scan.next();
		System.out.println("Enter Model: ");
		String model = scan.next();
		System.out.println("Enter Color: ");
		String color = scan.next();

		System.out.println("Enter Year: ");
		int year = scan.nextInt();
		System.out.println("Enter Price per hour: ");
		Double price = scan.nextDouble();
		int available = 0;

		try {
			// increment ID in the DataBase
			String select = "SELECT COUNT(*) FROM `cars`;";
			ResultSet rs = database.getStatement().executeQuery(select);
			rs.next();
			int ID = rs.getInt("COUNT(*)");

			String insert = "INSERT INTO `cars`(`ID`, `Brand`, `Model`, `Color`, `Year`, `Price`, `available`) "
					+ "VALUES ('" + ID + "','" + brand + "','" + model + "','" + color + "','" + year + "','" + price
					+ "','" + available + "');";
			
			database.getStatement().execute(insert);
			System.out.println("\nCar Added successfully \n");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
