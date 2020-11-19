package model;

public class RelationContact1WithContact2 {
	
	private int id;
	private Contact contact1;
	private Contact contact2;
	private String relationType;
	private String detail;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Contact getContact1() {
		return contact1;
	}
	public void setContact1(Contact contact1) {
		this.contact1 = contact1;
	}
	public Contact getContact2() {
		return contact2;
	}
	public void setContact2(Contact contact2) {
		this.contact2 = contact2;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	

}
