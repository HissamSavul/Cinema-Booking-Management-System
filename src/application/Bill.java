package application;

public class Bill {
	private Booking booking;
	private int amount;
	
	Bill(){
		setBooking(null);
		setAmount(-1);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	public void getBillDetails() {
		System.out.println(" => Bill Details!");
		System.out.println(" => Customer Name: " + booking.getCustomer().getFname());
		System.out.println(" => Movie Name: " + booking.getScreening().getTitle());
		System.out.println(" => Amount ID: " + booking.getBookedTicketsQuantity()*1000);
	}
	
}
