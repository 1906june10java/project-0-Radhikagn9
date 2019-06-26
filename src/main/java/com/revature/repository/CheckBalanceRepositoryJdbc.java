package com.revature.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.model.CheckBalance;
import com.revature.model.Customer;
import com.revature.util.ConnectionUtil;


public class CheckBalanceRepositoryJdbc implements CheckBalanceRepository{
	private static Logger logger = Logger.getLogger(CheckBalanceRepositoryJdbc.class);
	private static CheckBalanceRepositoryJdbc checkbalDaoJdbc;

	private CheckBalanceRepositoryJdbc() {

	}

	public static CheckBalanceRepositoryJdbc getcheckbalDaoJdbc() {
		if(checkbalDaoJdbc == null) {
			checkbalDaoJdbc = new CheckBalanceRepositoryJdbc();
		}

		return checkbalDaoJdbc;
	}


	public boolean insert(CheckBalance checkbalance) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "INSERT INTO checkbalance VALUES(NULL,?,?)";

			PreparedStatement statement = connection.prepareStatement(command);


			statement.setLong(++statementIndex, checkbalance.getId());
			statement.setLong(++statementIndex, checkbalance.getAccno());
			statement.setFloat(++statementIndex, checkbalance.getBalance());

			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.warn("Exception adding a new balance", e);
		}
		return false;
	}



	public boolean insertProcedure(CheckBalance checkbalance) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;


			String command = "{CALL INSERT_CheckBalance(?,?,?,?)}";


			CallableStatement statement = connection.prepareCall(command);

			statement.setLong(++statementIndex, checkbalance.getId());
			statement.setLong(++statementIndex, checkbalance.getAccno());
			statement.setFloat(++statementIndex, checkbalance.getBalance());

			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.warn("Exception creating a new balance", e);
		}
		return false;
	}


	public CheckBalance select(CheckBalance checkbalance) {
		// TODO Auto-generated method stub
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "SELECT * FROM CheckBalance WHERE C_ACCNO = ?";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setLong(++statementIndex, checkbalance.getAccno());
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				return new CheckBalance(
						result.getLong("C_ID"),
						result.getLong("C_ACCNO"),
						result.getFloat("C_BAL")

						);
			}
		} catch (SQLException e) {
			logger.warn("Exception selecting a balance", e);
		}
		return checkbalance;
	}

	@Override
	public CheckBalanceRepository select(CheckBalanceRepository checkbalance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCheckBalanceHash(CheckBalanceRepository checkbalance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(com.revature.service.CheckBalance checkbalance) {
		// TODO Auto-generated method stub
		return false;
	}







}
