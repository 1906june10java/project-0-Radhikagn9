package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.controller.ControlHandler;


/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {
	
    public static void main(String[] args) {
    	
    	ControlHandler control = new ControlHandler();
    	control.userInput();
		
		

	}
}

