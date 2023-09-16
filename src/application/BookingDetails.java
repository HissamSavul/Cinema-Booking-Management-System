package application;

public class BookingDetails {
	private Payment payment;
	private int bookingID;
	private String name;
	private String title;
	private int numTickets;
	private String dateTime;
	private int amount;
	
	BookingDetails(){
		bookingID = -1;
		name = null;
		title = null;
		numTickets = -1;
		dateTime = null;
		amount = -1;
	}

	public Payment getPayment() {return payment;}
	public void setPayment(Payment payment) {this.payment = payment;}
	public int getBookingID() {return bookingID;}
	public void setBookingID(int bookingID) {this.bookingID = bookingID;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public int getNumTickets() {return numTickets;}
	public void setNumTickets(int numTickets) {this.numTickets = numTickets;}
	public String getDateTime() {return dateTime;}
	public void setDateTime(String dateTime) {this.dateTime = dateTime;}
	public int getAmount() {return amount;}
	public void setAmount(int amount) {this.amount = amount;}
	
	public void getBookingDetails() {
		System.out.println(" => Booking Details!");
		System.out.println(" => Booking ID: " + bookingID);
		System.out.println(" => Customer Name: " + name);
		System.out.println(" => Booked Movie: " + title);
		System.out.println(" => Booked Tickets Quantity: " + numTickets);
		System.out.println(" => Booking Date+Time: " + dateTime);
		System.out.println(" => Booking Amount: " + amount);
	}
}
