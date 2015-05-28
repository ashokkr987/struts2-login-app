package com.tech_freaks.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {

	private static final String DBNAME = "tf_loginappdb"; //TODO - Change to your database name
	private static final String DB_USERNAME = "admin";//TODO - Change to your database user name
	private static final String DB_PASSWORD = "admin*"; //TODO - Change to your database login password
	 
		
	public boolean containsResult(String query, String[] params) throws Exception {
		boolean contains = false;
		Connection conn = null;
		try {
			conn = getConnection();
		     PreparedStatement prepStmt = conn.prepareStatement(query);
		     int count = 1;
		     for(String param : params) {
		    	 prepStmt.setString(count, param);
		    	 count++;
		     }
		     
		     ResultSet rs = prepStmt.executeQuery();
		     if(rs.next()) {
		       System.out.println("User login is valid in DB");
		       contains = true; 
		     }
		} catch(Exception e) {
		    System.out.println("validateLogon: Error while validating password: "+e.getMessage());
		    throw e;
		  } finally {
		     closeConnection(conn);
		  }
		return contains;
	}
		
		private void closeConnection(Connection conn) {
			try {
				if(conn!=null && !conn.isClosed()) {
					conn.close();
				}
			} catch(SQLException sqle) {
				System.out.println("Error while closing connection.");
			}
		}
	
	private Connection getConnection() throws Exception {
		   Connection conn = null;
		   try {
		     String url = "jdbc:mysql://localhost/"+DBNAME+"?user="+DB_USERNAME+"&password="+DB_PASSWORD;
		     Class.forName("com.mysql.jdbc.Driver");
		     conn = DriverManager.getConnection(url);
		   } catch (SQLException sqle) {
		      System.out.println("SQLException: Unable to open connection to db: "+sqle.getMessage());
		      throw sqle;
		   } catch(Exception e) {
		      System.out.println("Exception: Unable to open connection to db: "+e.getMessage());
		      throw e;
		   }
		   return conn;
		}
}
