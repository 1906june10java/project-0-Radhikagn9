package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.revature.util.ConnectionUtil;

public class CheckBalance {

	public float balance(String accno) {

		Scanner s = new Scanner (System.in);

		System.out.println ("Enter the Source account number :" );

		int saccno = s.nextInt ();

		System.out.println ("Enter the Destination account number :");  

		int daccno = s.nextInt ();

		System.out.println ("Enter the amount to transfer :");

		String amount = s.nextLine();
		Float amnt = Float.parseFloat(amount);
		
		return 1f;
	}
	}

	public float balance(Long Accno, Float Balance) {
		try (Connection connection = ConnectionUtil.getConnection()){
			PreparedStatement prepStmt = connection.prepareStatement("SELECT C_Bal FROM Checkbalance WHERE C_Accno= ?" );
					
			prepStmt.setLong(1, Accno);
			prepStmt.setFloat(3, Balance);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {

			//Balance= rs.getInt(Balance);
				System.out.println(Balance);
				if(Balance > amnt)
				{
					int up = prepStmt.executeUpdate("UPDATE Checkbalance SET  C_Bal = C_Bal -"+amnt+ "WHERE C_Accno="+saccno);

					int up1 =prepStmt.executeUpdate("UPDATE Checkbalance SET  C_Bal = C_Bal + \" amnt+ \"WHERE = avail_balance-"+amnt+ "where account_number="+daccno);

					//System.out.print (up+" "+up1);

					if (up==1 && up1==1)

					{

						cn.commit ();

						System.out.println ("*******: "+amnt+" balance is successfully Transferred:*******");

					}

					else

					{

						cn.rollback ();

						System.out.println ("rollback");

					}

				}

				else

				{

					System.out.println ("You does not have sufficient balance !!! please deposit in your account.");

				}

			}
		


			

		



		