package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.controller.ControlHandler;
import com.revature.util.ConnectionUtil;

public class Deposit {
	
	private static final Logger Logger = org.apache.log4j.Logger.getLogger(ControlHandler.class);
	
	public int depositFund(int accno, int amount) throws ClassNotFoundException, SQLException {

		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement("SELECT C_AMOUNT FROM Customer WHERE T_ACCNO=?");
			prepStmt.setInt(1,accno);
			prepStmt.setInt(2,amount);
			ResultSet res = prepStmt.executeQuery();
			System.out.println(res.next());
			if(res.next()) {
				Logger.info("Amount = ");
				float currentBalance = res.getBigDecimal("T_BALANCE").floatValue();
				System.out.println();
				//while (resultSet.next())
				//System.out.println(table + " " + accno + " " + resultSet.getInt(1));
				//return currentBalance; //2349871290
			}
			else 
				return 0;
		}   catch (SQLException e) {
			Logger.error("Cannot select.",e);		
		}
		return 1;
	}

}
