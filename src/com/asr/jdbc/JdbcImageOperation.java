package com.asr.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcImageOperation {

	static Connection con = ConnectionProvider.getConnection();
	static String uploadFolderPath = "images" + File.separator + "upload" + File.separator;
	static String downloadFloderPath = "images" + File.separator + "download" + File.separator;

	// Used to upload image from "images/upload" location to database.
	public static void uploadImage(String imgName) {
		try {
			String q = "insert into image(name,img) values (?,?)";
			PreparedStatement pstmt = con.prepareStatement(q);
			
			FileInputStream fis = null;
			if(new File(uploadFolderPath + imgName).exists()) {
				fis = new FileInputStream(uploadFolderPath + imgName);
			}
			else {
				System.out.println("!!! ERROR: file not found !!!");
				System.out.println("please enter a valid file name ...");
				return;
			}
			
			pstmt.setString(1, imgName);
			pstmt.setBinaryStream(2, fis, fis.available());
			pstmt.executeUpdate();
			
			System.out.println("Image uploaded to the database ...");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Used to download image to "images/download" location from database using image id.
	public static void downloadImage(int id) {
		String imageName = null;
		String imageRawName = null;
		String imageExtension = null;
		InputStream imageStream = null;
		try {
			String q = "select * from image where id = ?";
			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				imageName = rs.getString(2);
				imageStream = rs.getBinaryStream(3);
			}

			imageRawName = imageName.substring(0, imageName.lastIndexOf("."));
			imageExtension = imageName.substring(imageName.lastIndexOf("."));
			String downloadFloderImagePathWithoutExtension = downloadFloderPath + imageRawName;
			
			while(new File(downloadFloderImagePathWithoutExtension + imageExtension).exists()) {
				downloadFloderImagePathWithoutExtension += "_copy";
			}
			FileOutputStream fos = new FileOutputStream(downloadFloderImagePathWithoutExtension + imageExtension);
			fos.write(imageStream.readAllBytes());
			
			System.out.println("Image downloaded from the database ...");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createTable() {
		try {
			String q= "create table image(id int(20) primary key auto_increment, name varchar(31), img longblob)";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(q);
			
			System.out.println("Table created successfully in database ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteTable() {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("drop table image");
			
			System.out.println("Table deleted successfully in database ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
