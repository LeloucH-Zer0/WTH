package com.snapdeal.wth.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDao {

	private Connection con;
	private Statement  stmt;
	
	public UserDao() throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");  
		
		con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/wth_growth","root","");  
		
		stmt=con.createStatement();
	}
	
	public Integer getRating(int userId) throws SQLException{
		System.out.println(userId);
		ResultSet rs= stmt.executeQuery("select rating from users where id="+userId);
		
		if(rs.next())
			return (rs.getInt("rating"));
		else
			return 10;
	}
	
	


}


