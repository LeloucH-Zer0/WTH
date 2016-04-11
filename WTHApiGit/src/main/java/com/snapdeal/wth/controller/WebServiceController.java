package com.snapdeal.wth.controller;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.snapdeal.wth.Dao.DealsDao;
import com.snapdeal.wth.Dao.ScoresDao;

@Path("/service")
public class WebServiceController {
	@GET
	@Path("/base")
	@Produces("application/json")
	public Response baseFunction() {
		String result = "Hello There!";
		return Response.status(200).entity(result).build();
	}

	@Path("/like/{DealId}/{UserId}")
	@GET
	@Produces("application/json")
	public Response likeAction(@PathParam("DealId") Integer dealId, @PathParam("UserId") Integer userId)
			throws ClassNotFoundException, SQLException {
		String result = "Hello There !" + dealId.toString() + userId.toString();
		System.out.println(dealId + " " + userId);

		ScoresDao scoresDao = new ScoresDao();
		if (!scoresDao.isDealViewed(dealId, userId))
			scoresDao.like(userId, dealId);
		else {
			if (!scoresDao.isDealLiked(dealId, userId))
				scoresDao.updateDeal(dealId, userId, true, true);
			
		}
		return Response.status(200).entity(result).build();
	}

	@Path("/dislike/{DealId}/{UserId}")
	@GET
	@Produces("application/json")
	public Response dislikeAction(@PathParam("DealId") Integer dealId, @PathParam("UserId") Integer userId)
			throws ClassNotFoundException, SQLException {
		String result = "Hello There !" + dealId.toString() + userId.toString();
		System.out.println(dealId + " " + userId);

		ScoresDao scoresDao = new ScoresDao();
		if (!scoresDao.isDealViewed(dealId, userId))
			scoresDao.dislike(userId, dealId);
		else{
			
		if(scoresDao.isDealLiked(dealId, userId))
			scoresDao.updateDeal(dealId, userId, false, true);
		}
		return Response.status(200).entity(result).build();
	}

	@Path("/getListOfDeals/{UserId}")
	@GET
	@Produces("application/json")
	public Response getListOfDeals(@PathParam("UserId") Integer userId) throws ClassNotFoundException, SQLException {
		String result = "Hello There !" + userId.toString();
		DealsDao deals = new DealsDao();
		JSONArray jarray = deals.getDeals(userId);
	
		return Response.status(200).entity(jarray.toString()).build();
	}
	
	@Path("/getListOfLikedDeals/{UserId}")
	@GET
	@Produces("application/json")
	public Response getListOfLikedDeals(@PathParam("UserId") Integer userId) throws ClassNotFoundException, SQLException {
		String result = "Hello There !" + userId.toString();
		DealsDao deals = new DealsDao();
		JSONArray jarray = deals.getListOfLikedDeals(userId);
	
		return Response.status(200).entity(jarray.toString()).build();
	}
	
	@Path("/getDescribeDetailsAboutDeal/{UserId}/{DealId}")
	@GET
	@Produces("application/json")
	public Response getDescribeDetailsAboutDeal(@PathParam("UserId") Integer userId,@PathParam("DealId") Integer dealId) throws ClassNotFoundException, SQLException {
		String result = "Hello There !" + userId.toString();
		DealsDao deals = new DealsDao();
		JSONObject json = deals.getDescribeDetailsAboutDeal(userId,dealId);
	
		return Response.status(200).entity(json.toString()).build();
	}
	
	@Path("/getListOfTrendingDeals/{UserId}")
	@GET
	@Produces("application/json")
	public Response getListOfTrendingDeals(@PathParam("UserId") Integer userId) throws ClassNotFoundException, SQLException {
		String result = "Hello There !" + userId.toString();
		DealsDao deals = new DealsDao();
		JSONArray jarray = deals.getListOfTrendingDeals(userId);
	
		return Response.status(200).entity(jarray.toString()).build();

	
	}
	
	
	

}
