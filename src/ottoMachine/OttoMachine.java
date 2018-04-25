package ottoMachine;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.CardLayout;
import javax.swing.JPasswordField;
import java.awt.Component;

//GUI for banking app with basic bank functions and admin section for customer management. No database implementation.
public class OttoMachine extends JFrame{
	
	//Holders for customers and their accounts
	private static ArrayList<Account> accounts = new ArrayList<Account>();
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	//The customer that is logged in and their account
	Customer loggedIn;
	Account accInUse;
	
	
	private JTextField cusIDField;
	private JPasswordField pinField;
	private JTextField fnameFld;
	private JTextField lnameFld;
	private JTextField accNumFld;
	private JTextField amountFld;
	private JTextField depAmountFld;
	private JTextField wthdAmountFld;
	private JTextField delCusNumFld;
	private JTextField fNameFld;
	private JTextField cusNumFld;
	private JTextField iniDepFld;
	private JTextField lNameFld;
	private JTextField pinFld;
	private JTextField bankAccFld;
	
	public OttoMachine() {
		
		super("Banking");
		setResizable(false);
		setMinimumSize(new Dimension(498, 346));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		//Tabbed panel which contains tabs for the different functionalities
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 492, 317);
		getContentPane().add(tabbedPane);
		
		//GUI login tab items
		JPanel loginPan = new JPanel();
		tabbedPane.addTab("Log in", null, loginPan, null);
		tabbedPane.setEnabledAt(0, true);
		loginPan.setLayout(null);
		
		JLabel cusIDLabel = new JLabel("Customer ID:");
		cusIDLabel.setBounds(21, 14, 75, 14);
		loginPan.add(cusIDLabel);
		
		cusIDField = new JTextField();
		cusIDField.setBounds(96, 11, 104, 20);
		loginPan.add(cusIDField);
		cusIDField.setColumns(10);
		
		JLabel pinLabel = new JLabel("PIN:");
		pinLabel.setBounds(21, 42, 46, 14);
		loginPan.add(pinLabel);
		
		pinField = new JPasswordField();
		pinField.setBounds(96, 39, 86, 20);
		loginPan.add(pinField);
		pinField.setColumns(10);
		
		//Action listener for logging in
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Calls login method, if successful switches to account tab
				if (logIn() == true) {
					tabbedPane.setEnabledAt(1, true);
					tabbedPane.setSelectedIndex(1);
					tabbedPane.setEnabledAt(0, false);
				}	
			}
		});
		btnLogIn.setBounds(93, 70, 89, 23);
		loginPan.add(btnLogIn);
		
		//Button to enter administration section, enables admin tab and switches to it
		JButton btnAdministration = new JButton("Administration");
		btnAdministration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(5, true);
				tabbedPane.setSelectedIndex(5);
			}
		});
		btnAdministration.setBounds(10, 237, 124, 23);
		loginPan.add(btnAdministration);
		
		//GUI account tab items
		JPanel accPan = new JPanel();
		tabbedPane.addTab("Account", null, accPan, null);
		tabbedPane.setEnabledAt(1, false);
		accPan.setLayout(null);
		
		JLabel accountMainLabel = new JLabel("Account options");
		accountMainLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		accountMainLabel.setBounds(187, 11, 117, 14);
		accPan.add(accountMainLabel);
		
		//Withdraw button enables and switches to withdraw tab
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(2, true);
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnWithdraw.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnWithdraw.setBounds(10, 207, 125, 32);
		accPan.add(btnWithdraw);
		
		//Deposit button enables and switches to deposit tab
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(3, true);
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnDeposit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeposit.setBounds(157, 207, 125, 32);
		accPan.add(btnDeposit);
		
		//New payment button enables and switches to payment tab
		JButton btnMakeNewPayment = new JButton("Make new payment");
		btnMakeNewPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(4, true);
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnMakeNewPayment.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMakeNewPayment.setBounds(304, 207, 157, 32);
		accPan.add(btnMakeNewPayment);
		
		//View balance opens pop up dialog with account balance
		JButton btnViewBalance = new JButton("View balance");
		btnViewBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, 
						"Account balance: " + accInUse.getBalance() + " euros.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnViewBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewBalance.setBounds(10, 127, 125, 32);
		accPan.add(btnViewBalance);
		
		//View account details opens pop up dialog wit basic account information
		JButton btnViewAccountDetails = new JButton("View account information");
		btnViewAccountDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						"Name: " + loggedIn.getFName() + " " + loggedIn.getSName() + "\nAccount number: " + accInUse.getID(), 
						"Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnViewAccountDetails.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewAccountDetails.setBounds(10, 60, 186, 32);
		accPan.add(btnViewAccountDetails);
		
		//Recent transactions opens pop up dialog with recent account events
		JButton btnRecentTransactions = new JButton("Recent transactions");
		btnRecentTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, accInUse.recentActions(), "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnRecentTransactions.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRecentTransactions.setBounds(234, 60, 157, 32);
		accPan.add(btnRecentTransactions);
		
		//GUI withdraw tab items
		JPanel withdrawPan = new JPanel();
		tabbedPane.addTab("Withdraw", null, withdrawPan, null);
		withdrawPan.setLayout(null);
		
		JLabel wthdAmountLbl = new JLabel("Amount to withdraw:");
		wthdAmountLbl.setBounds(35, 69, 131, 14);
		withdrawPan.add(wthdAmountLbl);
		
		wthdAmountFld = new JTextField();
		wthdAmountFld.setColumns(10);
		wthdAmountFld.setBounds(163, 66, 100, 20);
		withdrawPan.add(wthdAmountFld);
		
		//Withdraw button
		JButton okBtn = new JButton("OK");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Calls Account withdraw method, if it was successful displays pop up dialog with withdraw info and new balance
				//Failures also show pop up dialog with either no sufficient funds or error message
				//Finally returns back to account tab and disables withdraw tab
				try {
					double num = Double.parseDouble(wthdAmountFld.getText());
					if (accInUse.withdraw(num) == true) {
						JOptionPane.showMessageDialog(null, num + " euros was withdrawn from your account. Balance is now " + accInUse.getBalance(), "Info", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Your account doesn't have enough balance", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Error, input numbers. Try again.", "Info", JOptionPane.INFORMATION_MESSAGE);
				} finally {
					tabbedPane.setSelectedIndex(1);
					tabbedPane.setEnabledAt(2, false);
				}
			}
		});
		okBtn.setBounds(163, 124, 89, 23);
		withdrawPan.add(okBtn);
		tabbedPane.setEnabledAt(2, false);
		
		//GUI deposit tab items
		JPanel depositPan = new JPanel();
		tabbedPane.addTab("Deposit", null, depositPan, null);
		depositPan.setLayout(null);
		
		JLabel depAmountLbl = new JLabel("Amount to deposit:");
		depAmountLbl.setBounds(37, 69, 131, 14);
		depositPan.add(depAmountLbl);
		
		depAmountFld = new JTextField();
		depAmountFld.setBounds(160, 66, 100, 20);
		depositPan.add(depAmountFld);
		depAmountFld.setColumns(10);
		
		//Deposit button
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Calls Account deposit method, if it was successful displays pop up dialog with deposit info and new balance
				//Failure shows error message
				//Finally returns back to account tab and disables deposit tab
				try {
					double num = Double.parseDouble(depAmountFld.getText());
					if (accInUse.deposit(num) == true) {
						JOptionPane.showMessageDialog(null, num + " euros was deposited to your account. Balance is now " + accInUse.getBalance(), "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Error, input numbers. Try again.", "Info", JOptionPane.INFORMATION_MESSAGE);
				} finally {
					tabbedPane.setSelectedIndex(1);
					tabbedPane.setEnabledAt(3, false);
				}
			}
		});
		btnOk.setBounds(160, 124, 89, 23);
		depositPan.add(btnOk);
		tabbedPane.setEnabledAt(3, false);
		
		//GUI payment tab items
		JPanel payPan = new JPanel();
		tabbedPane.addTab("Payment", null, payPan, null);
		tabbedPane.setEnabledAt(4, false);
		payPan.setLayout(null);
		
		JLabel fNameLbl = new JLabel("First name:");
		fNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fNameLbl.setBounds(10, 52, 110, 14);
		payPan.add(fNameLbl);
		
		JLabel paymentMainLbl = new JLabel("New payment");
		paymentMainLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymentMainLbl.setBounds(189, 11, 110, 14);
		payPan.add(paymentMainLbl);
		
		JLabel lastnameLbl = new JLabel("Lastname:");
		lastnameLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lastnameLbl.setBounds(10, 80, 110, 14);
		payPan.add(lastnameLbl);
		
		JLabel accNumLbl = new JLabel("Account number:");
		accNumLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		accNumLbl.setBounds(10, 132, 110, 14);
		payPan.add(accNumLbl);
		
		JLabel amountLblPay = new JLabel("Amount:");
		amountLblPay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		amountLblPay.setBounds(10, 168, 110, 14);
		payPan.add(amountLblPay);
		
		fnameFld = new JTextField();
		fnameFld.setBounds(146, 50, 206, 20);
		payPan.add(fnameFld);
		fnameFld.setColumns(10);
		
		lnameFld = new JTextField();
		lnameFld.setColumns(10);
		lnameFld.setBounds(146, 81, 206, 20);
		payPan.add(lnameFld);
		
		accNumFld = new JTextField();
		accNumFld.setColumns(10);
		accNumFld.setBounds(146, 130, 206, 20);
		payPan.add(accNumFld);
		
		amountFld = new JTextField();
		amountFld.setColumns(10);
		amountFld.setBounds(146, 166, 206, 20);
		payPan.add(amountFld);
		
		//Make payment button
		JButton btnMakePayment = new JButton("Make payment");
		btnMakePayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Checks if payee info is present and account has sufficient funds. If yes, displays payment info and new balance
				//Displays for failures and exceptions
				//Finally returns to account tab and disables payment tab
				try {
					String payeeF = fnameFld.getText();
					String payeeL = lnameFld.getText();
					int payeeAcc = Integer.parseInt(accNumFld.getText());
					double num = Double.parseDouble(amountFld.getText());
					if (payeeF != "" && payeeL != "" && accInUse.makePayment(payeeF, payeeL, payeeAcc, num) == true) {
						JOptionPane.showMessageDialog(null, 
								num + " euros was paid to: " + payeeF + " " + payeeL +  ". Balance is now " + accInUse.getBalance(), 
								"Info", JOptionPane.INFORMATION_MESSAGE);
					} else JOptionPane.showMessageDialog(null, "Incorrect input, try again.", "Info", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Error, try again.", "Info", JOptionPane.INFORMATION_MESSAGE);
				} finally {
					tabbedPane.setSelectedIndex(1);
					tabbedPane.setEnabledAt(4, false);
				}
			}
		});
		btnMakePayment.setBounds(189, 223, 130, 23);
		payPan.add(btnMakePayment);
		
		//GUI admin tab items
		JPanel adminPanel = new JPanel();
		tabbedPane.addTab("Admin", null, adminPanel, null);
		tabbedPane.setEnabledAt(5, false);
		adminPanel.setLayout(null);
		
		JLabel lblCreateNewCustomer = new JLabel("Create new customer and account");
		lblCreateNewCustomer.setBounds(10, 11, 209, 14);
		adminPanel.add(lblCreateNewCustomer);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setBounds(10, 36, 71, 14);
		adminPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(241, 36, 71, 14);
		adminPanel.add(lblLastName);
		
		JLabel lblSetCustomerNumber = new JLabel("Set customer number");
		lblSetCustomerNumber.setBounds(10, 61, 127, 14);
		adminPanel.add(lblSetCustomerNumber);
		
		JLabel lblSetPin = new JLabel("Set pin");
		lblSetPin.setBounds(241, 61, 46, 14);
		adminPanel.add(lblSetPin);
		
		JLabel lblInitialDeposit = new JLabel("Initial deposit");
		lblInitialDeposit.setBounds(10, 86, 79, 14);
		adminPanel.add(lblInitialDeposit);
		
		JLabel lblBankAccount = new JLabel("Bank account");
		lblBankAccount.setBounds(241, 86, 90, 14);
		adminPanel.add(lblBankAccount);
		
		JLabel lblDeleteCustomer = new JLabel("Delete customer");
		lblDeleteCustomer.setBounds(10, 161, 108, 14);
		adminPanel.add(lblDeleteCustomer);
		
		JLabel lblCustomerNumber = new JLabel("Customer number");
		lblCustomerNumber.setBounds(10, 186, 108, 14);
		adminPanel.add(lblCustomerNumber);
		
		fNameFld = new JTextField();
		fNameFld.setBounds(75, 33, 152, 20);
		adminPanel.add(fNameFld);
		fNameFld.setColumns(10);
		
		lNameFld = new JTextField();
		lNameFld.setBounds(312, 33, 165, 20);
		adminPanel.add(lNameFld);
		lNameFld.setColumns(10);
		
		cusNumFld = new JTextField();
		cusNumFld.setBounds(138, 58, 90, 20);
		adminPanel.add(cusNumFld);
		cusNumFld.setColumns(10);
		
		pinFld = new JTextField();
		pinFld.setBounds(312, 58, 90, 20);
		adminPanel.add(pinFld);
		pinFld.setColumns(10);
		
		iniDepFld = new JTextField();
		iniDepFld.setBounds(91, 83, 136, 20);
		adminPanel.add(iniDepFld);
		iniDepFld.setColumns(10);
		
		bankAccFld = new JTextField();
		bankAccFld.setBounds(341, 83, 136, 20);
		adminPanel.add(bankAccFld);
		bankAccFld.setColumns(10);
		
		delCusNumFld = new JTextField();
		delCusNumFld.setBounds(120, 183, 108, 20);
		adminPanel.add(delCusNumFld);
		delCusNumFld.setColumns(10);
		
		//Create new customer button
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Gets customer and account info from fields and attempts to create new customer and account to their respective lists
				//Displays success or exception dialogs
				try {
					int customerID = Integer.parseInt(cusNumFld.getText());
					String fName = fNameFld.getText();
					String lName = lNameFld.getText();
					int pin = Integer.parseInt(pinFld.getText());
					int iniDeposit = Integer.parseInt(iniDepFld.getText());
					int accID = Integer.parseInt(bankAccFld.getText());
					Customer create = new Customer(customerID, fName, lName, pin);
					customers.add(create);
					Account newAcc = new Account(accID, create, iniDeposit);
					accounts.add(newAcc);
					JOptionPane.showMessageDialog(null, "New customer and account created", "Info", JOptionPane.INFORMATION_MESSAGE);
					tabbedPane.setSelectedIndex(0);
					tabbedPane.setEnabledAt(5, false);	
					System.out.println(customers.get(2).getFName());
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Error, try again.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnCreate.setBounds(10, 114, 89, 23);
		adminPanel.add(btnCreate);
		
		//Delete customer button
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Takes customer number as input and attempts to delete the customer and their account
				////Displays success or exception dialogs
				try {
					int customerID = Integer.parseInt(delCusNumFld.getText());
					int indx = customers.indexOf(customerID);	
					customers.remove(indx+1);
					int accIndx = accounts.indexOf(indx+1);
					accounts.remove(accIndx+1);
					JOptionPane.showMessageDialog(null, "Customer and account removed", "Info", JOptionPane.INFORMATION_MESSAGE);
					tabbedPane.setSelectedIndex(0);
					tabbedPane.setEnabledAt(5, false);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Error, try again.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnDelete.setBounds(10, 224, 89, 23);
		adminPanel.add(btnDelete);
				
	}
	
	//Performs log in. Searches customers list for given customer number, if found, makes comparison of given pin to customer pin.
	//Sets the current user and account to logged in and enables and switches to account tab.
	//Login failures and exceptions notified with pop up dialog
	public boolean logIn() {
		try {
			int customerID = Integer.parseInt(cusIDField.getText());
			for (int i = 0; i < customers.size(); ++i) {
				int compare = customers.get(i).getID();
				if (compare == customerID) {
					Customer cus = customers.get(i);
					Account acc = accounts.get(i);
					int indx = customers.indexOf(cus);
					int accIndx = accounts.indexOf(acc);
					loggedIn = customers.get(indx);
					accInUse = accounts.get(accIndx);
				}
			}
			System.out.println(accounts.indexOf(loggedIn));	
			int pin = Integer.parseInt(pinField.getText());
			if (loggedIn.comparePin(pin) == true) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect details, try again.", "Info", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Login failed, try again.", "Info", JOptionPane.INFORMATION_MESSAGE);
			exception.printStackTrace();
			return false;
		} 
	}
			
	public static void main(String args[]) {
		// example accounts and customers
		Customer testCOne = new Customer(1234, "Darth", "Vader", 1234);
		customers.add(testCOne);
		Customer testCTwo = new Customer(5678, "Luke", "Skywalker", 5678);
		customers.add(testCTwo);		
		Account testAOne = new Account(4321, testCOne, 1000);
		accounts.add(testAOne);
		Account testATwo = new Account(8765, testCTwo, 500);
		accounts.add(testATwo);
		
		OttoMachine frame = new OttoMachine();
		frame.setVisible(true);
	}
}

	