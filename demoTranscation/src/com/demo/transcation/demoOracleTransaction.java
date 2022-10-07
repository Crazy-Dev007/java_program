package com.demo.transcation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.Scanner;

public class demoOracleTransaction {

	public static void main(String[] args) {
		Connection con = null;
		try {
			Scanner s = new Scanner(System.in);
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##shriramwar", "Admin");
			System.out.println("Commit status : " + con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("Commit status : " + con.getAutoCommit());
			PreparedStatement ps1 = con.prepareStatement("select * from Bank48 where accno=?  ");
			PreparedStatement ps2 = con.prepareStatement("update bank48 set balance=balance+? where accno=?");
			Savepoint sp = con.setSavepoint();
			System.out.println("Enter homeAccNo: ");
			long hAccNo = s.nextLong();// 6123456
			ps1.setLong(1, hAccNo);
			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next()) {
				float bal = rs1.getFloat(3);// 12000
				System.out.println("Enter benefiecieryAccNo:");
				long bAccNo = s.nextLong();// 313131
				ps1.setLong(1, bAccNo);
				ResultSet rs2 = ps1.executeQuery();
				if (rs2.next()) {
					System.out.println("Enter the amt to be transferred:");
					float amt = s.nextFloat();// 3000
					if (amt <= bal) {
						ps2.setFloat(1, -amt);
						ps2.setLong(2, hAccNo);
						int i = ps2.executeUpdate();// Updated in buffer
						ps2.setFloat(1, amt);
						ps2.setLong(2, bAccNo);
						int j = ps2.executeUpdate();// Updated in buffer
						if (i == 1 && j == 1) {
							con.commit();// Updated DataBase
							System.out.println("Transaction Successfully..");
							
							PreparedStatement ps3 = con.prepareCall("Insert into TansLog48 values(?,?,?,systimestamp)");
							ps3.setLong(1, hAccNo);
							ps3.setLong(2, bAccNo);
							ps3.setFloat(3, amt);
							
							int translong = ps3.executeUpdate();
							
							if (translong > 0) {
								System.out.println("Transaction logged !!!");
							}
							
							
							
							
						} else {
							con.rollback(sp);
							System.out.println("Transaction failed...");
							
							
						}
					} else {
						System.out.println("Insufficient fund...");
					}
				} else {
					System.out.println("Invalid bAccNo...");
				}
			} else {
				System.out.println("Invalid homeAccNo...");
			}

			con.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
