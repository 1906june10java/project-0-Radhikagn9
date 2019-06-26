package com.revature.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.model.Customer;
import com.revature.service.CheckBalance;
import com.revature.service.Transaction;
import com.revature.util.ConnectionUtil;
import com.revature.service.Transaction;

public class ControlHandler {
	private static final Logger Logger = org.apache.log4j.Logger.getLogger(ControlHandler.class);

	public int login(String userName, String password) {
		try (Connection connection = ConnectionUtil.getConnection()){
			PreparedStatement prepStmt = connection.prepareStatement("SELECT C_USERNAME, C_PASSWORD FROM Customer WHERE C_USERNAME=? AND C_PASSWORD=?");
			prepStmt.setString(1, userName);
			prepStmt.setString(2, password);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				Logger.info("You are signed in");
				return 1;
			}
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


	public int options(int menu) {
		int i=4;
		do {

			switch(i){
			case 1:
				System.out.println("Check Balance");
				CheckBalance balance = new CheckBalance();
				return 0;  
			case 2:
				System.out.println("Transfer");
				Transaction transaction = new Transaction();
				transaction.transferFund(ofAccNumber, amount);
				return 0;
			case 3:
				System.out.println("Deposit");
				return 0;
			default:
				System.out.println("Exit");
				return 0;
			}
			}

			public void userInput() {

				Scanner sc = new Scanner(System.in);
				System.out.println("Enter your Username: ");
				String name = sc.nextLine();
				System.out.println("Enter your Password: ");
				String password = sc.nextLine();
				login(name, password);
				System.out.println("Choose your option number: 1. Check Balance 2. Transfers 3. Deposit");
				String option = sc.nextLine();
				int menu = Integer.parseInt(option);


			}


		}







