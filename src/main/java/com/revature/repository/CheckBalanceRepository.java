package com.revature.repository;

import java.util.List;

import com.revature.model.Customer;
import com.revature.service.CheckBalance;

public interface CheckBalanceRepository {
	
	public CheckBalanceRepository select(CheckBalanceRepository checkbalance);
	
	public String getCheckBalanceHash(CheckBalanceRepository checkbalance);

	boolean insert(CheckBalance checkbalance);

	boolean insert(com.revature.model.CheckBalance checkbalance);

}
