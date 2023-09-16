package application;
import java.sql.SQLException;
import java.util.ArrayList;

public class Movie {
	private static ArrayList<Movie> movies = new ArrayList<Movie>();
	private MovieDescription movieDesc;
	private  ArrayList<Screening> screens = new ArrayList<Screening>();
	
	Movie(){
		movieDesc = null;
	}
	
	Movie(MovieDescription desc, int id, String title, String duration, String genre, String path){
		desc = new MovieDescription();
		desc.setMovieID(id);
		desc.setTitle(title);
		desc.setDuration(duration);
		desc.setGenre(genre);
		desc.setImage(path);
		setMovieDesc(desc);
	}
	
	public Screening addScreenToMovie(Screening screen, int screenid, int movieid, String title, String date, String time, int tseats){
		screen = new Screening();
		screen.setScreeningID(screenid);
		screen.setMovID(movieid);
		screen.setTitle(title);
		screen.setDate(date);
		screen.setTime(time);
		screen.setTotalSeats(tseats);
		for(int i=0 ; i<movies.size();i++) {
			if(movies.get(i).getMovieDesc().getMovieID() == movieid) {
				movies.get(i).getScreens().add(screen);
			}
		}
		return screen;
	}
	
	public  ArrayList<Screening> getScreens() {return screens;}
	public  void setScreens(ArrayList<Screening> screens) {this.screens = screens;}
	public MovieDescription getMovieDesc() {return movieDesc;}
	public void setMovieDesc(MovieDescription movieDesc) {this.movieDesc = movieDesc;}
	public static ArrayList<Movie> getMovies() {return movies;}
	public static void setMovies(ArrayList<Movie> movies) {Movie.movies = movies;}

	public static void setAllMoviesFromDB() throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		ArrayList<Movie> movie = new ArrayList<Movie>();
		movie = pf.getDB().getAllMovies(movie);
		System.out.println(movie.size());
		for(int i=0 ; i<movie.size() ; i++) {
			System.out.println(" => Movie #" + (i+1));
			System.out.println(" => Movie ID: " + movie.get(i).getMovieDesc().getMovieID());
			System.out.println(" => Movie Title: " + movie.get(i).getMovieDesc().getTitle());
			System.out.println(" => Movie Genre: " + movie.get(i).getMovieDesc().getGenre());
			System.out.println(" => Movie Duration: " + movie.get(i).getMovieDesc().getDuration());
			System.out.println(" => Movie Image: " + movie.get(i).getMovieDesc().getImage());
			
		}
		movie = null;
	}
	
	static int a = 0;
	public static void setAllMoviesScreensFromDB() throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		ArrayList<Movie> movie = new ArrayList<Movie>();
		ArrayList<Screening> screen = new ArrayList<Screening>();
		movie = pf.getDB().getAllMovies(movie);
		screen = pf.getDB().getAllScreenings(screen);
		System.out.println(movie.size());
		for(int i=0 ; i<movie.size() ; i++) {
			System.out.println(" => Movie #" + (i+1));
			System.out.println(" => Movie ID: " + movie.get(i).getMovieDesc().getMovieID());
			System.out.println(" => Movie Title: " + movie.get(i).getMovieDesc().getTitle());
			System.out.println(" => Movie Genre: " + movie.get(i).getMovieDesc().getGenre());
			System.out.println(" => Movie Duration: " + movie.get(i).getMovieDesc().getDuration());
			System.out.println(" => Movie Image: " + movie.get(i).getMovieDesc().getImage());
			for(int j=0 ; j<screen.size();j++) {
				if(movie.get(i).getMovieDesc().getTitle().equals(screen.get(j).getTitle())) {
					System.out.println(" => Screen #" + (j+1));
					movie.get(i).setScreens(screen);
					movie.get(i).getScreens().get(j).getScreeningDetails();
				}
			}
		}
		if(a++ != 0) 
			movies = movie;		
		movie = null;
	}
	
	public void getMovieDetails() {
		for(int i=0 ; i<movies.size() ; i++) {
			System.out.println(" => Movie #" + (i+1));
			System.out.println(" => Movie ID: " + movies.get(i).getMovieDesc().getMovieID());
			System.out.println(" => Movie Title: " + movies.get(i).getMovieDesc().getTitle());
			System.out.println(" => Movie Genre: " + movies.get(i).getMovieDesc().getGenre());
			System.out.println(" => Movie Duration: " + movies.get(i).getMovieDesc().getDuration());
			System.out.println(" => Movie Image: " + movies.get(i).getMovieDesc().getImage());

		}
	}
	
	//manager
	public boolean addMovie(String title, String duration, String genre, String image) throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		boolean success = pf.getDB().newMovie(pf.getDB().getUserID(),title,duration,genre,image);
			if(success == true) {
				movieDesc  = new MovieDescription();
		    	this.movieDesc.setMovieID(pf.getDB().getMovieID());
		    	this.movieDesc.setTitle(title);
		    	this.movieDesc.setDuration(duration);
		    	this.movieDesc.setGenre(genre);
		    	this.movieDesc.setImage(image);
		    	movies.add(this);
		    	getMovieDetails();
				System.out.println(" => New Movie " + this.movieDesc.getTitle()  + " has been added!");
				return true;
			}
		return false;
	}
	
	public boolean removeMovie(int movID,String title, String duration, String genre, String image) throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		boolean success = pf.getDB().delMovie(movID,title,duration,genre,image);
			if(success == true) {
				for(int i=0; i<movies.size();i++) {
					if(movies.get(i).getMovieDesc().getMovieID() == movID) {
						System.out.println(" => Movie " + movies.get(i).getMovieDesc().getTitle() + " has been deleted!");
						movies.get(i).getMovieDesc().setMovieID(-1);
						movies.get(i).getMovieDesc().setTitle("");
						movies.get(i).getMovieDesc().setDuration("");
						movies.get(i).getMovieDesc().setGenre("");
						movies.get(i).getMovieDesc().setImage("");
						movies.get(i).setMovieDesc(null);
				    	movies.remove(movies.get(i));
				    	getMovieDetails();
					}
				}
				return true;
			}
		return false;
	}
	public boolean updateMovie(int movID,String title, String duration, String genre, String image) throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		boolean success = pf.getDB().updateMovie(movID,title,duration,genre,image);
			if(success == true) {
				for(int i=0; i<movies.size();i++) {
					if(movies.get(i).getMovieDesc().getMovieID() == movID) {
						System.out.println(" => Movie " + movies.get(i).getMovieDesc().getTitle() + " has been updated!");
						movies.get(i).getMovieDesc().setMovieID(movID);
						movies.get(i).getMovieDesc().setTitle(title);
						movies.get(i).getMovieDesc().setDuration(duration);
						movies.get(i).getMovieDesc().setGenre(genre);
						movies.get(i).getMovieDesc().setImage(image);
				    	getMovieDetails();
					}
				}
				return true;
			}
		return false;
	}

	public ArrayList<Movie> getMovie() throws SQLException {
		PersistenceFactory pf = PersistenceFactory.getInstace(null);
		ArrayList<Movie> movieList = null;
		movieList = pf.getDB().getAllMovies(movieList);
		return movieList;
	}
}
