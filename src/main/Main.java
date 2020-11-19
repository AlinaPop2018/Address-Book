package main;

import view.Menu;

public class Main {

	public static void main(String[] args) {
		Menu menu = new Menu();
		
		try {
			menu.createMenu();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : " + e.getMessage());
		}

	}

}
