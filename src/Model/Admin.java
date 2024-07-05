package Model;

import java.util.Scanner;

import Controller.AddNewAccount;
import Controller.AddNewCar;
import Controller.ChangePassword;
import Controller.DeleteCar;
import Controller.EditUserData;
import Controller.Quit;
import Controller.ShowAllRents;
import Controller.ShowSpecUserRents;
import Controller.UpdateCar;
import Controller.ViewCars;

public class Admin extends User {

	public Admin() {
		super();
	}

	// array of predefined operations
	private Operation[] operations = new Operation[] { new AddNewAccount(1),
													   new AddNewCar(),
													   new ViewCars(),
													   new UpdateCar(),
													   new DeleteCar(),
													   new ShowAllRents(),
												   	   new ShowSpecUserRents(),  
													   new EditUserData(),
													   new ChangePassword(),
													   new Quit() };

	@Override
	public void showList(Database database, Scanner scan) {
		System.out.println();
		System.out.println("01. Add New Admin");
		System.out.println("02. Add New Car");
		System.out.println("03. View Cars");
		System.out.println("04. Update Car");
		System.out.println("05. Delete Car");
		System.out.println("06. Show All Rents");
		System.out.println("07. Show User's Rents");
		System.out.println("08. Edit My Data");
		System.out.println("09. Change Password");
		System.out.println("10. Quit");
		System.out.println();

		// enter the operation to execute
		int i = scan.nextInt();

		if (i < 1 || i > 10) {
			showList(database, scan);
			return;
		}

		operations[i - 1].operation(database, scan, this);

		if (i != 10) {
			showList(database, scan);
		}
	}

}
