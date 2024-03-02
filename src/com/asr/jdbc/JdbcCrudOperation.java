package com.asr.jdbc;

import java.sql.*;

import javax.sql.RowSet;

public class JdbcCrudOperation {
	
	static Connection con = ConnectionProvider.getConnection();
	
	public static void deleteTable() {
		try {
			String query = "drop table student";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			
			System.out.println("Table deleted successfully in database ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteData(int id) {
		try {
			String query = "delete from student where sid=?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

			System.out.println("Data deleted data successfully from table for ID "+id+" ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateData(int id, String name, String city) {
		try {
			String query = "update student set sname=?, scity=? where sid=?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(3, id);
			pstmt.setString(1, name);
			pstmt.setString(2, city);
			pstmt.executeUpdate();

			System.out.println("Updated data successfully inside table for ID "+id+" ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getAllData() {
		try {
			String query = "select * from student";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			System.out.println("All data of student table ...");
			while(rs.next()) {
				System.out.println(rs.getInt(1) + " :: " + rs.getString(2) + " :: " + rs.getString(3));
			}
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertData(String name, String city)
	{
		try {
			String query = "insert into student(sName, sCity) values(?,?)";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, city);
			pstmt.executeUpdate();

			System.out.println("Inserted data successfully into table ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void createTable() {
		try {
			String query = "create table student(sId int(20) primary key auto_increment, sName varchar(63), sCity varchar(31))";
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
			System.out.println("Table created successfully in database ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getDbDateAndTime() {
		try {
			String query = "select now()";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			System.out.println("Current date and time of database ...");
			while(rs.next()) {
				System.out.println(rs.getDate(1) + " :: " + rs.getTime(1));
			}
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
