package com.revature.repository;

import java.util.List;

import com.revature.model.Customer;

public interface CustomerRepository {
public boolean create(Customer customer);
	
	public boolean createSecure(Customer customer);
		
	public boolean insertProcedure(Customer customer);
	public Customer select(Customer customer);
	public List<Customer> selectAll();
	public String getCustomerHash(Customer customer);

	boolean insert(Customer customer);
	
}




