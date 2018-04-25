package ottoMachine;

// The user class
public class Customer {
	
	private String fName;
	private String sName;
	final private int customerID;
	final private int pin;
	
	//Basic info and pin number for logging in
	public Customer(int customerID, String fName, String lName, int pin) {
		this.customerID = customerID;
		this.fName = fName;
		this.sName = lName;
		this.pin = pin;
	}
	
	//Basic getters
	public String getFName() {
		return fName;
	}
	
	public String getSName() {
		return sName;
	}
	
	public int getID() {
		return customerID;
	}
	
	//Used to compare entered pin from GUI to customer pin (login)
	public boolean comparePin(int pinC) {
		if (pin == pinC) return true;
		else return false;
	}
	
}
