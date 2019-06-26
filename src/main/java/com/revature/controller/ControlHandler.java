package com.revature.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.service.CheckBalance;
import com.revature.service.Transaction;
import com.revature.util.ConnectionUtil;

public class ControlHandler {
	private static final Logger Logger = org.apache.log4j.Logger.getLogger(ControlHandler.class);

	public int login(String userName, String password) {
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement("SELECT C_USERNAME, C_PASSWORD, C_ACCNO FROM Customer WHERE C_USERNAME=? AND C_PASSWORD=?");
			prepStmt.setString(1, userName);

			prepStmt.setString(2, password);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				Logger.info("You are signed in");
				return Integer.parseInt(rs.getObject("C_ACCNO").toString());
			}
			else Logger.error("Invalid Username/Password.");
		} catch (SQLException e) {
			Logger.error("Invalid Username/Password.",e);
		}
		return 0;
	}


	private boolean validateUsername(String username) {
		return username.length() > 3;
	}

	private boolean validatePass(String pass) {
		return pass.length() >= 10 & pass.matches(".*\\d+.*");
	}


	public int options(int senderAccNo, int menu) {
		Scanner sc = new Scanner(System.in);

		switch(menu){
		case 1:
			System.out.println("Check Balance");
			CheckBalance balance = new CheckBalance();
		//	balance.showBalance(accno, balance);
			System.out.println("Balance: ");
			return 0;  
		case 2:
			System.out.println("Welcome to Transfers");
			System.out.print("Please enter the phone number of the recipient:");
			String phone = sc.nextLine();
			try {
				Connection connection = ConnectionUtil.getConnection();
				PreparedStatement prepStmt = connection.prepareStatement("SELECT C_ACCNO FROM Customer WHERE C_PHONENUMBER=?");
				prepStmt.setString(1, phone);
				ResultSet rs = prepStmt.executeQuery();
				if(rs.next()) {
					System.out.println("Valid user");
					int recieverAccno = rs.getInt("C_ACCNO");
					System.out.println("Please enter the amount to be transferred:");
					float amt = sc.nextFloat();
					Transaction transaction = new Transaction();
					//transaction.transferFund(int F_accno,int F_amount, int T_amount, Float T_bal);
					
					
					//	System.out.println(transaction.transferFund(senderAccNo, amt, recieverAccno));
					//} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					//}
				
				else Logger.error("Specified User does not exist");

			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return 0;
		case 3:
			System.out.println("Deposit");
			return 0;
		default:
			System.out.println("Exit");
			System.out.println("You are signed out");
			return 0;
		}
	}


	public void userInput() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Username: ");
		String name = sc.nextLine();
		System.out.println("Enter your Password: ");
		String password = sc.nextLine();
		int accountNumber = login(name, password);
		if(accountNumber!=0) {
			System.out.println(accountNumber);
			System.out.println("Choose your option number: 1. Check Balance 2. Transfers 3. Deposit");
			String option = sc.nextLine();
			int menu = Integer.parseInt(option);
			options(accountNumber,menu);
		}
		
		

	}


}







