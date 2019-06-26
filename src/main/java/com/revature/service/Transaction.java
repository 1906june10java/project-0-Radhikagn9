package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.controller.ControlHandler;
import com.revature.util.ConnectionUtil;

public class Transaction {

	private static final Logger Logger = org.apache.log4j.Logger.getLogger(ControlHandler.class);


	public boolean validateUser(String FirstName, int phoneNumber) throws ClassNotFoundException,
	SQLException {

		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement("SELECT C_PHONENUMBER FROM Customer WHERE C_FIRSTNAME=?");
			prepStmt.setString(1,FirstName);
			prepStmt.setInt(2, phoneNumber);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				Logger.info("PhoneNumber selected");
				Logger.info("valid User");
				rs.close();
				return true;
			}
		}   catch (SQLException e) {
			Logger.error("Invalid User.",e);		
			return false;
		}
		return true;
	}


	public float transferFund(int accno, float balance) throws ClassNotFoundException,
	SQLException {

		int amount;
		return balance;
        
	}

	public float receiveFund(float balance1, int daccno)  { 
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement prepStmt = connection.prepareStatement("UPDATE Customer SET C_Balance=" +(balance1)+ "- C_Balance WHERE =?");
			prepStmt.setFloat(1,balance1);
			prepStmt.executeUpdate("UPDATE Customer SET C_Balance= "+ (balance1)+ " -C_Balance WHERE accno= ?" );
			prepStmt.executeUpdate(
					"UPDATE Customer SET C_Balance="
							+ (balance1)+" C_Balance WHERE C_accno= ?");
			prepStmt.setLong(1,daccno);
			prepStmt.setFloat(2,balance1);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				Logger.info("Balance selected");
				Float balance2 = rs.getFloat(2);
				if (balance1 < 100)
					Logger.info("Unsufficient Fund.");
				rs.close();
				return 1 ;
			}
		}   catch (SQLException e) {
			Logger.error("Cannot select.",e);		
			return 0;
		}
		return 1f;
	}


	
	class UnsufficientFundException extends Exception {
		private static final long serialVersionUID = 1L;

		public UnsufficientFundException(String message) {
			super(message);
		}
	}
}




