package com.kdyzm.gentleman.intelligentcontroller.api.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kdyzm.gentleman.intelligentcontroller.model.Post;
import com.kdyzm.gentleman.intelligentcontroller.model.PostDetail;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/demo")
public interface DemoRest {
	@GET
	@Path("/test/{id}")
	public PostDetail getPostDetail(@PathParam("id") Integer id);
	
	@GET
	@Path("/test/rest")
	public PostDetail restTemplateDemo();
	
	@GET
	@Path("/konachan/post/{id}")
	public Post getKonachanPostById(@PathParam("id") Long id);
}
