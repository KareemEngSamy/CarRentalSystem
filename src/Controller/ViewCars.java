package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Car;
import Model.Database;
import Model.Operation;
import Model.User;

public class ViewCars implements Operation{

	@Override
	public void operation(Database database, Scanner scan, User user) {
		
		System.out.println();
		String select = "SELECT * FROM `cars`;";
		ArrayList<Car> cars = new ArrayList<>();
		
		try {
			
			ResultSet rs = database.getStatement().executeQuery(select);
			while(rs.next()) {
				Car car = new Car();
				car.setID(rs.getInt("ID"));
				car.setBrand(rs.getString("Brand"));
				car.setModel(rs.getString("Model"));
				car.setColor(rs.getString("Color"));
				car.setYear(rs.getInt("Year"));
				car.setPrice(rs.getDouble("Price"));
				car.setAvailable(rs.getInt("Available"));
				cars.add(car);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(Car c : cars) {
			if(c.isAvailable() < 2) { 
				System.out.println("ID: " + c.getID());
				System.out.println("Brand: " + c.getBrand());
				System.out.println("Model: " + c.getModel());
				System.out.println("Color: " + c.getColor());
				System.out.println("Year: " + c.getYear());
				System.out.println("Price: " + c.getPrice() + "$");
				if(c.isAvailable()==0) {
					System.out.println("Status: Available");
				}
				else{
					System.out.println("Status: Not Available");
				}
				System.out.println("-------------------");
			}
		}
		
	}

}
