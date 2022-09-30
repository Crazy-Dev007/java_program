package com.connectionPoolDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class ConnectionPool {

	public Vector<Connection> v;
	private String url;
	private String pass;
	private String uname;

	public ConnectionPool(String url, String uname, String pass) {
		this.url = url;
		this.uname = uname;
		this.pass = pass;
	}

	public void createConnection() {
		try {
			v = new Vector<Connection>();
			while (v.size() <= 3) {

				Connection con = DriverManager.getConnection(this.url, this.uname, this.pass);
				v.add(con);
				System.out.println(con);
			}
			if (v.size() >= 3)
				System.out.println("Connection Pool Created.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public synchronized Connection useConnection() {
		Connection con = v.firstElement();
		v.remove(0);
		return con;
	}

	public void returnConnection(Connection con) {
		v.addElement(con);
	}
}
