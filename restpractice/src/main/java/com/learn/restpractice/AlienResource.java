package com.learn.restpractice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("xyz")
public class AlienResource {
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Alien getAlien(){
		System.out.println("hi called");
		Alien a1 = new Alien();
		a1.setName("Hargovind");
		a1.setPoints(60);
		return a1;
	}
}