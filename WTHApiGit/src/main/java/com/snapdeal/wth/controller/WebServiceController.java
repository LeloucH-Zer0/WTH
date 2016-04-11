package com.snapdeal.wth.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/service")
public class WebServiceController
{		
	@GET
	@Path("/base")
	@Produces("application/json")
	public Response baseFunction()
	{
		String result = "Hello There!";
		return Response.status(200).entity(result).build();
	}
	
	@Path("/like/{DealId}/{UserId}")
	@GET
	@Produces("application/json")
	public Response likeAction(@PathParam("DealId") Integer dealId, @PathParam("UserId") Integer userId)
	{
		String result = "Hello There !"+ dealId.toString() + userId.toString();
		return Response.status(200).entity(result).build();
	}
}
