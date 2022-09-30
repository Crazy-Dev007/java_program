package com.demo.transcation;

import java.sql.Connection;
import java.sql.DriverManager;

public class demoOracleTransaction {

	public static void main(String[] args) {
		
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:@localhost:1521:xe", "system", "pass");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
