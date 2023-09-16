package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Booking {
	private BookingDetails bookDesc;
	
	Booking(){
		bookDesc =  null;
	}
	
	Booking(BookingDetails newDesc,int id, String cname, String btitle, int tickets, String datetime, int amount) {
		newDesc = new BookingDetails();
		newDesc.setBookingID(id);
		newDesc.setName(cname);
		newDesc.setTitle(btitle);
		newDesc.setDateTime(datetime);
		newDesc.setNumTickets(tickets);
		newDesc.setAmount(amount);
		setBookDes(newDesc);
	}

	public BookingDetails getBookDes() {return bookDesc;}
	public void setBookDes(BookingDetails bookDes) {this.bookDesc = bookDes;}

	public static void setAllBookingFromDB() throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		ArrayList<Booking> book = new ArrayList<Booking>();
		book = pf.getDB().getAllBookings(book);
		System.out.println(book.size());
		for(int i=0 ; i<book.size() ; i++) {
			System.out.println(" => Booking #" + (i+1));
			System.out.println(" => Booking ID: " + book.get(i).getBookDes().getBookingID());
			System.out.println(" => Customer Name: " + book.get(i).getBookDes().getName());
			System.out.println(" => Movie Title: " + book.get(i).getBookDes().getTitle());
			System.out.println(" => Movie Date and Time: " + book.get(i).getBookDes().getDateTime());
			System.out.println(" => Movie Tickets Bought: " + book.get(i).getBookDes().getNumTickets());
			System.out.println(" => Bill AMmount: " + book.get(i).getBookDes().getAmount());
		}
		book = null;
	}
	public boolean makeBooking(String title, int seatsBooked, String screenid) throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		SingletonCookie sc = SingletonCookie.getInstace("",-1,"");
		boolean success = pf.getDB().newBooking(title,seatsBooked,screenid);
		if(success == true) {
	    	ArrayList<Customer> custList = null;
	    	custList = pf.getDB().getAllCustomers(custList);
	    	ArrayList<Screening> screenList = null;
	    	screenList = pf.getDB().getAllScreenings(screenList);
			bookDesc  = new BookingDetails();
	    	bookDesc.setBookingID(pf.getDB().getBookingID());
	    	for(int i=0 ; i<custList.size(); i++) {
	    		if(sc.getUserID() == custList.get(i).getID()) 
	    			bookDesc.setName(custList.get(i).getFname()+custList.get(i).getLname());
	    	}

	    	for(int i=0 ; i<screenList.size(); i++) {
	    		if((""+screenList.get(i).getScreeningID()).equals(screenid)) {
	    			bookDesc.setDateTime(screenList.get(i).getDate()+" "+screenList.get(i).getTime());
	    		}
	    			
	    	}
			bookDesc.setAmount(seatsBooked*1000);
	    	bookDesc.setTitle(title);
	    	bookDesc.setNumTickets(seatsBooked);
			System.out.println(" => New Booking " + this.bookDesc.getBookingID()  + " has been added!");
			return true;
		}	
		return false;
	}
	
	public boolean removeBooking(String firstName, String lastName, String accountNum, String cvv) throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		boolean success = pf.getDB().removeBooking();
		if(success == true) {
			ArrayList<Booking> bookList = null;
			bookList = pf.getDB().getAllBookings(bookList);
			for(int i=0 ; i<bookList.size() ; i++) {
				if(bookList.get(i) == this) {
					this.getBookDes().setAmount(-1);
					this.getBookDes().setBookingID(-1);
					this.getBookDes().setDateTime("");
					this.getBookDes().setName("");
					this.getBookDes().setNumTickets(-1);
					this.getBookDes().setPayment(null);
					this.getBookDes().setTitle("");
					this.setBookDes(null);
					bookList.remove(i);
					return true;
				}
			}
		}	
		return false;
	}
	
	/*
	 public ArrayList<Booking> getBookings() throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		ArrayList<Booking> bookList = null;
		bookList = pf.getDB().getAllBookings(bookList);
		return bookList;
	}
	
	*/
}
