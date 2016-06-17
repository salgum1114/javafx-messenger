package com.messanger.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Messanger_Connection {

	private Connection conn;

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/messanger", "root", "root");
		} catch (ClassNotFoundException e) {
			System.out.println("연결 실패");
		} catch (SQLException e) {
			System.out.println("연결 실패");
		}
		return conn;
	}
	
	public void getClose() throws SQLException{
		this.conn.close();
		this.conn = null;
	}
}
