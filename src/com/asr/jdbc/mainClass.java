package com.asr.jdbc;

import java.io.File;

public class mainClass {

	public static void main(String agrs[]) {
		
		simpleCrudOperations();
		
		System.out.println("\n-------------------------------------------\n");
		
		imageOperations();	
		
	}
	
	private static void imageOperations() {
//		JdbcImageOperation.createTable();
		
//		JdbcImageOperation.uploadImage("test2.jpg");
		
//		JdbcImageOperation.downloadImage(2);
		
//		JdbcImageOperation.deleteTable();
	}
	
	private static void simpleCrudOperations() {
		
		// 1. get date and time
		JdbcCrudOperation.getDbDateAndTime();
		
//		// 2. create table
//		JdbcOperation.createTable();
		
//		// 3. insert data into table
//		JdbcOperation.insertData("aditya","delhi");
		
//		// 4. get data from table
//		JdbcOperation.getAllData();
		
//		// 5. update data inside table
//		JdbcOperation.updateData(2, "tushar", "ghaziyabad");
		
//		// 6. delete data inside table
//		JdbcOperation.deleteData(2);
		
//		// 7. delete table
//		JdbcOperation.deleteTable();	
	}
	
}
