package Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Car;
import Model.Client;
import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

public class ShowAllRents implements Operation {

	@Override
	public void operation(Database database, Scanner scan, User user) {

		try {

			ArrayList<Rent> rents = new ArrayList<>();
			ArrayList<Integer> CarIDs = new ArrayList<>();
			ArrayList<Integer> userIDs = new ArrayList<>();

			String select = "SELECT * FROM `rents`;";
			ResultSet rs = database.getStatement().executeQuery(select);

			while (rs.next()) {
				Rent rent = new Rent();
				rent.setID(rs.getInt("ID"));
				CarIDs.add(rs.getInt("Car"));
				userIDs.add(rs.getInt("User"));
				rent.setDateTime(rs.getString("DateTime"));
				rent.setHours(rs.getInt("Hours"));
				rent.setTotal(rs.getDouble("Total"));
				rent.setStatus(rs.getInt("Status"));
				rents.add(rent);
			}

			for (int i = 0; i < CarIDs.size(); i++) {
				Rent r = rents.get(i);
				
				String selectUser = "SELECT * FROM `users` WHERE `ID` = '" + userIDs.get(i) + "' ;";
				ResultSet rs1 = database.getStatement().executeQuery(selectUser);
				rs1.next();

				User u = new Client();
				u.setID(rs1.getInt("ID"));
				u.setFirstName(rs1.getString("FirstName"));
				u.setLastName(rs1.getString("LastName"));
				u.setEmail(rs1.getString("Email"));
				u.setPhoneNumber(rs1.getString("PhoneNumber"));
				u.setPassword(rs1.getString("Password"));
				r.setUser(u);

				String selectCars = "SELECT * FROM `cars` WHERE `ID` = '" + CarIDs.get(i) + "' ;";
				ResultSet rs2 = database.getStatement().executeQuery(selectCars);
				rs2.next();

				Car car = new Car();
				car.setID(rs2.getInt("ID"));
				car.setBrand(rs2.getString("Brand"));
				car.setModel(rs2.getString("Model"));
				car.setColor(rs2.getString("Color"));
				car.setYear(rs2.getInt("Year"));
				car.setPrice(rs2.getDouble("Price"));
				car.setAvailable(rs2.getInt("Available"));
				r.setCar(car);

				String name = r.getUser().getFirstName() + " " + r.getUser().getLastName();
				String email = r.getUser().getEmail();
				String phonenumber = r.getUser().getPhoneNumber();
				String carInfo = r.getCar().getBrand() + " " + r.getCar().getModel() + " " + r.getCar().getYear() + " "
						+ r.getCar().getColor();

				System.out.println("\nRent ID: " + r.getID());
				System.out.println("Name: " + name);
				System.out.println("Email: " + email);
				System.out.println("Phone Number: " + phonenumber);
				System.out.println("Car: " + carInfo);
				System.out.println("Car ID: " + r.getCar().getID());
				System.out.println("Date and Time: " + r.getDateTime());
				System.out.println("Hours: " + r.getHours());
				System.out.println("Total: " + r.getTotal());

				System.out.println("Status: " + r.getStatusToString());
				System.out.println("-------------------");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
