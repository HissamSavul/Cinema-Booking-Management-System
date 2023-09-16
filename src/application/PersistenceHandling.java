package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;

import java.sql.*;  
import java.lang.Math;   
public abstract class PersistenceHandling {
	public Connection con;
	public Statement stmt;
	public PreparedStatement pstat;
	public ResultSet rs;
	public int nextCvv;
	public int nextBal;
	
	PersistenceHandling(){
		con = null;
		stmt = null;
		pstat = null;
		rs = null;
	}
	
	protected int getMovieID() throws SQLException {
		int newID=0;
		String Query = "SELECT movieID FROM movie ORDER BY movieID DESC LIMIT 1";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		while(rs.next()) 
			newID = rs.getInt(1);
		return newID;
	}
	
	protected int getUserID() throws SQLException {
		int newID=0;
		String Query = "SELECT userID FROM account ORDER BY userID DESC LIMIT 1";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		while(rs.next()) 
			newID = rs.getInt(1);
		return newID;
	}
	
	protected int getScreenID() throws SQLException {
		int newID=0;
		String Query = "SELECT screenID FROM screening ORDER BY screenID DESC LIMIT 1";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		while(rs.next()) 
			newID = rs.getInt(1);
		return newID;
	}
	
	protected int getBookingID() throws SQLException {
		int newID=0;
		String Query = "SELECT bookingID FROM booking ORDER BY bookingID DESC LIMIT 1";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		while(rs.next()) 
			newID = rs.getInt(1);
		return newID;
	}
	
	protected int getBillID() throws SQLException {
		int newID=0;
		String Query = "SELECT billID FROM bill ORDER BY billID DESC LIMIT 1";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		while(rs.next()) 
			newID = rs.getInt(1);
		return newID;
	}
	
	protected int getPaymentID() throws SQLException {
		int newID=0;
		String Query = "SELECT paymentID FROM payment ORDER BY paymentID DESC LIMIT 1";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		while(rs.next()) 
			newID = rs.getInt(1);
		return newID;
	}
	
	protected int getCrediID() throws SQLException {
		int newID=0;
		String Query = "SELECT accNum FROM credit ORDER BY accNum DESC LIMIT 1";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		while(rs.next()) 
			newID = rs.getInt(1);
		return newID;
	}
	
	protected String getImg(String title) throws SQLException {
		String Query = "SELECT image FROM movie WHERE title = ?";
		pstat = con.prepareStatement(Query);
		pstat.setString(1, title);
		rs = pstat.executeQuery();
		while(rs.next()) 
			return rs.getString(1);
		return null;
	}
	
	protected int getDesiredMovie(String title) throws SQLException {
		String Query = "SELECT movieID FROM movie WHERE title = ?";
		pstat = con.prepareStatement(Query);
		pstat.setString(1, title);
		rs = pstat.executeQuery();
		while(rs.next()) 
			return rs.getInt(1);
		return -1;
	}
	
	
	protected ArrayList<Movie> getAllMovies(ArrayList<Movie> movieList) throws SQLException {
		movieList =  new ArrayList<Movie>();
		String Query2 = "SELECT * FROM movie";
		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();
		while(rs.next()) {
			MovieDescription desc = null;
			Movie movie = new Movie(desc,rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
			movieList.add(movie);	
		}
		return movieList;  		
	}
	
	protected ArrayList<Screening> getAllScreenings(ArrayList<Screening> screenList) throws SQLException {
		screenList =  new ArrayList<Screening>();
		//screening for every movie
		String Query2 = "SELECT * FROM screening";
		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();
		while(rs.next()) {
			 Screening screen = new Screening(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getInt(6));
			 screenList.add(screen);	
		}
		return screenList;  		
	}

	
	protected ArrayList<Booking> getAllBookings(ArrayList<Booking> bookList) throws SQLException {
		bookList = new ArrayList<Booking>();
		//screening for every movie
		String Query2 = "SELECT booking.bookingID,concat(firstname,\" \",lastname),movieTitle,numSeats,concat(date,\" \",time) FROM screening INNER JOIN booking on (screenID = screeningID) INNER JOIN customer on (customer.customerID = booking.customerID) INNER JOIN bill on (booking.bookingID = bill.bookingID) ORDER BY booking.bookingID";
		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();
		while(rs.next()) {
			BookingDetails desc = null;
			Booking booking = new Booking(desc,rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),(rs.getInt(4)*1000));			
			bookList.add(booking);	
		}
		return bookList;  		
	}
	
	protected ArrayList<Customer> getAllCustomers(ArrayList<Customer> custList) throws SQLException {
		custList = new ArrayList<Customer>();
		//screening for every movie
		String Query2 = "SELECT * FROM customer";
		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();
		while(rs.next()) {
			Customer cust = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			System.out.print(cust.getLname());
			custList.add(cust);	
		}
		return custList;  		
	}
	
	protected ArrayList<Payment> getAllPayments(ArrayList<Payment> payList) throws SQLException {
		payList = new ArrayList<Payment>();
		//screening for every movie
		String Query2 = "SELECT * FROM payment";
		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();
		while(rs.next()) {
			Payment payment = new Payment();
			payList.add(payment);	
		}
		return payList;  		
	}
	
	protected boolean checkEmptyFields(int ID,String Username, String Password, String role, String fname, String lname) throws SQLException {
		Alert alert;
		if(Username.isEmpty() || Password.isEmpty() || role.isEmpty() || ID == 0 || fname.isEmpty() || lname.isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Fields are Empty!");
			alert.showAndWait();
			return true;
		}
		return false;
	}
	
	protected boolean checkEmptyFields2(int ID,String title, String duration, String genre) throws SQLException {
		Alert alert;
		if(title.isEmpty() || duration.isEmpty() || genre.isEmpty() || ID == 0) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Fields are Empty!");
			alert.showAndWait();
			return true;
		}
		return false;
	}

	protected boolean checkEmptyFields3(int ID,String Username, String Password, String role, String fname, String lname, String path) throws SQLException {
		Alert alert;
		if(Username.isEmpty() || Password.isEmpty() || role.isEmpty() || ID == 0 || fname.isEmpty() || lname.isEmpty() || path == null) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Fields are Empty!");
			alert.showAndWait();
			return true;
		}
		return false;
	}
	
	protected boolean checkEmptyFields4(String cvv,String acc, String fname, String lname) throws SQLException {
		Alert alert;
		if(fname.isEmpty() || lname.isEmpty() || acc.isEmpty() || cvv == null) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Fields are Empty!");
			alert.showAndWait();
			return true;
		}
		return false;
	}
	
	protected boolean Login(String Username, String Password, String role) {
		String fname = null,lname = null;
		String Query = "SELECT * FROM ACCOUNT WHERE username = ? and password = ? and Role = ?";
		String Query3 = "SELECT customerID,image FROM customer INNER JOIN account ON (customerID = userID) WHERE username = ?";
		try {	
			String Query2 = "SELECT firstname,lastname FROM manager INNER JOIN account ON (managerID = userID)";
			pstat = con.prepareStatement(Query2);
			rs = pstat.executeQuery();
			while(rs.next()) {
				fname = rs.getString(1);
				lname = rs.getString(2);
			}
			
			boolean empty = checkEmptyFields(getUserID(),Username,Password,role,fname,lname);
			pstat = con.prepareStatement(Query);
			pstat.setString(1, Username);
			pstat.setString(2, Password);
			pstat.setString(3, role);
			rs = pstat.executeQuery();
			Alert alert;
			if(empty == false) {
				if(rs.next()) {
					pstat = con.prepareStatement(Query3);
					pstat.setString(1, Username);
					rs = pstat.executeQuery();
					while(rs.next()) {
						SingletonCookie sc = SingletonCookie.getInstace(Username,rs.getInt(1),rs.getString(2));
						sc.setUserID(rs.getInt(1));
						sc.setImagePath(rs.getString(2));
						sc.setUserName(Username);
					}
					return true;
				}
				else {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Wrong Username, Password or Role");
					alert.showAndWait();
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;                                                           
		
	}
	
	protected boolean addNewUser(String Username, String Password, String fname, String lname, String path) throws SQLException {	
		int newID = getUserID();
		String Query = "INSERT INTO account(userID,username,password,Role) VALUES(?,?,?,?)";
		String Query2 = "INSERT INTO customer(customerID,firstname,lastname,image) VALUES(?,?,?,?);";
		String Query3 = "SELECT * FROM account";
		String Query4 = "INSERT INTO credit(accNum,cvv,customerID,balance) VALUES(?,?,?,?);";

		try {		
			boolean empty = checkEmptyFields3((newID+1),Username,Password,"Customer",fname,lname,path);
			Alert alert;
			if(empty == false) {
				pstat = con.prepareStatement(Query3);
				rs = pstat.executeQuery();
				
				while(rs.next()) {
					if(Username.equals(rs.getString(2))) {
						System.out.println(" => User already exists!!");
						alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Username is taken!");
						alert.show();
						return false;
					}
				}
				
				if(Password.length() > 10) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Passowrd should be of length<10");
					alert.showAndWait();		
					return false;
				} 
				pstat = con.prepareStatement(Query);
				pstat.setInt(1, (newID+1));
				pstat.setString(2, Username);
				pstat.setString(3, Password);
				pstat.setString(4, "Customer");
				int rowsInserted = pstat.executeUpdate();
				if (rowsInserted > 0)
				    System.out.println(" => A new Account was inserted successfully!");
				    
				pstat = con.prepareStatement(Query2);
				pstat.setInt(1, (newID+1));
				pstat.setString(2, fname);
				pstat.setString(3, lname);
				pstat.setString(4, path);
				rowsInserted = pstat.executeUpdate();
				
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Message");
				alert.setHeaderText(null);
				alert.setContentText("SuccessFully Created a new Account & User!");
				alert.showAndWait();	
				
				if (rowsInserted > 0) 
				    System.out.println(" => A new Customer was inserted successfully!");	
				
				Random random = new Random();
				nextCvv = random.nextInt(100,999);
				nextBal = random.nextInt(900,6000);
				int newID2 = getCrediID();
				pstat = con.prepareStatement(Query4);
				pstat.setInt(1, (newID2+1));
				pstat.setInt(2, nextCvv);
				pstat.setInt(3, (newID+1));
				pstat.setInt(4, nextBal);
				rowsInserted = pstat.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}  
	
	protected ObservableList<MovieDescription> showAllMovies(ObservableList<MovieDescription> movieList) throws SQLException {
		String Query = "SELECT count(*) FROM movie";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		
		while(rs.next()) {
			if(rs.getInt(1) == 0) {
				System.out.println(" => No Movies exist in Database");
				return movieList;
			}
		}
		
		String Query2 = "SELECT * FROM movie";
		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();
		while(rs.next()) {
			MovieDescription desc = null;
			Movie movie = new Movie(desc,rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
			movieList.add(movie.getMovieDesc());	
		}
		return movieList;                                                           
	}
	
	protected ObservableList<Screening> showAllScreens(ObservableList<Screening> screenList) throws SQLException {
		String Query = "SELECT count(*) FROM screening";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		
		while(rs.next()) {
			if(rs.getInt(1) == 0) {
				System.out.println(" => No Screenings exist in Database");
				return screenList;
			}
		}
		
		String Query2 = "SELECT * FROM screening";
		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();
		while(rs.next()) {
			Screening screen = new Screening(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getInt(6));
			screenList.add(screen);	
		}
		return screenList;                                                           
	}
	
	protected ObservableList<Customer> showAllCustomers(ObservableList<Customer> customerList) throws SQLException {
		String Query = "SELECT count(*) FROM customer";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		
		while(rs.next()) {
			if(rs.getInt(1) == 0) {
				System.out.println(" => No Customers exist in Database");
				return customerList;
			}
		}
	
		String Query2 = "SELECT * FROM customer";
		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();
		while(rs.next()) {
			Customer cust = new Customer();
			cust.newCustomer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			customerList.add(cust);	
		}
		return customerList;                                                           
	}
	
	protected ObservableList<Account> showAllAccounts(ObservableList<Account> accList) throws SQLException {
		String Query = "SELECT count(*) FROM account";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		
		while(rs.next()) {
			if(rs.getInt(1) == 0) {
				System.out.println(" => No accounts exist in Database");
				return accList;
			}
		}
		
		String Query2 = "SELECT * FROM account WHERE Role = ?";
		pstat = con.prepareStatement(Query2);
		pstat.setString(1, "Customer");
		rs = pstat.executeQuery();
		while(rs.next()) {
			Account acc = new Account(rs.getInt(1), rs.getString(2), rs.getString(3));
			accList.add(acc);	
		}
		return accList;                                                           
	}
	
	protected ObservableList<BookingDetails> getBookingDetails(ObservableList<BookingDetails> bookList) throws SQLException {
		String Query = "SELECT count(*) FROM booking";
		pstat = con.prepareStatement(Query);
		rs = pstat.executeQuery();
		
		while(rs.next()) {
			if(rs.getInt(1) == 0) {
				System.out.println(" => No booking exist in Database");
				return bookList;
			}
		}
		
		String Query2 = "SELECT booking.bookingID,concat(firstname,\" \",lastname),movieTitle,numSeats,concat(date,\" \",time),TotalBill FROM screening INNER JOIN booking on (screenID = screeningID) INNER JOIN customer on (customer.customerID = booking.customerID) INNER JOIN bill on (booking.bookingID = bill.bookingID)";
		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();

		while(rs.next()) {
			BookingDetails bd = new BookingDetails();
			Booking booking = new Booking(bd,rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6));			
			bookList.add(booking.getBookDes());	
		}
		return bookList;                                                           
	}
	
	protected boolean newMovie(int ID, String title, String duration, String genre, String imagePath) throws SQLException {
		int newID = getMovieID();
		String Query = "INSERT INTO movie(movieID,title,duration,genre,image,screeningID) VALUES(?,?,?,?,?,?)";
		String Query2 = "SELECT * FROM movie";

		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();
		
		while(rs.next()) {
			if(title.equals(rs.getString(2))) {
				System.out.println(" => Movie already exists, add screening instead!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText(" Movie already exists, add screening instead!");
				alert.show();
				return false;
			}
		}
		
		boolean empty = checkEmptyFields2((newID+1),title,duration,genre);
		Alert alert;
		if(empty == false) {
			if(duration.length() > 4) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("movie should not be of more than 4hrs");
				alert.showAndWait();		
				return false;
			} 
			pstat = con.prepareStatement(Query);
			pstat.setInt(1, (newID+1));
			pstat.setString(2, title);
			pstat.setString(3, duration);
			pstat.setString(4, genre);
			pstat.setString(5, imagePath);
			pstat.setInt(6, -1);
			int rowsInserted = pstat.executeUpdate();
			if (rowsInserted > 0)
			    System.out.println(" => A new movie was inserted successfully!");
			
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("SuccessFully added movie!");
			alert.showAndWait();				
			return true;
		}
		return false;                                                           
	}
	
	protected boolean delMovie(int ID, String title, String duration, String genre, String imagePath) throws SQLException {
		int newID = getMovieID(); 
		String Query = "DELETE FROM movie WHERE title = ?";
		String Query2 = "SELECT count(*) FROM movie";
		String Query3 = "SELECT count(title) FROM movie WHERE title=?";
		String Query4 = "SELECT count(*) FROM booking INNER JOIN movie ON (booking.movieID = movie.movieID) WHERE title = ?";
		String Query5 = "SELECT movieID FROM movie WHERE title = ?";
		String Query6 = "DELETE FROM screening WHERE movieID = ?";

		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();
		Alert alert;

		while(rs.next()) {
			if(rs.getInt(1) == 0) {
				System.out.println(" => No Movies exist in Database");
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Message");
				alert.setHeaderText(null);
				alert.setContentText("Movie Table is Empty!");
				alert.showAndWait();
				return false;
			}
		}
		
		pstat = con.prepareStatement(Query3);
		pstat.setString(1, title);
		rs = pstat.executeQuery();

		while(rs.next()) {
			if(rs.getInt(1) == 0) {
				System.out.println(" => the searched movie doesnt exist in database");
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Movie Doesn't Exist in Database!");
				alert.showAndWait();
				return false;
			}
			break;
		}
		
		pstat = con.prepareStatement(Query4);
		pstat.setString(1, title);
		rs = pstat.executeQuery();
		while(rs.next()) {
			if(rs.getInt(1) != 0) {
				System.out.println(" => Movie already has bookings");
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Movie Already has Bookings!");
				alert.showAndWait();
				return false;
			}
			break;
		}
		
		int currID = 0;
		pstat = con.prepareStatement(Query5);
		pstat.setString(1, title);
		rs = pstat.executeQuery();
		while(rs.next()) {
			currID = rs.getInt(1);
		}
		
		boolean empty = checkEmptyFields2((newID+1),title,duration,genre);
		if(empty == false) {
			pstat = con.prepareStatement(Query);
			pstat.setString(1, title);
			int rowsInserted = pstat.executeUpdate();
			if (rowsInserted > 0)
			    System.out.println(" => A movie was deleted successfully!");
			
			pstat = con.prepareStatement(Query6);
			pstat.setInt(1, currID);
			rowsInserted = pstat.executeUpdate();
			
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("SuccessFully deleted movie!");
			alert.showAndWait();				
			return true;
		}
		return false;                                                           
	}
	
	protected boolean updateMovie(int ID, String title, String duration, String genre, String imagePath) throws SQLException {
		int newID = getMovieID(); 
		String Query = "UPDATE movie SET title = ?,duration = ?,genre = ?,image = ? WHERE movieID = ?";
		
		if(imagePath.equals(null)) 
			Query = "UPDATE movie SET title = ?,duration = ?,genre = ?";
		
		Alert alert;
		boolean empty = checkEmptyFields2((newID+1),title,duration,genre);
		if(empty == false) {
			String Query4 = "SELECT count(*) FROM booking INNER JOIN movie ON (booking.movieID = movie.movieID) WHERE title = ?";
			pstat = con.prepareStatement(Query4);
			pstat.setString(1, title);
			rs = pstat.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1) != 0) {
					System.out.println(" => Movie already has bookings");
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Movie Already has Bookings!");
					alert.showAndWait();
					return false;
				}
				break;
			}
			String Query3 = "UPDATE screening SET movieTitle = ? WHERE movieID = ?";
			pstat = con.prepareStatement(Query3);
			pstat.setString(1, title);
			pstat.setInt(2, ID);
			int rowsInserted = pstat.executeUpdate();
			
			pstat = con.prepareStatement(Query);
			pstat.setString(1, title);
			pstat.setString(2, duration);
			pstat.setString(3, genre);
			pstat.setInt(5, ID);

			if(!imagePath.equals(null)) 
				pstat.setString(4, imagePath);

			rowsInserted = pstat.executeUpdate();
			if (rowsInserted > 0)
			    System.out.println(" => A movie was updated successfully!");
			
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("SuccessFully updated movie!");
			alert.showAndWait();				
			return true;
		}
		return false;                                                           
	}
	
	//screening
	protected boolean newScreening(String title, String date, String time, int tseats) throws SQLException {
		int newID = getScreenID();
		int movID = 0;
		String Query = "INSERT INTO screening(screenID,date,time,totalSeats,movieTitle,movieID) VALUES(?,?,?,?,?,?)";
		String Query3 = "SELECT movieID,count(*) FROM movie WHERE title = ?";

		boolean empty = checkEmptyFields2(newID+1,title,date,time);
		Alert alert;
		if(empty == false) {
			pstat = con.prepareStatement(Query3);
			pstat.setString(1, title);
			rs = pstat.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt(2) > 0) 
					movID = rs.getInt(1);
				else {
					System.out.println(" => Movie doesnt exist, first add movie");
					alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(" Movie doesnt exist, first add Movie");
					alert.show();
					return false;
				}
			}
				
			pstat = con.prepareStatement(Query);
			pstat.setInt(1, (newID+1));
			pstat.setString(2, date);
			pstat.setString(3, time);
			pstat.setInt(4, tseats);
			pstat.setString(5, title);
			pstat.setInt(6, movID);
			int rowsInserted = pstat.executeUpdate();
			if (rowsInserted > 0)
			    System.out.println(" => A new screening was inserted successfully!");
			
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("SuccessFully added screening!");
			alert.showAndWait();				
			return true;
		}
		return false;                                                           
	}
	
	protected boolean delScreening(String screen, String title, String date, String time, int tseats) throws SQLException {
		int newID = getScreenID();
		int screenid = Integer.valueOf(screen);  

		String Query = "DELETE FROM screening WHERE screenID = ?";
		String Query2 = "SELECT count(*) FROM screening";
		String Query3 = "SELECT count(screenID) FROM screening WHERE screenID=?";

		pstat = con.prepareStatement(Query2);
		rs = pstat.executeQuery();
		Alert alert;

		while(rs.next()) {
			if(rs.getInt(1) == 0) {
				System.out.println(" => No Screening exist in Database");
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Message");
				alert.setHeaderText(null);
				alert.setContentText("Screening Table is Empty!");
				alert.showAndWait();
				return false;
			}
		}
		
		pstat = con.prepareStatement(Query3);
		pstat.setInt(1, screenid);
		rs = pstat.executeQuery();

		while(rs.next()) {
			if(rs.getInt(1) == 0) {
				System.out.println(" => the searched screening doesnt exist in database");
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("screening Doesn't Exist in Database!");
				alert.showAndWait();
				return false;
			}
			break;
		}
		boolean empty = checkEmptyFields2(screenid,title,date,time);
		if(empty == false) {
			pstat = con.prepareStatement(Query);
			pstat.setInt(1, screenid);
			int rowsInserted = pstat.executeUpdate();
			if (rowsInserted > 0)
			    System.out.println(" => A screening was deleted successfully!");
			
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("SuccessFully deleted screening!");
			alert.showAndWait();				
			return true;
		}
		return false;                                                           
	}
	
	protected boolean updateScreening(String screen, String title, String date, String time, int tseats) throws SQLException {
		int newID = getScreenID();
		int screenid = Integer.valueOf(screen);  
		String Query = "UPDATE screening SET movieTitle = ?,date = ?,time = ? WHERE screenID = ?";
		String Query2 = "SELECT count(title) FROM movie WHERE title=?";
		
		Alert alert;
		boolean empty = checkEmptyFields2(screenid,title,date,time);
		if(empty == false) {
			pstat = con.prepareStatement(Query2);
			pstat.setString(1, title);
			rs = pstat.executeQuery();

			while(rs.next()) {
				if(rs.getInt(1) == 0) {
					System.out.println(" => the entered movie doesnt exist in database");
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Movie Doesn't Exist in Database!");
					alert.showAndWait();
					return false;
				}
				break;
			}
			pstat = con.prepareStatement(Query);
			pstat.setString(1, title);
			pstat.setString(2, date);
			pstat.setString(3, time);
			pstat.setInt(4, screenid);

			int rowsInserted = pstat.executeUpdate();
			if (rowsInserted > 0)
			    System.out.println(" => A screening was updated successfully!");
			
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("SuccessFully updated screening!");
			alert.showAndWait();				
			return true;
		}
		return false;                                                           
	}
	
	protected void updateImg(int id, String imgPath) throws SQLException {
		String Query = "UPDATE customer SET image = ?  WHERE customerID = ?";
		pstat = con.prepareStatement(Query);
		pstat.setString(1, imgPath);
		pstat.setInt(2, id);
		int rowsInserted = pstat.executeUpdate();
		if (rowsInserted > 0)
		    System.out.println(" => Image was updated successfully!");
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Message");
		alert.setHeaderText(null);
		alert.setContentText("SuccessFully updated image!");
		alert.showAndWait();	
	}
	
	protected int getSelectedScreeningBookedSeats(String screenid) throws NumberFormatException, SQLException {
		String Query = "SELECT totalSeats FROM screening WHERE screenID = ?";
		pstat = con.prepareStatement(Query);
		pstat.setInt(1, Integer.valueOf(screenid));
		rs = pstat.executeQuery();
		while(rs.next())
			return Integer.valueOf(rs.getString(1));
		return -1;
	}
	
	//booking
	protected boolean newBooking(String title, int seatsBooked, String screenID) throws SQLException {
		Alert alert;
		if(seatsBooked == 0) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR Message");
			alert.setHeaderText(null);
			alert.setContentText("no seats are reserved so no booking made!");
			alert.showAndWait();	
			return false;
		}
		SingletonCookie sc = SingletonCookie.getInstace("", -1, "");

		int newID = getBookingID();
		int movID = 0,currentSeats=0;
		String Query = "INSERT INTO booking(bookingID,movieID,customerID,numSeats,screeningID) VALUES(?,?,?,?,?)";
		String Query2 = "SELECT movieID FROM movie WHERE title = ?";
		String Query4 = "SELECT totalSeats FROM screening WHERE screenID = ?";
		String Query5 = "INSERT INTO bill(billID,bookingID,TicketPrice,TotalSeats,TotalBill) VALUES(?,?,?,?,?)";
		pstat = con.prepareStatement(Query4);
		pstat.setInt(1,Integer.valueOf(screenID));
		rs = pstat.executeQuery();
		
		while(rs.next())
			currentSeats = rs.getInt(1);
		
		pstat = con.prepareStatement(Query2);
		pstat.setString(1,title);
		rs = pstat.executeQuery();
		
		while(rs.next())
			movID = rs.getInt(1);

			pstat = con.prepareStatement(Query);
			pstat.setInt(1, (newID+1));
			pstat.setInt(2, movID);
			pstat.setInt(3, sc.getUserID());
			pstat.setInt(4, seatsBooked);
			pstat.setInt(5, Integer.valueOf(screenID));
			int rowsInserted = pstat.executeUpdate();
			if (rowsInserted > 0)
			    System.out.println(" => A new booking was inserted successfully!");
			
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("SuccessFully added booking!");
			alert.showAndWait();	
			
			//bill
			int billid = getBillID();
			pstat = con.prepareStatement(Query5);
			pstat.setInt(1, (billid+1));
			pstat.setInt(2, (newID+1));
			pstat.setInt(3, 1000);
			pstat.setInt(4, seatsBooked);
			pstat.setInt(5, 1000*seatsBooked);
			rowsInserted = pstat.executeUpdate();
			if (rowsInserted > 0)
			    System.out.println(" => A new Bill was inserted successfully!");
			
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("SuccessFully added bill!");
			alert.showAndWait();	
			
			String Query3 = "UPDATE screening SET totalSeats = ? WHERE screenID = ?";
			pstat = con.prepareStatement(Query3);
			pstat.setInt(1,(currentSeats-seatsBooked));
			pstat.setInt(2,Integer.valueOf(screenID));
			rowsInserted = pstat.executeUpdate();
			return true;
	}
	
	protected boolean removeBooking() throws SQLException {
		Alert alert;
		int newID = getBookingID();
		int seatsBooked = 0;
		int totalSeats = 0;
		int screenid = 0;
		String Query2 = "SELECT numSeats,totalSeats,screenID FROM booking INNER JOIN screening ON (screenID = screeningID) WHERE bookingID = ?";
		pstat = con.prepareStatement(Query2);
		pstat.setInt(1,newID);
		rs = pstat.executeQuery();
		
		while(rs.next()) {
			seatsBooked = rs.getInt(1);
			totalSeats = rs.getInt(2);
			screenid = rs.getInt(3);
		}
		
		String Query3 = "UPDATE screening SET totalSeats = ? WHERE screenID = ?";
		pstat = con.prepareStatement(Query3);
		pstat.setInt(1,(seatsBooked+totalSeats));
		pstat.setInt(2,screenid);
		int rowsInserted = pstat.executeUpdate();
		
		String Query = "DELETE FROM booking where bookingID = ?";
		pstat = con.prepareStatement(Query);
		pstat.setInt(1,newID);

		rowsInserted = pstat.executeUpdate();
		if (rowsInserted > 0)
			 System.out.println(" => A booking was deleted successfully!");
			
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Message");
		alert.setHeaderText(null);
		alert.setContentText("SuccessFully deleted booking!");
		alert.showAndWait();	

		return true;
	}
	
	protected int getTotalBill() throws SQLException {
		int bid = getBookingID();
		String Query = "SELECT TotalBill FROM bill WHERE bookingID = ?";
		pstat = con.prepareStatement(Query);
		pstat.setInt(1,bid);
		rs = pstat.executeQuery();
		while(rs.next()) 
			return rs.getInt(1);
		return -1;
	}
	
	protected boolean addPayment(String fname, String lname, String ceditAccNum, String cvv) throws SQLException {
		SingletonCookie sc = SingletonCookie.getInstace("", -1, "");
		boolean empty = checkEmptyFields4(cvv,ceditAccNum,lname,ceditAccNum);
		if(empty == true)
			return false;
		Alert alert;
		String fname1=null,lname1=null;
		String Query = "SELECT firstname,lastname FROM customer WHERE customerID = ?";
		pstat = con.prepareStatement(Query);
		pstat.setInt(1,sc.getUserID());
		rs = pstat.executeQuery();
		while(rs.next()) {
			fname1 = rs.getString(1);
			lname1 = rs.getString(2);
		}
		
		if(!fname1.equals(fname) || !lname1.equals(lname)) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Either Firstname Or Lastname is wrong or Both!");
			alert.showAndWait();
			return false;
		}
		
		String Query2 = "SELECT accNum,cvv,balance FROM credit WHERE customerID = ?";
		int accnum=0,rcvv=0,bal=0;
		pstat = con.prepareStatement(Query2);
		pstat.setInt(1,sc.getUserID());
		rs = pstat.executeQuery();
		while(rs.next()) {
			accnum = rs.getInt(1);
			rcvv = rs.getInt(2);
			bal = rs.getInt(3);
		}
		
		int bid = getBillID();
		int billseats = 0;
		String Query3 = "SELECT TotalBill FROM bill WHERE bookingID = ?";
		pstat = con.prepareStatement(Query3);
		pstat.setInt(1,bid);
		rs = pstat.executeQuery();
		while(rs.next()) 
			billseats = rs.getInt(1);

		if(Integer.valueOf(ceditAccNum) != accnum || Integer.valueOf(cvv) != rcvv) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Either account num Or cvv is wrong or Both!");
			alert.showAndWait();
			return false;
		}
		if(bal < billseats) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Insufficient balance");
			alert.showAndWait();
			return false;
		}
		
		String Query4 = "INSERT INTO payment(paymentID,billID,amount) VALUES(?,?,?)";
		int pid = getPaymentID();
		pstat = con.prepareStatement(Query4);
		pstat.setInt(1,(pid+1));
		pstat.setInt(2,bid);
		pstat.setInt(3,billseats);

		int rowsInserted = pstat.executeUpdate();
		if (rowsInserted > 0)
			 System.out.println(" => A payment was added successfully!");
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Message");
		alert.setHeaderText(null);
		alert.setContentText("SuccessFully made Payment!");
		alert.showAndWait();	
		
		String Query5 = "UPDATE credit SET balance = ? WHERE customerID = ?";
		pstat = con.prepareStatement(Query5);
		pstat.setInt(1,(bal-billseats));
		pstat.setInt(2,sc.getUserID());
		rowsInserted = pstat.executeUpdate();
		
	    return true;
	}
	public abstract void setHandler();
	public abstract void endConnection(); 
	public abstract void startConnection(); 
}

class mySQLDBHandler extends PersistenceHandling{

	mySQLDBHandler(){
		super();
	}
	
	public void setHandler() {  
		try{  
			//step1 load the driver class  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			System.out.println(" => Driver Loaded Successfully!");
		}
		catch(ClassNotFoundException e){ 				
			System.out.println(" => Driver Not Loaded");
		}  		  
	}
	
	public void startConnection() {  
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemadb","root","tiger12345");
			System.out.println(" => Connection Established!");
		} catch (SQLException e) {
			e.printStackTrace();
		} 		  
	}
	
	public void endConnection() {  
		try {
			con.close();
			System.out.println(" => Connection Closed!\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} 		  
	}
}

class OracleDBBHandler extends PersistenceHandling{
	public void setHandler() {
		
	}

	@Override
	public void endConnection() {  
		try {
			con.close();
			System.out.println(" => Connection Closed!\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} 		  
	}

	@Override
	public void startConnection() {
		
	}
	
}
