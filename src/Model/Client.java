package Model;

import java.util.Scanner;

import Controller.ChangePassword;
import Controller.EditUserData;
import Controller.Quit;
import Controller.RentCar;
import Controller.ReturnCar;
import Controller.ShowUserRents;
import Controller.ViewCars;

public class Client extends User {

	public Client() {
		super();
	}

	// array of predefined operations
	private Operation[] operations = new Operation[] { new ViewCars(),
													   new RentCar(),
													   new ReturnCar(),
													   new ShowUserRents(5723),
													   new EditUserData(),
													   new ChangePassword(),
													   new Quit() };

	@Override
	public void showList(Database database, Scanner scan) {
		System.out.println();
		System.out.println("1. View Cars");
		System.out.println("2. Rent Car");
		System.out.println("3. Return Car");
		System.out.println("4. Show My Rents");
		System.out.println("5. Edit My Data");
		System.out.println("6. Change Password");
		System.out.println("7. Quit");
		System.out.println();

		// enter the operation to execute
		int i = scan.nextInt();

		if (i < 1 || i > 7) {
			showList(database, scan);
			return;
		}

		operations[i - 1].operation(database, scan, this);

		if (i != 7) {
			showList(database, scan);
		}
	}
}
