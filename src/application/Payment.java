package application;

import java.sql.SQLException;
import java.util.ArrayList;


public class Payment {
	private Customer customer;
	private int amount;

	Payment(){
		setCustomer(null);
		setAmount(-1);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;}

	public int getAmount() {return amount;}
	public void setAmount(int amount) {this.amount = amount;}
	
	public void getPaymentDetails() {
		System.out.println(" => Payment Details!");
		System.out.println(" => Amount Paid: " + amount + " Rs");
	}
	
	public boolean makePayment(String firstName, String lastName, String accountNum, String cvv) throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		boolean success = pf.getDB().addPayment(firstName,lastName, accountNum,cvv);
		if(success == true) {
			ArrayList<Booking> bookList = null;
			bookList = pf.getDB().getAllBookings(bookList);
			for(int i=0 ; i<bookList.size() ; i++) {
				if(bookList.get(i).getBookDes().getName().equals(firstName+lastName)) {
					this.setAmount(bookList.get(i).getBookDes().getAmount());
					bookList.get(i).getBookDes().setPayment(this);
					this.getCustomer().getUserDetails();
					this.getPaymentDetails();
					this.getCustomer().getCredit().updateCredit(amount);
					return true;
				}
			}
		}	
		return false;
	}
}

