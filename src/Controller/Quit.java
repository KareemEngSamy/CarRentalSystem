package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class Quit implements Operation{

	@Override
	public void operation(Database database, Scanner scan, User user) {
		
		System.out.println("\n♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
		System.out.println("Thanks For Visiting Us. ");
		System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
		scan.close();
		
		
	}

	
	
}
