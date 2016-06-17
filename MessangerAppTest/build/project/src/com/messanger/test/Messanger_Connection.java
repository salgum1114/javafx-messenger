package com.messanger.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Messanger_Connection {

	private Connection conn;

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		this.conn = DriverManager.getConnection("jdbc:mysql://192.168.10.18:3306/messanger", "root", "root");
		
		return conn;
	}
	
	public void getClose() throws SQLException{
		this.conn.close();
		this.conn = null;
	}
}
