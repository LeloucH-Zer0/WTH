package com.snapdeal.wth.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ScoresDao {
	
	private Connection con;
	private Statement  stmt;
	
	public ScoresDao() throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");  
		
		con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/wth_growth","root","");  
		
		stmt=con.createStatement();
	}
	
	public void like(int userId,int dealId) throws SQLException{
		stmt.execute("insert into scores (userId,dealId,viewed,liked) values("+userId+","+dealId+",1,1)");
	}
	
	public void dislike(int userId,int dealId) throws SQLException{
		stmt.execute("insert into scores (userId,dealId,viewed,liked) values("+userId+","+dealId+",1,0)");
	}
	
	public Boolean isDealViewed(int dealId,int userId) throws SQLException{
		
		ResultSet rs=stmt.executeQuery("select * from scores where dealId="+dealId+" and userId="+userId);  
		return rs.next();
	}
	
	public Boolean isDealLiked(int dealId,int userId) throws SQLException{
		ResultSet rs=	stmt.executeQuery("select * from scores where dealId="+dealId+" and userId="+userId+" and liked=true");  
		return rs.next();
	}
	
	public void updateDeal(int dealId,int userId,Boolean liked,Boolean viewed) throws SQLException, ClassNotFoundException{
		UserDao userDao = new UserDao();
		int userRating =  userDao.getRating(userId);
		
		System.out.println(userRating);
		if(liked)
			stmt.executeUpdate("update deals set count=count+"+userRating+" where id="+dealId);
		else
			stmt.executeUpdate("update deals set count=count-"+userRating+" where id="+dealId);
			
			stmt.executeUpdate("update scores set liked="+liked+",viewed="+viewed+" where dealId="+dealId+" and userId="+userId);
		
	}
	
	

}
