package view;

import java.io.File;
import java.util.Scanner;

import controler.TxtCrud;

public class Menu {

	public void createMenu() throws Exception {
		Scanner strInput = new Scanner(System.in);
		String choice, cont = "y";
		TxtCrud txtCrud = new TxtCrud();
		File file1 = new File("address_book.txt");
		file1.createNewFile();
		File file2 = new File("relation.txt");
		file2.createNewFile();
		
		try {
			while (cont.equalsIgnoreCase("y")) {
				System.out.println("\t\t  Address book\n");

				System.out.println("1 ===> View All  Record ");
				System.out.println("2 ===> Add New Contact ");
				System.out.println("3 ===> Add New Relation between two contacts");	
				System.out.println("4 ===> Update Specific Record ");
				System.out.println("5 ===> Search Specific Record ");
				System.out.println("6 ===> Delete  Record ");

				System.out.print("\n");
				System.out.println("Enter your choice: ");
				choice = strInput.nextLine();
				
				if (choice.equals("1")) {
					txtCrud.ViewAllContacts();					
				} else if (choice.equals("2")) {
					txtCrud.AddContact(strInput);
				} else if (choice.equals("3")) {
					txtCrud.AddRelation(strInput);
				} else if (choice.equals("4")) {
					txtCrud.UpdateContact(strInput);					
				} else if (choice.equals("5")) {
					txtCrud.SearchContact(strInput);
				} else if (choice.equals("6")) {
					txtCrud.DeleteContactAndRelation(strInput);
				}
				
				System.out.println("Do you want to continue? Y/N");
				cont = strInput.nextLine();
			}
		
		} catch (Exception e) {
			System.out.println("ERROR ! " + e.getMessage());
			System.out.println();
			e.printStackTrace();
		} finally {
			strInput.close();
		}		
	}

	
}
