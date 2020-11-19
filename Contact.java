package model;

public class Contact implements Comparable{
	
	private int id;
	private int idRelation;
	private String name;
	private String surname;
	private String telephone;
	private String email;
	private int age;
	private String hairColor;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdRelation() {
		return idRelation;
	}
	public void setIdRelation(int idRelation) {
		this.idRelation = idRelation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHairColor() {
		return hairColor;
	}
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	@Override
	public int compareTo(Object ob) {
		// TODO Auto-generated method stub
		return this.getSurname().compareTo(((Contact) ob).getSurname());
	}
	
	

}
