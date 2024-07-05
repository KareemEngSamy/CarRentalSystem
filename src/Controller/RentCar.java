package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Car;
import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

public class RentCar implements Operation {

	@Override
	public void operation(Database database, Scanner scan, User user) {

		System.out.println("\nEnter Car ID: \n[-1 to Show all Cars]");
		int carID = scan.nextInt();

		while (carID == -1) {
			new ViewCars().operation(database, scan, user);
			System.out.println("\nEnter Car ID: \n[-1 to Show all Cars]");
			carID = scan.nextInt();
		}

		System.out.println("Enter Hours: ");
		int hours = scan.nextInt();

		try {

			String select = "SELECT * FROM `cars` WHERE `ID` = '" + carID + "';";
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

			// Car is available only when is == 0
			if (car.isAvailable() != 0) {
				System.out.println("Car isn't Available");
				return;
			}

			// increment ID in the DataBase
			ResultSet rs1 = database.getStatement().executeQuery("SELECT COUNT(*) FROM `rents`;");
			rs1.next();
			int ID = rs1.getInt("COUNT(*)");

			double total = car.getPrice() * hours;

			Rent rent = new Rent();

			String insert = "INSERT INTO `rents`(`ID`, `User`, `Car`, `DateTime`, `Hours`, `Total`, `Status`) "
					+ "VALUES ('" + ID + "','" + user.getID() + "','" + car.getID() + "','" + rent.getDateTime() + "','"
					+ hours + "','" + total + "','0')";
			
			database.getStatement().execute(insert);
			System.out.println("\nRent ID: "+ ID + "\nTotal: " + total + "$" +"\n\nCar Rented successfully.\nENJOY YOUR RIDE! \n");
						
			
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
