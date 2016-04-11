package com.snapdeal.wth.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class DealsDao {
	
	private Connection con;
	private Statement  stmt,stmt2;
	
	public DealsDao() throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");  
		
		con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/wth_growth","root","");  
		
		stmt=con.createStatement();
		stmt2=con.createStatement();
	}
	
	public JSONArray getDeals(int userId) throws SQLException{
		
		ResultSet rs= stmt.executeQuery("select deals.id,Sellers.name,deals.img,deals.deal,deals.count,deals.price from deals,Sellers where deals.sellerId=Sellers.id");
		JSONArray jarray= new JSONArray();
		while(rs.next()){
			JSONObject json =  new JSONObject();
			json.put("dealId", rs.getInt(1));
			json.put("sellerName", rs.getString(2));
			ResultSet r = stmt2.executeQuery("select liked,viewed from scores where userId = " + userId + " and dealId = "+ rs.getInt(1));
			json.put("imagePath", rs.getString(3));
			json.put("dealHeading", rs.getString(4));
			if(r.next())
			{
				System.out.println("liked" + r.getBoolean(1));
				System.out.println("viewed" + r.getBoolean(2));
				json.put("liked", r.getBoolean(1));
				json.put("viewed", r.getBoolean(2));
			}
			else
			{
				json.put("liked", false);
				json.put("viewed", false);
			}
			/*json.put("liked", rs.getBoolean(5));
			json.put("viewed", rs.getBoolean(6));
			*/
			json.put("dealCount", (double)rs.getInt(5)/(double)10);
			json.put("price", rs.getInt(6));
			jarray.put(json);
		}
		System.out.println(jarray.toString());
		return jarray;
	}
	
	public JSONArray getListOfLikedDeals(int userId) throws SQLException{
	
		ResultSet rs= stmt.executeQuery("select deals.id,Sellers.name,deals.img,deals.deal,deals.count,deals.price from deals,Sellers,scores where deals.sellerId=Sellers.id and deals.id=scores.dealId and scores.liked=1 and scores.userId="+userId);
		JSONArray jarray= new JSONArray();
		while(rs.next()){
			JSONObject json =  new JSONObject();
			json.put("dealId", rs.getInt(1));
			json.put("sellerName", rs.getString(2));
			json.put("imagePath", rs.getString(3));
			json.put("dealHeading", rs.getString(4));
			json.put("dealCount", (double)rs.getInt(5)/(double)10);
			json.put("price", rs.getInt(6));
			jarray.put(json);
		}
	
	return jarray;
	}
	
	public JSONObject getDescribeDetailsAboutDeal(int userId,int dealId) throws SQLException{
		
		ResultSet rs= stmt.executeQuery("select  deals.id,Sellers.name,deals.img,deals.deal,deals.count,deals.price , deals.description from deals,Sellers where deals.id="+dealId+" and deals.sellerId=Sellers.id");
		JSONObject json =  new JSONObject();
		if(rs.next()){
			json.put("dealId", rs.getInt(1));
			json.put("sellerName", rs.getString(2));
			json.put("imagePath", rs.getString(3));
			json.put("dealHeading", rs.getString(4));
			json.put("dealCount", (double)rs.getInt(5)/(double)10);
			json.put("price", rs.getInt(6));
			json.put("description", rs.getString(7));
			ResultSet r = stmt2.executeQuery("select liked,viewed from scores where userId = " + userId + " and dealId = "+ rs.getInt(1));
			if(r.next())
			{
				System.out.println("liked" + r.getBoolean(1));
				System.out.println("viewed" + r.getBoolean(2));
				json.put("liked", r.getBoolean(1));
				json.put("viewed", r.getBoolean(2));
			}
			else
			{
				json.put("liked", false);
				json.put("viewed", false);
			}	
		}
		return json;
		
	}

	
public JSONArray getListOfTrendingDeals(int userId) throws SQLException{
		
		ResultSet rs= stmt.executeQuery("select deals.id,Sellers.name,deals.img,deals.deal,deals.count,deals.price from deals,Sellers where deals.sellerId=Sellers.id order by count desc limit 10");
		JSONArray jarray= new JSONArray();
		while(rs.next()){
			JSONObject json =  new JSONObject();
			json.put("dealId", rs.getInt(1));
			json.put("sellerName", rs.getString(2));
			ResultSet r = stmt2.executeQuery("select liked,viewed from scores where userId = " + userId + " and dealId = "+ rs.getInt(1));
			json.put("imagePath", rs.getString(3));
			json.put("dealHeading", rs.getString(4));
			if(r.next())
			{
				System.out.println("liked" + r.getBoolean(1));
				System.out.println("viewed" + r.getBoolean(2));
				json.put("liked", r.getBoolean(1));
				json.put("viewed", r.getBoolean(2));
			}
			else
			{
				json.put("liked", false);
				json.put("viewed", false);
			}
			/*json.put("liked", rs.getBoolean(5));
			json.put("viewed", rs.getBoolean(6));
			*/
			json.put("dealCount", (double)rs.getInt(5)/(double)10);
			json.put("price", rs.getInt(6));
			jarray.put(json);
		}
		System.out.println(jarray.toString());
		return jarray;
	}
	

}
