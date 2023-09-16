package application;

public class Credit {
	private int accNum;
	private int balance;
	private int cvv;
	
	Credit(){
		setAccNum(-1);
		setBalance(0);
		setCvv(-1);
	}

	public int getAccNum() {return accNum;}
	public void setAccNum(int accNum) {this.accNum = accNum;}
	public int getBalance() {return balance;}
	public void setBalance(int balance) {this.balance = balance;}
	public int getCvv() {return cvv;}
	public void setCvv(int cvv) {this.cvv = cvv;}
	
	public void getCreditDetails() {
		System.out.println(" => Credit Details!");
		System.out.println(" => Account Number: " + accNum);
		System.out.println(" => Balance: " + balance);
		System.out.println(" => CVV: " + cvv);
	}
	
	public void updateCredit(int bal) {
		balance -= bal;
	}
}
