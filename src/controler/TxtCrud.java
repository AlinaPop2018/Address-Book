package controler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import view.CommandLineTable;
import model.Contact;
import model.RelationContact1WithContact2;

public class TxtCrud {
		
	public void ViewAllContacts() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("address_book.txt"));		
		CommandLineTable tb = new CommandLineTable();
		tb.setShowVerticalLines(true);
        tb.setHeaders("ID", "Name", "Surname","Telephone number", "Email", "Age", "Hair color", "Relation With", "Relation Type", "Relation Details");
        String record;
        List<Contact> addressBook = new ArrayList<>();
        while ((record = br.readLine()) != null) {      	
			StringTokenizer sto = new StringTokenizer(record, ",");

			Contact c1 = new Contact();
			c1.setId(Integer.parseInt(sto.nextToken()));			
			c1.setName(sto.nextToken());
			c1.setSurname(sto.nextToken());
			c1.setTelephone(sto.nextToken());
			c1.setEmail(sto.nextToken());
			c1.setAge(Integer.parseInt(sto.nextToken()));
			c1.setHairColor(sto.nextToken());
			c1.setIdRelation(Integer.parseInt(sto.nextToken()));
			
			addressBook.add(c1);	
		}
		if (!addressBook.isEmpty()) {
			Collections.sort(addressBook);
			for (Contact c1 : addressBook) {
				String relationWith = " -- ";
				String relationType = " -- ";
				String relationDetails = " -- ";
				int relationTypeId = 0;
				if (c1.getIdRelation() != 0) {
					RelationContact1WithContact2 relation = searchTheRelationForContact(c1.getId());
					Contact c2 = searchContact2Details(relation.getContact2().getId());
					relationWith = c2.getSurname();
					relationTypeId = Integer.parseInt(relation.getRelationType());
					switch (relationTypeId) {
					case 1:
						relationType = "Friends";
						break;
					case 2:
						relationType = "Family";
						break;
					case 3:
						relationType = "Acquaintance";
						break;
					default:
						break;
					}
					relationDetails = relation.getDetail();
				}				
				tb.addRow(String.valueOf(c1.getId()), c1.getName(), c1.getSurname(), c1.getTelephone(), c1.getEmail(), String.valueOf(c1.getAge()), c1.getHairColor(), relationWith, relationType, relationDetails);
			}
		}		
		tb.print();
		br.close();
	}
	
	public void ViewSpecificContacts(String specificColumn) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("address_book.txt"));		
		CommandLineTable tb = new CommandLineTable();
		tb.setShowVerticalLines(true);
        tb.setHeaders("ID", "Name", "Surname","Telephone number", "Email", "Age", "Hair color", "Relation With", "Relation Type", "Relation Details");
        String record;
        List<Contact> addressBook = new ArrayList<>();
        while ((record = br.readLine()) != null) {       	
			StringTokenizer sto = new StringTokenizer(record, ",");

			Contact c1 = new Contact();
			c1.setId(Integer.parseInt(sto.nextToken()));			
			c1.setName(sto.nextToken());
			c1.setSurname(sto.nextToken());
			c1.setTelephone(sto.nextToken());
			c1.setEmail(sto.nextToken());
			c1.setAge(Integer.parseInt(sto.nextToken()));
			c1.setHairColor(sto.nextToken());
			c1.setIdRelation(Integer.parseInt(sto.nextToken()));
			
			if (record.contains(specificColumn) ) {
				addressBook.add(c1);
			}	
		}
		if (!addressBook.isEmpty()) {
			Collections.sort(addressBook);
			for (Contact c1 : addressBook) {
				String relationWith = " -- ";
				String relationType = " -- ";
				String relationDetails = " -- ";
				int relationTypeId = 0;
				if (c1.getIdRelation() != 0) {
					RelationContact1WithContact2 relation = searchTheRelationForContact(c1.getId());
					Contact c2 = searchContact2Details(relation.getContact2().getId());
					relationWith = c2.getSurname();
					relationTypeId = Integer.parseInt(relation.getRelationType());
					switch (relationTypeId) {
					case 1:
						relationType = "Friends";
						break;
					case 2:
						relationType = "Family";
						break;
					case 3:
						relationType = "Acquaintance";
						break;
					default:
						break;
					}
					relationDetails = relation.getDetail();
				}				
				tb.addRow(String.valueOf(c1.getId()), c1.getName(), c1.getSurname(), c1.getTelephone(), c1.getEmail(), String.valueOf(c1.getAge()), c1.getHairColor(), relationWith, relationType, relationDetails);
			}
		}		
		tb.print();
		br.close();
	}

	private Contact searchContact2Details(int idContact2) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("address_book.txt"));
        String record;
		Contact c2 = new Contact();
		
		while ((record = br.readLine()) != null) {

			StringTokenizer sto = new StringTokenizer(record, ",");
			if (record.contains(String.valueOf(idContact2))) {
				c2.setId(Integer.parseInt(sto.nextToken()));			
				c2.setName(sto.nextToken());
				c2.setSurname(sto.nextToken());
				c2.setTelephone(sto.nextToken());
				c2.setEmail(sto.nextToken());
				c2.setAge(Integer.parseInt(sto.nextToken()));
				c2.setHairColor(sto.nextToken());
				c2.setIdRelation(Integer.parseInt(sto.nextToken()));
			}
		}
		br.close();
		return c2;
	}

	private RelationContact1WithContact2 searchTheRelationForContact(int idContact1) throws IOException {
		String record;

		BufferedReader br = new BufferedReader(new FileReader("relation.txt"));
		RelationContact1WithContact2 relation = new RelationContact1WithContact2();
		
		while ((record = br.readLine()) != null) {

			StringTokenizer sto = new StringTokenizer(record, ",");
			if (record.contains(String.valueOf(idContact1))) {
				relation.setId(Integer.parseInt(sto.nextToken()));
				Contact c1 = new Contact();
				c1.setId(Integer.parseInt(sto.nextToken()));
				relation.setContact1(c1);
				Contact c2 = new Contact();
				c2.setId(Integer.parseInt(sto.nextToken()));
				relation.setContact2(c2);
				relation.setRelationType(sto.nextToken());
				relation.setDetail(sto.nextToken());
			}
		}
		
		br.close();
		return relation;
	}

	public void AddContact(Scanner strInput) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter("address_book.txt", true));

		String ID, name, surname, tel, email, age, hairColor, idRelation;

		System.out.print("Enter the ID: ");
		ID = strInput.nextLine();
		while (ID.isEmpty()) {
			System.out.print("Mandatory field. Enter the  ID: ");
			ID = strInput.nextLine();
		}
		
		System.out.print("Enter the  Name: ");
		name = strInput.nextLine();
		while (name.isEmpty()) {
			System.out.print("Mandatory field. Enter the  Name: ");
			name = strInput.nextLine();
		}
		
		System.out.print("Enter the  Surname: ");
		surname = strInput.nextLine();
		while (surname.isEmpty()) {
			System.out.print("Mandatory field. Enter the  Surname: ");
			surname = strInput.nextLine();
		}
		
		System.out.print("Enter the Telephone number: ");
		tel = strInput.nextLine();
		while (tel.isEmpty()) {
			System.out.print("Mandatory field. Enter the  Telephone number: ");
			tel = strInput.nextLine();
		}
		
		System.out.print("Enter the  Email: ");
		email = strInput.nextLine();
		while (email.isEmpty()) {
			System.out.print("Mandatory field. Enter the Email: ");
			email = strInput.nextLine();
		}
		
		System.out.print("Enter the  Age: ");
		age = strInput.nextLine();
		if (age.isEmpty()) {
			age = "0";
		}
		
		System.out.print("Enter the  Hair Color: ");
		hairColor = strInput.nextLine();
		if (hairColor.isEmpty()) {
			hairColor = "-";
		}
		
		idRelation = "0";
		
		bw.write(ID + "," + name + "," + surname + "," + tel + "," + email+ "," + age + "," + hairColor+ "," + idRelation);
		bw.flush();
		bw.newLine();
		bw.close();
	}
	
	public void AddRelation(Scanner strInput) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("relation.txt", true));
		
		String idRelation, IDcontact1, IDcontact2, relationType, detail;

		System.out.print("Enter the ID of Relation: ");
		idRelation = strInput.nextLine();
		while (idRelation.isEmpty()) {
			System.out.print("Mandatory field. Enter the  ID: ");
			idRelation = strInput.nextLine();
		}
		
		System.out.print("Enter the ID of Contact 1: ");
		IDcontact1 = strInput.nextLine();
		while (IDcontact1.isEmpty()) {
			System.out.print("Mandatory field. Enter the  ID of Contact 1: ");
			IDcontact1 = strInput.nextLine();
		}
		
		System.out.print("Enter the ID of Contact 2 (that is in relation with Contact 1) : ");
		IDcontact2 = strInput.nextLine();
		while (IDcontact2.isEmpty()) {
			System.out.print("Mandatory field. Enter the  ID of Contact 2: ");
			IDcontact2 = strInput.nextLine();
		}
		
		System.out.println("Choose the relation Type: ");
		System.out.println("1 ===> Friends ");
		System.out.println("2 ===> Family ");
		System.out.println("3 ===> Acquaintance");
		relationType = strInput.nextLine();
		detail = "--";
		switch (relationType) {
		case "1":
			System.out.print("Enter the number of years of frienship : ");
			detail = strInput.nextLine();
			break;
		case "2":
			System.out.print("Enter the type of family relation (parent, granparent, son/daughter, aunt/uncle) : ");
			detail = strInput.nextLine();
			break;
		case "3":
			System.out.print("Enter Acquaintance detail : ");
			detail = strInput.nextLine();
			break;
		default:
			break;
		}		
		
		bw.write(idRelation + "," + IDcontact1 + "," + IDcontact2 + "," + relationType + "," + detail);
		bw.flush();
		bw.newLine();
		bw.close();
		
		updateContact1withRelation(IDcontact1, idRelation);
		
	}

	private void updateContact1withRelation(String IDcontact1, String idRelation) throws IOException {
		List<String> newLines = new ArrayList<>();
		for (String line : Files.readAllLines(Paths.get("address_book.txt"), StandardCharsets.UTF_8)) {
			StringTokenizer sto = new StringTokenizer(line, ",");
	    	Contact c1 = new Contact();
	    	c1.setId(Integer.parseInt(sto.nextToken()));			
			c1.setName(sto.nextToken());
			c1.setSurname(sto.nextToken());
			c1.setTelephone(sto.nextToken());
			c1.setEmail(sto.nextToken());
			c1.setAge(Integer.parseInt(sto.nextToken()));
			c1.setHairColor(sto.nextToken());
			c1.setIdRelation(Integer.parseInt(sto.nextToken()));
			if (c1.getId() == Integer.parseInt(IDcontact1)) {		    	
		       newLines.add(line.replace(String.valueOf(c1.getIdRelation()), ""+idRelation));
		    } else {
		       newLines.add(line);
		    }
		}
		Files.write(Paths.get("address_book.txt"), newLines, StandardCharsets.UTF_8);
		
	}
	
	public void UpdateContact(Scanner strInput) throws IOException {
		String newID, newName, newSurname, newTel, newEmail, newAge, newHairColor;
		String oldID, oldName, oldSurname, oldTel, oldEmail, oldAge, oldHairColor;
		
		System.out.println("Choose the column that you want to update in the Contact table: ");
		System.out.println("1 ===> ID ");
		System.out.println("2 ===> Name ");
		System.out.println("3 ===> Surname");
		System.out.println("4 ===> Telephone number ");
		System.out.println("5 ===> Email ");
		System.out.println("6 ===> Age ");
		System.out.println("7 ===> Hair color");
		int key = Integer.parseInt(strInput.nextLine());
		
		String searchColumn = "";
		String newColumn = "";
		
		switch (key) {
		case 1:
			System.out.println("Enter the old ID: ");
			oldID = strInput.nextLine();
			System.out.println("Enter the new ID: ");
			newID = strInput.nextLine();
			searchColumn = oldID; newColumn = newID;
			break;
		case 2:
			System.out.println("Enter the old Name: ");
			oldName = strInput.nextLine();	
			System.out.println("Enter the new Name: ");
			newName = strInput.nextLine();
			searchColumn = oldName; newColumn = newName;
			break;
		case 3:
			System.out.println("Enter the old Surname: ");
			oldSurname = strInput.nextLine();
			System.out.println("Enter the new Surname: ");
			newSurname = strInput.nextLine();
			searchColumn = oldSurname; newColumn = newSurname;
			break;
		case 4:
			System.out.println("Enter the old Telephone number: ");
			oldTel = strInput.nextLine();
			System.out.println("Enter the new Telephone number: ");
			newTel = strInput.nextLine();
			searchColumn = oldTel; newColumn = newTel;
			break;
		case 5:
			System.out.println("Enter the old Email: ");
			oldEmail = strInput.nextLine();
			System.out.println("Enter the new Email: ");
			newEmail = strInput.nextLine();
			searchColumn = oldEmail; newColumn = newEmail;
			break;
		case 6:
			System.out.println("Enter the old Age: ");
			oldAge = strInput.nextLine();
			System.out.println("Enter the new Age: ");
			newAge = strInput.nextLine();
			searchColumn = oldAge; newColumn = newAge;
			break;
		case 7:
			System.out.println("Enter the old Hair color: ");
			oldHairColor = strInput.nextLine();
			System.out.println("Enter the new Hair color: ");
			newHairColor = strInput.nextLine();
			searchColumn = oldHairColor; newColumn = newHairColor;
			break;
		default:
			break;
		}
		
		List<String> newLines = new ArrayList<>();
		for (String line : Files.readAllLines(Paths.get("address_book.txt"), StandardCharsets.UTF_8)) {
			StringTokenizer sto = new StringTokenizer(line, ",");
			if (line.contains(searchColumn)) {		    	
		       newLines.add(line.replace(searchColumn, ""+ newColumn));
		    } else {
		       newLines.add(line);
		    }
		}
		Files.write(Paths.get("address_book.txt"), newLines, StandardCharsets.UTF_8);
	}
	
	public void SearchContact(Scanner strInput) throws IOException {
		String ID, name, surname, tel, email, age, hairColor;
		
		System.out.println("Choose the column that you are searching in the Contact table: ");
		System.out.println("1 ===> ID ");
		System.out.println("2 ===> Name ");
		System.out.println("3 ===> Surname");
		System.out.println("4 ===> Telephone number ");
		System.out.println("5 ===> Email ");
		System.out.println("6 ===> Age ");
		System.out.println("7 ===> Hair color");
		int key = Integer.parseInt(strInput.nextLine());
		
		String searchColumn = "";
		
		switch (key) {
		case 1:
			System.out.println("Enter the  ID: ");
			ID = strInput.nextLine();
			searchColumn = ID; 
			break;
		case 2:
			System.out.println("Enter the  Name: ");
			name = strInput.nextLine();	
			searchColumn = name;
			break;
		case 3:
			System.out.println("Enter the  Surname: ");
			surname = strInput.nextLine();
			searchColumn = surname;
			break;
		case 4:
			System.out.println("Enter the  Telephone number: ");
			tel = strInput.nextLine();
			searchColumn = tel;
			break;
		case 5:
			System.out.println("Enter the  Email: ");
			email = strInput.nextLine();
			searchColumn = email;
			break;
		case 6:
			System.out.println("Enter the  Age: ");
			age = strInput.nextLine();
			searchColumn = age;
			break;
		case 7:
			System.out.println("Enter the  Hair color: ");
			hairColor = strInput.nextLine();
			searchColumn = hairColor;
			break;
		default:
			break;
		}
		
		ViewSpecificContacts(searchColumn);
	}

	public void DeleteContactAndRelation(Scanner strInput) throws IOException {
		String ID, record;

		File tempDB = new File("address_book_temp.txt");
		File db = new File("address_book.txt");

		BufferedReader br = new BufferedReader(new FileReader(db));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

		System.out.println("\t\t Delete  Record\n");

		System.out.println("Enter the  ID: ");
		ID = strInput.nextLine();

		while ((record = br.readLine()) != null) {

			if (record.contains(ID))
				continue;

			bw.write(record);
			bw.flush();
			bw.newLine();

		}

		br.close();
		bw.close();
		db.delete();

		tempDB.renameTo(db);
		
		deleteRelationForContact1(ID);

	}

	private void deleteRelationForContact1(String IDcontact1) throws IOException {
		List<String> newLines = new ArrayList<>();
		for (String line : Files.readAllLines(Paths.get("relation.txt"), StandardCharsets.UTF_8)) {
			StringTokenizer sto = new StringTokenizer(line, ",");
			RelationContact1WithContact2 rel = new RelationContact1WithContact2();
	    	rel.setId(Integer.parseInt(sto.nextToken()));
	    	Contact c1 = new Contact();
	    	c1.setId(Integer.parseInt(sto.nextToken()));
			rel.setContact1(c1);
			Contact c2 = new Contact();
			c2.setId(Integer.parseInt(sto.nextToken()));
			rel.setContact2(c2);
			rel.setRelationType(sto.nextToken());
			rel.setDetail(sto.nextToken());
			if (c1.getId() == Integer.parseInt(IDcontact1)) {		    	
		       continue;
		    } else {
		       newLines.add(line);
		    }
		}
		Files.write(Paths.get("relation.txt"), newLines, StandardCharsets.UTF_8);
		
	}


}
