package bankApp;

import java.io.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){ 
		
		//A project by Marco Rivelli.
		
		ArrayList<BankAccount> accounts=new ArrayList<BankAccount>();
		
		BankAccount n5578= new BankAccount(5578,"9995",0);
		BankAccount n5737= new BankAccount(5737,"5896",100);
		BankAccount n8690= new BankAccount(8690,"7696",2000);
		BankAccount n2544= new BankAccount(2544,"7682",6000000);
		
		accounts.add(n8690);
		accounts.add(n5737);
		accounts.add(n5578);
		accounts.add(n2544);
		
		
		try {  //Load file saved                                        //INSERT FILE SOURCE
			FileInputStream fileIn=new FileInputStream("C:\\Users\\Marco\\Desktop\\eclipse-workspace\\bankApp\\bankFile");
			ObjectInputStream in= new ObjectInputStream(fileIn);
			accounts=(ArrayList<BankAccount>) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e1) {
			System.out.println("ERROR: FILE TO LOAD NOT FOUND");
			e1.printStackTrace();
		}
		
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Welcome to JavaBank!\n1 = Log in\n2 = Create new JavaBank account"); 
		
		int option=scan.nextInt();
		@SuppressWarnings("unused") String consumeN=scan.nextLine();
		
		switch(option) {
		case 1: //Log in
		
		System.out.println("Insert card number:");
		int card=scan.nextInt();
		
		for(int i=0;i<accounts.size();i++) {
			if(accounts.get(i).getId()==card) {
				System.out.println("********************");
				System.out.println("Select an action:");
				System.out.println("1 = Show balance");
				System.out.println("2 = Deposit");
				System.out.println("3 = Withdraw");
				System.out.println("4 = Change PIN");
				System.out.println("5 = Exit");
				
				int action=scan.nextInt();
				
				switch(action) {
				case 1: accounts.get(i).showBalance();
				    break;
				case 2: accounts.get(i).deposit();
				    break;
				case 3: accounts.get(i).withdraw();
				    break;
				case 4:accounts.get(i).setPin(accounts.get(i).changePin());
					break;
				}
			}
		}
		break;
		
		case 2: //Generate a new account
		 Random random=new Random();
		 int newCard=random.nextInt(9999999)+10000;
		 
			for(int i=0;i<accounts.size();i++) {
				
				while(accounts.get(i).getId()==newCard) {
					newCard=random.nextInt(9999999)+10000;
				}
			 }
			
			System.out.println("********************");
			System.out.println("Create a new PIN (4 characters):");
			String newPin=scan.nextLine();
			
			while(newPin.length()!=4) {
					System.out.println("Create a valid PIN (4 characters):"); 
					  newPin=scan.nextLine();
			}
			    
			System.out.println("Repeat your PIN to confirm:");
			String confirmPin=scan.nextLine();
			while(!confirmPin.equals(newPin)) {
						System.out.println("PIN is wrong, repeat your PIN to confirm:");
						confirmPin=scan.nextLine();
			}
			 
			BankAccount newAccount= new BankAccount(newCard,newPin,0);
			accounts.add(newAccount);
			System.out.println("New JavaBank account succesfully created! \nYour new card number is: "+newCard);
			break;
		}
		
		
        
	    try { //Save file
	    	FileOutputStream fileOut=new FileOutputStream("bankFile");      //INSERT FILE NAME
			ObjectOutputStream out=new ObjectOutputStream(fileOut);
		    out.writeObject(accounts);
		    out.close();
			fileOut.close();
		} catch (IOException e) {
			System.out.println("ERROR: FILE NOT SAVED");
			e.printStackTrace();
		}
		
		
	    scan.close();
		System.exit(0);
		}

	
	
	}



