package com.kdyzm.gentleman.intelligentcontroller.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.kdyzm.gentleman.intelligentcontroller.model.response.ResponseModel;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/system")
public interface SystemController {
	
	@GET
	@Path("/shutdown")
	public ResponseModel<Long> shutdown(@QueryParam("date") String date);
	
	@GET
	@Path("/shutdown/cancel")
	public ResponseModel<Boolean> cancelShutdown();
	
	@GET
	@Path("/voice/up")
	public ResponseModel<Boolean> systemVoiceUp();
	
	@GET
	@Path("/voice/down")
	public ResponseModel<Boolean> systemVoiceDown();
	
	@GET
	@Path("/voice/mute")
	public ResponseModel<Boolean> systemVoiceMute();
	
}
