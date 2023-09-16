package application;

public abstract class User {
	private Account acc;
	private int ID;
	private String fname;
	private String lname;
	private String picture;

	User(){
		setID(-1);
		setFname(null);
		setLname(null);
		setPicture(null);
	}
	User(int id, String fname1, String lname1, String img){
		setID(id);
		setFname(fname1);
		setLname(lname1);
		setPicture(lname1);
	}
	
	public Account getAcc() {
		return acc;
	}
	public void setAcc(Account acc) {
		this.acc = acc;
	}
	public String getPicture() {return picture;}
	public void setPicture(String picture) {this.picture = picture;}
	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}
	public String getFname() {return fname;}
	public void setFname(String name) {this.fname = name;}
	public String getLname() {return lname;}
	public void setLname(String name) {this.lname = name;}
	
	public abstract void getUserDetails();
}

class Customer extends User{
	private Credit credit;
	Customer(){
		super();
	}
	
	Customer(int id, String fname1, String lname1, String img){
		super(id,fname1,lname1,img);
	}
	
	public Credit getCredit() {return credit;}
	public void setCredit(Credit credit) {this.credit = credit;}
	
	@Override
	public void getUserDetails() {
		System.out.println(" => Customer Details!");
		System.out.println(" => Customer ID: " + getID());
		System.out.println(" => Firstname: " + getFname());
		System.out.println(" => Lastname: " + getLname());
		System.out.println(" => Picture Path: " + getPicture());
	}
	
	public void newCustomer(int newID,String firstname, String lastname, String path) {
		setID(newID);
		setFname(firstname);
		setLname(lastname);
		setPicture(path);		
		System.out.println(" => New Customer has been created!");
		getUserDetails();
	}
}
class Manager extends User{
	Manager(){
		super();
	}
	
	Manager(int id, String fname1, String lname1, String img){
		super(id,fname1,lname1,img);
	}

	@Override
	public void getUserDetails() {
		System.out.println(" => Manager Details!");
		System.out.println(" => Manager ID: " + getID());
		System.out.println(" => Firstname: " + getFname());
		System.out.println(" => Lastname: " + getLname());
		System.out.println(" => Picture Path: " + getPicture());
	}
}
