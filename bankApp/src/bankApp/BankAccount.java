package bankApp;

import java.io.Serializable;
import java.util.Scanner;

public class BankAccount implements Serializable{

	private static final long serialVersionUID = -2725924008688424495L;
	private final int id;
	private String pin;
	private double balance;
	
	public BankAccount(int id, String pin, double balance) {
		super();
	    this.id=id;
		this.pin = pin;
		this.balance=balance;
		
	}

	public int getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}
	
	public void showBalance() {
		insertPin();
		System.out.println("********************");
		System.out.println("Current balance: "+this.getBalance()+"$");
	}
	
	public void deposit() {
		insertPin();
		Scanner v= new Scanner(System.in);
		System.out.println("********************");
		System.out.println("Insert amount to deposit:");
		
		int d=v.nextInt();
		v.close();
		balance=this.getBalance()+d;
		System.out.println("Deposit successful.");
	}
	public void withdraw() {
		insertPin();
		Scanner s= new Scanner(System.in);
		System.out.println("********************");
		System.out.println("Insert amount to withdraw:");
		
			int w=s.nextInt();
			if(w>2000) {
				System.out.println("Maximum withdraw amount cannot be over: 2000$");
				withdraw();
			}else if(w>this.getBalance()) {
				System.out.println("Balance not sufficient for this amount.");
				withdraw();
			}
			
			balance=this.getBalance()-w;
			System.out.println("Withdraw successful.");
			
		s.close();
	}
	
	public void insertPin() {
		Scanner scan= new Scanner(System.in);
		System.out.println("********************");
		System.out.println("Insert pin:");
		String p=scan.nextLine();
		int tryCount=0;
		
		for(int i=0;i<3;i++){
			if(p.equals(pin)) {
				System.out.println("PIN: OK");
				break;
			}else {
				tryCount++;
				if(tryCount>=3){
					System.out.println("ACCESS DENIED");
					scan.close();
				System.exit(0);
				}
				System.out.println("PLEASE TRY AGAIN: ("+(3-tryCount)+" attempt"+((3-tryCount)<=1 ? " ":"s ")+"left)");
				p=scan.nextLine();
			}
		}
	}

	public String changePin() {
		insertPin();
		Scanner scan=new Scanner(System.in);
		System.out.println("********************");
		System.out.println("Create a new PIN (4 characters):");
		String nPin=scan.nextLine();
		
		while(nPin.length()!=4) {
				System.out.println("Create a valid PIN (4 characters):"); 
				  nPin=scan.nextLine();
		}
		    
		System.out.println("Repeat new PIN to confirm:");
		String confirmPin=scan.nextLine();
		while(!confirmPin.equals(nPin)) {
					System.out.println("PIN is wrong, repeat new PIN to confirm:");
					confirmPin=scan.nextLine();
		}
		System.out.println("New PIN confirmed.");
		scan.close();
		return nPin;
	}
	

    public void setPin(String pin) {
		this.pin = pin;
	}


	
}
