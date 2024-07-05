package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Car;
import Model.Database;
import Model.Operation;
import Model.User;

public class UpdateCar implements Operation {

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

			String select = "SELECT * FROM `cars` WHERE `ID` = '" + ID + "';";
			ResultSet rs = database.getStatement().executeQuery(select);
			rs.next();
			Car car = new Car();
			car.setID(rs.getInt("ID"));
			car.setBrand(rs.getString("Brand"));
			car.setModel(rs.getString("Model"));
			car.setColor(rs.getString("Color"));
			car.setYear(rs.getInt("Year"));
			car.setPrice(rs.getDouble("Price"));
			car.setAvailable(rs.getInt("Available"));

			if(car.isAvailable()>1) {
				System.out.println("Car doesn't exist!");
				return;
			}
			
			System.out.println("Enter Brand: \n[If " + car.getBrand() + " --> Enter -1]");
			String brand = scan.next();
			if (brand.equals("-1"))
				brand = car.getBrand();

			System.out.println("Enter Model: \n[If " + car.getModel() + " --> Enter -1]");
			String model = scan.next();
			if (model.equals("-1"))
				model = car.getModel();

			System.out.println("Enter Color: \n[If " + car.getColor() + " --> Enter -1]");
			String color = scan.next();
			if (color.equals("-1"))
				color = car.getColor();

			System.out.println("Enter Year: \n[If " + car.getYear() + " --> Enter -1]");
			int year = scan.nextInt();
			if (year == -1)
				year = car.getYear();

			System.out.println("Enter Price: \n[If " + car.getPrice() + " --> Enter -1]");
			double price = scan.nextDouble();
			if (price == -1)
				price = car.getPrice();

			String update = "UPDATE `cars` SET `Brand`='" + brand + "',`Model`='" + model + "',`Color`='" + color + "',"
					+ "`Year`='" + year + "',`Price`='" + price + "' WHERE `ID`='" + ID + "';";
			database.getStatement().execute(update);
			System.out.println("\nCar Updated successfully ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
