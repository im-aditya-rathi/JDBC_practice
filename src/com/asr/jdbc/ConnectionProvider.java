package com.asr.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

		private static Connection con = null;
		
		public static Connection getConnection() {
			try {
				if(con == null) {
					String url = "jdbc:mysql://localhost:3306/jdbcpractice";
//					String url = "jdbc:mysql://127.0.0.1:3306/jdbcpractice";
					String username = "root";
					String pass = "root";
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection(url,username,pass);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return con;
		}
}
