package ottoMachine;

//Inherits from Transaction, added information for recipient of payment
public class Payment extends Transaction {
	
	//Recipient name and account number
	private String payeeFName;
	private String payeeSName;
	private int payeeAccNum;
	
	public Payment (int accID, double amount, String type, String payeeFName, String payeeSName, int payeeAccNum) {
		super(accID, amount, type);
		this.payeeFName = payeeFName;
		this.payeeSName = payeeSName;
		this.payeeAccNum = payeeAccNum;
	}
	
	//Adds payee name to transaction summary
	public String toString() {
		return super.toString() + " Payee: " + payeeFName + " " + payeeSName;
	}
}
