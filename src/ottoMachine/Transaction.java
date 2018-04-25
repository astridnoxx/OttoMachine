package ottoMachine;

//Basic form for transactions

public class Transaction {
	
	//The account of the transaction, amount and transaction type
	private int accID;
	double amount;
	String type;
	
	public Transaction (int accID, double amount, String type) {
		this.accID = accID;
		this.amount = amount;
		this.type = type;
	}
	
	//Return basic details of transaction for the recent actions in Account
	public String toString() {
		return "\nTransaction type: " +  type + ". Amount: " + amount;
	}
	
}
