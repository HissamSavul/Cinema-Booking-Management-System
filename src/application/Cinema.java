package application;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public final class Cinema {
	//public static ArrayList<Booking> bookList = new  ArrayList<Booking>();
	//public static ArrayList<Movie> movieList = new ArrayList<Movie>();
	//public static ArrayList<Screening> screenList;
	//public static ArrayList<Customer> custList;
	//public static ArrayList<Payment> paymentlist;
	private  Booking booking;
	private  Movie movie;
	private  Screening screening;
	private  Account account;

    private static Cinema instance;
    private Cinema(Booking b,Movie m,Screening s,Account a) {
    	b = new Booking();
    	m = new Movie();
    	s = new Screening();
    	a = new Account();
    	booking = b;
    	movie = m;
    	screening = s;
    	account = a;
    }
    public Booking getBooking() {return booking;}
	public void setBooking(Booking booking) {this.booking = booking;}
	public Movie getMovie() {return movie;}
	public void setMovie(Movie movie) {this.movie = movie;}
    public Account getAccount() {return account;}
	public void setAccount(Account account) {this.account = account;}
	public Screening getScreening() {return screening;}
	public void setScreening(Screening screening) {this.screening = screening;}

	public static Cinema getInstace(Booking b, Movie m, Screening s, Account a) {
        if(instance == null) {
            instance = new Cinema(b,m,s,a);
        }
        return instance;
    }
    
    public boolean makePayment(String firstName, String lastName, String accountNum, String cvv) throws SQLException {
		Payment payment = new Payment();
		boolean success = payment.makePayment(firstName,lastName,accountNum,cvv);
    	if(success == true) {
    		//paymentlist.add(payment);
	    	return true;
    	}
		return false;	
    	
    }
    public boolean makeBooking(String title,int spin,String screenNo) throws SQLException {
    	Booking booking = new Booking();
		boolean success = booking.makeBooking(title,spin,screenNo);
    	if(success == true) {
    		//bookList.add(booking);
	    	return true;
    	}
		return false;	
    	
    }
    public boolean cancelBooking(String firstName, String lastName, String accountNum, String cvv) throws SQLException {
		boolean success = booking.removeBooking(firstName,lastName,accountNum,cvv);
		if(success == true) {
		    //bookList.remove(i);
			return true;
		}	
    	return false;
    }
    public boolean addMovie(javafx.event.ActionEvent event, String title, String duration, String genre, String path) throws SQLException, IOException {
    	Movie movie = new Movie();
    	boolean success = movie.addMovie(title,duration,genre,path);
		if(success == true) {
			//movieList.add(movie);
	    	return true;
		}
		return false;	
    }
    public boolean removeMovie(javafx.event.ActionEvent event,int movID, String title, String duration, String genre, String path) throws SQLException, IOException {
    	boolean success = Cinema.getInstace(null,null,null,null).getMovie().removeMovie(movID,title,duration,genre,path);
		if(success == true) {
		    //movieList.remove(i);
			return true;
		}	
    	return false;
    }
    public boolean updateMovie(javafx.event.ActionEvent event,int movID, String title, String duration, String genre, String path) throws SQLException, IOException {
    	boolean success = Cinema.getInstace(null,null,null,null).getMovie().updateMovie(movID,title,duration,genre,path);
		if(success == true) {
			return true;
		}	
    	return false;
    }
    public boolean addScreen(String title, String date,String time,int totalSeats) throws SQLException {
		Screening screening = new Screening();
		boolean success = screening.addScreening(title,date,time,totalSeats);
    	if(success == true) 
	    	return true;
		return false;	
    }
    public boolean updateScreen(String screenNo,String title, String date,String time,int totalSeats) throws SQLException {
		boolean success = Cinema.getInstace(null,null,null,null).getScreening().updateScreening(screenNo,title, date,time,totalSeats);
    	if(success == true) 
	    	return true;
		return false;	
    }
    public boolean removeScreen(String screenNo,String title, String date,String time,int totalSeats) throws SQLException {
		boolean success = Cinema.getInstace(null,null,null,null).getScreening().removeScreening(screenNo,title, date,time,totalSeats);
    	if(success == true) 
	    	return true;
		return false;	
    }
    public boolean Signup(javafx.event.ActionEvent event,String Username, String Password, String firstName, String secondName, String path) throws IOException, SQLException {
		Account account = new Account();
		boolean success = account.newRegistration(Username,Password,firstName,secondName,path);
		if(success == true)
			return true;
		return false;
    }
    //initial manager data
	Manager manager = new Manager(1,"Hissam","Savul","C:\\Users\\admin\\Pictures\\Screenshots\\Screenshot(19).png");
	Account managerAcc = new Account(1,"savul","123");
	
    public String Signin(RadioButton ManBtn, RadioButton CustBtn, String Username, String Password, Button SignIn) throws IOException {
    	boolean success;
		if(CustBtn.isSelected()) {
			success = Cinema.getInstace(null,null,null,null).getAccount().Login(Username, Password, CustBtn.getText());
			if(success == true) {
				return "CustomerInterface.fxml";
			}
		}	
		else if(ManBtn.isSelected()) {
			success = Cinema.getInstace(null,null,null,null).getAccount().Login(Username, Password, ManBtn.getText());
			if(success == true) {
				return "ManagerInterface.fxml";
			}
		}
		else
			Cinema.getInstace(null,null,null,null).getAccount().Login(Username, Password,"");
		return "";
    }
    public void updateAccount(javafx.event.ActionEvent event,String Username, String Password, String firstName, String secondName, String path) throws IOException, SQLException {
		
    }
    public void deleteAccount(javafx.event.ActionEvent event,String Username, String Password, String firstName, String secondName, String path) throws IOException, SQLException {

		
    }
}