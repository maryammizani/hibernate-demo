package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String lJdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC"; // useSSL=false: update for MYSQL8
		String lUser = "hbstudent";
		String lPass = "hbstudent";
		
		try {
			System.out.println("Connecting to database: " + lJdbcUrl);
			Connection lConnection = DriverManager.getConnection(lJdbcUrl, lUser, lPass);
			System.out.println("Connection Successful!");
		} 
		catch (Exception e){
			e.printStackTrace();
		}

	}

}
