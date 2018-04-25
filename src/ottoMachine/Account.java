package ottoMachine;

import java.util.ArrayList;

//Account class for basic operations

public class Account {
	
	//Account number and the customer it links to
	final private int accNumber;
	private Customer owner;
	private double balance;
	
	//Stores list of transactions for the account, used to print out latest events
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public Account (int accNumber, Customer owner, double balance) {
		
		this.accNumber =  accNumber;
		this.owner = owner;
		this.balance = balance; //initial deposit amount
	}
	
	//Getters
	
	public double getBalance() {
		return balance;
	}
	
	public int getID() {
		return accNumber;
	}
	
	//Banking operations for transactions: withdraw, deposit and make a payment. Performs operation and adds the event to transactions ArrayList
	
	public boolean withdraw(double amount) {
		
		if (balance >= amount) {
			balance -= amount;
			Transaction act = new Transaction(this.getID(), amount, "withdraw");
			transactions.add(act);
			return true;
		}
		else return false;
		
	}
	
	public boolean deposit(double amount) {
		balance += amount;
		Transaction act = new Transaction(this.getID(), amount, "deposit");
		transactions.add(act);
		return true;
	}
	
	public boolean makePayment(String payeeFName, String payeeSName, int payeeAccNum, double amount) {
		
		if (balance >= amount) {
			balance -= amount;
			Payment pay = new Payment(this.getID(), amount, "payment", payeeFName, payeeSName, payeeAccNum);
			transactions.add(pay);
			return true;
		}
		else return false;
	}

	
	//Returns selection of latest transactions, if any
	public String recentActions() {
		String recents = "";
		if (transactions.isEmpty()) {
			return "No transactions found";
		} 
		int listsize = transactions.size();
		if (listsize < 4) {
			for (int i = 0; i <= listsize-1; ++i) {
				recents += transactions.get(i).toString();
			}
		} else {
			for (int i = 0; i < 4; ++i) {
				recents += transactions.get(listsize - i -1).toString();
			}
		}
		if (!transactions.isEmpty()) {
			return recents;
		}
		else return "";
	}
}
