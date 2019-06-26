package com.revature.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerRepositoryJdbc implements CustomerRepository {

	private static Logger logger = Logger.getLogger(CustomerRepositoryJdbc.class);


	private static CustomerRepositoryJdbc customerDaoJdbc;

	private CustomerRepositoryJdbc() {

	}

	public static CustomerRepositoryJdbc getCustomerDaoJdbc() {
		if(customerDaoJdbc == null) {
			customerDaoJdbc = new CustomerRepositoryJdbc();
		}

		return customerDaoJdbc;
	}


	@Override
	public boolean insert(Customer customer) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "INSERT INTO Customer VALUES(NULL,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(command);


			statement.setLong(++statementIndex, customer.getId());
			statement.setString(++statementIndex, customer.getFirstName().toUpperCase());
			statement.setString(++statementIndex, customer.getLastName().toUpperCase());
			statement.setString(++statementIndex, customer.getUsername().toLowerCase());
			statement.setString(++statementIndex, customer.getPassword());
			statement.setString(++statementIndex, customer.getEmail());
			statement.setLong(++statementIndex, customer.getAccno());
			statement.setLong(++statementIndex, customer.getPhoneNumber());
			

			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.warn("Exception creating a new customer", e);
		}
		return false;
	}


	@Override
	public boolean insertProcedure(Customer customer) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;


			String command = "{CALL INSERT_Customer(?,?,?,?,?,?,?,?)}";

			//Notice the CallableStatement
			CallableStatement statement = connection.prepareCall(command);

			//Set attributes to be inserted
			statement.setLong(++statementIndex, customer.getId());
			statement.setString(++statementIndex, customer.getFirstName().toUpperCase());
			statement.setString(++statementIndex, customer.getLastName().toUpperCase());
			statement.setString(++statementIndex, customer.getUsername().toLowerCase());
			statement.setString(++statementIndex, customer.getPassword());
			statement.setLong(++statementIndex, customer.getAccno());
			statement.setLong(++statementIndex, customer.getPhoneNumber());
			statement.setString(++statementIndex, customer.getEmail());
			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.warn("Exception creating a new customer with stored procedure", e);
		}
		return false;
	}


	@Override
	public List<Customer> selectAll() {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String command = "SELECT * FROM CUSTOMER";
			PreparedStatement statement = connection.prepareStatement(command);
			ResultSet result = statement.executeQuery();

			List<Customer> customerList = new ArrayList<>();
			while(result.next()) {
				customerList.add(new Customer(
						result.getLong("C_ID"),
						result.getString("C_FIRSTNAME"),
						result.getString("C_LASTNAME"),
						result.getString("C_USERNAME"),
						result.getString("C_PASSWORD"), 
						result.getLong("C_ACCNO"), 
						result.getLong("C_PHONENUMBER"),
						result.getString("C_EMAIL")
						));
			}

			return customerList;
		} catch (SQLException e) {
			logger.warn("Exception selecting all customers", e);
		} 
		return new ArrayList<>();
	}


	@Override
	public String getCustomerHash(Customer customer) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "SELECT GET_CUSTOMER_HASH(?,?) AS HASH FROM DUAL";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, customer.getUsername());
			statement.setString(++statementIndex, customer.getPassword());
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				return result.getString("HASH");
			}
		} catch (SQLException e) {
			logger.warn("Exception getting customer hash", e);
		} 
		return new String();
	}

	@Override
	public Customer select(Customer customer) {
		// TODO Auto-generated method stub
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "SELECT * FROM CUSTOMER WHERE C_USERNAME = ?";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, customer.getUsername());
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				return new Customer(
						result.getInt("C_ID"),
						result.getString("C_FIRSTNAME"),
						result.getString("C_LASTNAME"),
						result.getString("C_USERNAME"),
						result.getString("C_PASSWORD"), statementIndex, statementIndex, command
						result.getLong("C_ACCNO"), 
						result.getLong("C_PHONENUMBER"),
						result.getString("C_EMAIL")
						);
			}
		} catch (SQLException e) {
			logger.warn("Exception selecting a customer", e);
		}
		return customer;
	}

	@Override
	public boolean create(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createSecure(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}


	
	}











