package application;

import java.sql.SQLException;
import java.util.ArrayList;

public  class Account {
	private int userID;
	private String username;
	private String password;
	
	public int getUserID() {return userID;}
	public void setUserID(int userID) {this.userID = userID;}
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	Account(){
		userID = -1;
		username = null;
		password = null;
	}

	Account(int id, String uname, String lname){
		userID = id;
		username = uname;
		password = lname;
	}
	
	public void getAccountDetails() {
		System.out.println(" => Account Details!");
		System.out.println(" => UserID: " + userID);
		System.out.println(" => Username: " + username);
		System.out.println(" => Password: " + password);
	}

	public void setAccount(int id, String uname, String lname) throws SQLException {
		userID = id;
		username = uname;
		password = lname;
	}
	
	public boolean newRegistration(String Username,String Password, String fname, String lname, String path) throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		boolean success = pf.getDB().addNewUser(Username,Password,fname,lname,path);
		if(success == true) {
			this.setAccount(pf.getDB().getUserID(),Username,Password);
			Customer customer = new Customer();
			customer.newCustomer(pf.getDB().getUserID(),fname,lname,path);
			System.out.println(" => New Account has been created!");
			//this.getAccountDetails();
			return true;
		}
		return false;
	}

	public boolean Login(String Username, String Password, String Btn) {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		boolean success = pf.getDB().Login(Username, Password,Btn);
		if(success == true) {
			System.out.println(" => " + Username + " has logged in!");
			return true;
		}
		return false;
	}
	
	public void deleteAccount() {
		userID = -1;
		username = null;
		password = null;
	}	
}



