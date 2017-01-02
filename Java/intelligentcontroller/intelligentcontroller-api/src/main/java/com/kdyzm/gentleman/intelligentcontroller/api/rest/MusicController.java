package com.kdyzm.gentleman.intelligentcontroller.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kdyzm.gentleman.intelligentcontroller.model.response.ResponseModel;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/controller")
public interface MusicController {

	@Path("/next")
	@GET
	public ResponseModel<Boolean> next();

	@Path("/before")
	@GET
	public ResponseModel<Boolean> before();

	@Path("/stop")
	@GET
	public ResponseModel<Boolean> stop();

	@Path("/play")
	@GET
	public ResponseModel<Boolean> play();

	@Path("/voiceup")
	@GET
	public ResponseModel<Boolean> voiceup();

	@Path("/voicedown")
	@GET
	public ResponseModel<Boolean> voicedown();
}
