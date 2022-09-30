package com.connectionPoolDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CpMain {

	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPool("jdbc:oracle:thin:@localhost:1521:xe", "system", "123456");
		// Create the Connection pool
		cp.createConnection();
		System.out.println("before use Pool Size : "+ cp.v.size());
		// Use of Connection 
		Connection con = cp.useConnection();
		try {
			System.out.println("Within Pool Size : "+ cp.v.size());
			PreparedStatement pst = con.prepareStatement("select * from tab");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			
			// returning connection
			cp.returnConnection(con);
			System.out.println("After Pool Size : "+ cp.v.size());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
