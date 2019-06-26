package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.controller.ControlHandler;
import com.revature.util.ConnectionUtil;

public class CheckBalance {

	private static final Logger Logger = org.apache.log4j.Logger.getLogger(ControlHandler.class);

	public float showBalance(int accno, float balance) throws ClassNotFoundException, SQLException {

		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement("SELECT C_BALANCE FROM CUSTOMER WHERE C_ACCNO=?");
			prepStmt.setInt(1,accno); 
			prepStmt.setFloat(2,balance);
			ResultSet res = prepStmt.executeQuery();
			Float current_balance = res.getFloat(2);
			//System.out.println(res.next());
			if(res.next()) {
				Logger.info("Current Balance is:" + current_balance);
				float currentBalance = res.getBigDecimal("C_BALANCE").floatValue();
				System.out.println(currentBalance);
				//while (resultSet.next())
				//System.out.println(table + " " + accno + " " + resultSet.getInt(1));
				return currentBalance; //2349871290
			}
			else return -1f;
		}   catch (SQLException e) {
			Logger.error("Cannot select.",e);		
		}
		return -2f;
		
	
	}}
	
	
	
	
	

	