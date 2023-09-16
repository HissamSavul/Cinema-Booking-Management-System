package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Screening {
	private int screeningID;
	private int movID;
	private String title;
	private String date;
	private String time;
	private int totalSeats;
	
	Screening(){
		setTitle(null);
		setScreeningID(-1);
		setDate(null);
		setTime(null);
		setTotalSeats(50);
		setMovID(-1);
	}
	
	Screening(int screenid, String rdate, String rtime, int seats, String moviet, int movid) {
		this.movID = movid;
		this.title = moviet;
		this.screeningID = screenid;
		this.date = rdate;
		this.time = rtime;
		this.totalSeats = seats;
	}

	public int getMovID() {return movID;}
	public void setMovID(int movID) {this.movID = movID;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public int getScreeningID() {return screeningID;}
	public void setScreeningID(int screeningID) {this.screeningID = screeningID;}
	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}
	public String getTime() {return time;}
	public void setTime(String time) {this.time = time;}
	public int getTotalSeats() {return totalSeats;}
	public void setTotalSeats(int totalSeats) {this.totalSeats = totalSeats;}
	
	public void getScreeningDetails() {
		System.out.println(" => Movie Title: " + title);
		System.out.println(" => Movie ID: " + movID);
		System.out.println(" => Screening ID: " + screeningID);
		System.out.println(" => Date: " + date);
		System.out.println(" => Time: " + time);
		System.out.println(" => Available Seats: " + totalSeats);
	}
	
	public static void setAllScreensFromDB() throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		ArrayList<Screening> screen = new ArrayList<Screening>();
		screen = pf.getDB().getAllScreenings(screen);
		System.out.println(screen.size());
		for(int i=0 ; i<screen.size() ; i++) {
			System.out.println(" => Screen #" + (i+1));
			screen.get(i).getScreeningDetails();
		}
		screen = null;
	}
	
	public boolean addScreening( String moviet,String rdate, String rtime, int seats) throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		boolean success = pf.getDB().newScreening(moviet,rdate,rtime,seats);
		if(success == true) {
			this.movID = pf.getDB().getDesiredMovie(moviet);
			this.title = moviet;
			this.screeningID = pf.getDB().getScreenID();
			this.date = rdate;
			this.time = rtime;
			this.totalSeats = seats;
			for(int i=0 ; i<Movie.getMovies().size(); i++) {
				if(Movie.getMovies().get(i).getMovieDesc().getTitle().equals(moviet)) {
					Movie.getMovies().get(i).getScreens().add(this);
				}
			}
			
			return true;
		}	
		return false;
	}
	
	public boolean removeScreening(String screenNum,String moviet,String rdate, String rtime, int seats) throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		boolean success = pf.getDB().delScreening(screenNum,moviet,rdate,rtime,seats);
		if(success == true) {
			this.movID = -1;
			this.title = null;
			this.screeningID = -1;
			this.date = null;
			this.time = null;
			this.totalSeats = -1;
			for(int i=0 ; i<Movie.getMovies().size(); i++) {
				if(Movie.getMovies().get(i).getMovieDesc().getTitle().equals(moviet)) {
					Movie.getMovies().get(i).getScreens().remove(this);
				}
			}
			
			return true;
		}	
		return false;
	}
	
	public boolean updateScreening(String screenNum,String moviet,String rdate, String rtime, int seats) throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		boolean success = pf.getDB().updateScreening(screenNum,moviet,rdate,rtime,seats);
		if(success == true) {
			this.movID = pf.getDB().getDesiredMovie(moviet);
			this.title = moviet;
			this.screeningID = Integer.valueOf(screenNum);
			this.date = rdate;
			this.time = rtime;
			this.totalSeats = -1;
			for(int i=0 ; i<Movie.getMovies().size(); i++) {
				if(Movie.getMovies().get(i).getMovieDesc().getTitle().equals(moviet)) {
					//Movie.getMovies().get(i).getScreens().remove(this);
				}
			}
			
			return true;
		}	
		return false;
	}
	
	
}
