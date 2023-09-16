package application;

public class MovieDescription {
	private int movieID;
	private String title;
	private String duration;
	private String genre;
	private String image;
	
	MovieDescription(){
		movieID = -1;
		title = null;
		duration = null;
		genre = null;
		setImage(null);
	}

	public int getMovieID() {return movieID;}
	public void setMovieID(int movieID) {this.movieID = movieID;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getDuration() {return duration;}
	public void setDuration(String duration) {this.duration = duration;}
	public String getGenre() {return genre;}
	public void setGenre(String genre) {this.genre = genre;}
	public String getImage() {return image;}
	public void setImage(String image) {this.image = image;}
	
	public void getMovieDescriptionSetails() {
		System.out.println(" => Movie Details!");
		System.out.println(" => Movie ID: " + movieID);
		System.out.println(" => Title: " + title);
		System.out.println(" => Duration: " + duration);
		System.out.println(" => Genre: " + genre);
		System.out.println(" => image path: " + image);
	}

}
