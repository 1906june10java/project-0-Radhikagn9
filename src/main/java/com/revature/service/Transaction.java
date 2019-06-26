package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.controller.ControlHandler;
import com.revature.model.FromTransaction;
import com.revature.model.ToTransaction;
import com.revature.util.ConnectionUtil;

public class Transaction {

	private static final Logger Logger = org.apache.log4j.Logger.getLogger(ControlHandler.class);

	
	public boolean validateUser(String FirstName, int phoneNumber) throws ClassNotFoundException,
	SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement prepStmt = connection.prepareStatement("SELECT C_PHONENUMBER FROM Customer WHERE C_FIRSTNAME=?");
			prepStmt.setString(1,FirstName);
			prepStmt.setInt(2, phoneNumber);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				Logger.info("PhoneNumber selected");
				Float balance1 = rs.getFloat(2);
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
	
	
	
	
	public float transferFund(Long ofAccNumber, Float amount) throws ClassNotFoundException,
	SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement prepStmt = connection.prepareStatement("SELECT F_Balance FROM FromTable WHERE F_Accno=?");
			prepStmt.setLong(1,ofAccNumber);
			prepStmt.setFloat(2,amount);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				Logger.info("Balance selected");
				Float balance1 = rs.getFloat(2);
				if (balance1 < 0)
					Logger.info("Unsufficient Fund");
				rs.close();
				return 1 ;
			}
		}   catch (SQLException e) {
			Logger.error("Cannot select.",e);		
			return 0;
		}
		return 1;
	}

	public float receiveFund(float balance1, Long ofAccNumber)  { 
		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement prepStmt = connection.prepareStatement("UPDATE FromTable SET F_Balance=" +(balance1)+ "- F_Balance WHERE =" + ofAccNumber);
			prepStmt.setFloat(1,balance1);
			prepStmt.executeUpdate("UPDATE FromTable SET F_Balance= "+ (balance1)+ " -F_Balance WHERE accno=" + ofAccNumber);
			prepStmt.executeUpdate(
					"UPDATE ToTable SET T_Balance="
							+ (balance1)+" T_Balance WHERE T_accno=" + ofAccNumber);
			prepStmt.setLong(1,ofAccNumber);
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


	public Float showBalance(Long accno, Float balance) throws ClassNotFoundException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			PreparedStatement prepStmt = connection.prepareStatement("SELECT T_Balance FROM ToTable WHERE T_accno=" +accno);
			prepStmt =(PreparedStatement) prepStmt.executeQuery("SELECT T_Balance FROM ToTable WHERE T_accno=" +accno);
			prepStmt.setFloat(1,balance);
			prepStmt.setLong(2,accno);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				Logger.info("Balance selected");
				//while (resultSet.next())
				//System.out.println(table + " " + accno + " " + resultSet.getInt(1));
				return 1f;
			}
		}   catch (SQLException e) {
			Logger.error("Cannot select.",e);		
			return 0f;
		}
		return 1f;
		
	}

		class UnsufficientFundException extends Exception {
			private static final long serialVersionUID = 1L;

			public UnsufficientFundException(String message) {
				super(message);
			}
		}

		

				Transaction transaction = new Transaction();
				{
					
				}
}
	

			
		
