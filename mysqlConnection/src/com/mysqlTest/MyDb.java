package com.mysqlTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyDb {
	public static void main(String[] args) {
		  Connection connection = null;
	        try {
	            // below two lines are used for connectivity.
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/test",
	                "root", "");
	 
	            // mydb is database
	            // mydbuser is name of database
	            // mydbuser is password of database
	 
	            Statement statement;
	            statement = connection.createStatement();
	            ResultSet resultSet;
	            resultSet = statement.executeQuery(
	                "select * from user");
	            int code;
	            String title;
	            while (resultSet.next()) {
	                code = resultSet.getInt("id");
	                title = resultSet.getString("name").trim();
	                System.out.println("id : " + code
	                                   + " name : " + title);
	            }
	            resultSet.close();
	            statement.close();
	            connection.close();
	        }
	        catch (Exception exception) {
	            System.out.println(exception);
	        }
	    } // function ends
	}

