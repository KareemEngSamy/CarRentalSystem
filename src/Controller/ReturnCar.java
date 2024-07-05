package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

public class ReturnCar implements Operation {

	@Override
	public void operation(Database database, Scanner scan, User user) {

		System.out.println("\nEnter Rent ID: \n[-1 to Show all Rents]");
		int rentID = scan.nextInt();

		while (rentID == -1) {
			new ShowUserRents(user.getID()).operation(database, scan, user);
			;
			System.out.println("\nEnter Rent ID: \n[-1 to Show all Rents]");
			rentID = scan.nextInt();
		}

		try {

			String selectReturn = "SELECT * FROM `rents` WHERE `ID` = '" + rentID + "' ;";
			ResultSet rs = database.getStatement().executeQuery(selectReturn);
			rs.next();

			Rent r = new Rent();
			r.setID(rs.getInt("ID"));
			r.setUser(user);
			r.setDateTime(rs.getString("DateTime"));
			r.setHours(rs.getInt("Hours"));
			r.setTotal(rs.getDouble("Total"));
			r.setStatus(rs.getInt("Status"));

			if (r.getStatusToString().equals("Delayed")) {
				System.out.println("\nThere is " + r.getDelayedHours() + " Delayed Hours!");
				System.out.println("You have to pay 5000$ as a Fine.\n");
			}

			String update = "UPDATE `rents` SET `Status`='1' WHERE `ID` = '" + rentID + "';";
			database.getStatement().execute(update);

			System.out.println("Car Returned Successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
