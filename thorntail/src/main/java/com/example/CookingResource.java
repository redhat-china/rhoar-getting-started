package com.example;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Info;
import io.swagger.annotations.Contact;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition.Scheme;;

@Path("/")
@Api(value = "/", protocols = "http", tags = "Cooking Service")
@SwaggerDefinition(schemes = Scheme.HTTP, 
                   host = "cooking-backend-services.apps.example.com", 		
                   info = @Info(title = "Cooking Service", 
                   version = "0.0.1", 
                   description = "An Service for CRUD Operations towards cooking repositories",
                   termsOfService = "Terms of service",
                   contact = @Contact(name = "Kylin Soong", url = "http://ksoong.org/docs", email = "kylinsoong.1214@gmail.com"),
                   license = @License(name = "Apache License Version 2.0", url = "http://www.apache.org/licenses/")))
public class CookingResource {

    
    static Map<String, Cooking> map = new ConcurrentHashMap<>();
    
    static {
    	map.put("Egg", new Cooking("Egg"));
    	map.put("Par", new Cooking("Par"));
    	map.put("Bar", new Cooking("Bar"));
    }

    @GET
    @Path("/cooking")
    @Produces("application/json")
    @ApiOperation(value = "Get all cooking", notes = "Return all cookings in repositories")
    public Collection<Cooking> getCooking() {
        return map.values();
    }
    
    @GET
    @Path("/cooking/{content}")
    @Produces("application/json")
    @ApiOperation(
            value = "Get cooking by content",
            notes = "Returns cooking for content specified.")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Cooking not found")})
    public Cooking getCooking(@ApiParam(value = "Cooking content", required = true) @PathParam("content") String content) { 	
        return map.get(content) == null ? new Cooking("Egg") : map.get(content);
    }
    
    @POST
    @Path("/cooking")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiOperation(
            value = "Add a cooking",
            notes = "Add a cooking to repositories")
    public Cooking createCooking(Cooking cooking) {
    	return map.put(cooking.getContent(), cooking);
    }
    
    @PUT
    @Path("/cooking/{content}")
    @Consumes("application/json")
    @Produces("application/json")
    @ApiOperation(
            value = "Update a exist cooking",
            notes = "Update a exist cooking in repositories, return the updated value")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Cooking not found")})
    public Cooking updateCooking(@ApiParam(value = "Cooking content", required = true) @PathParam("content") String content, Cooking cooking) {
    	return map.put(content, cooking);
    }
    
    @DELETE
    @Path("/cooking/{content}")
    @Produces("application/json")
    @ApiOperation(
            value = "Delete a exist cooking",
            notes = "Delete a exist cooking in repositories")
    public Cooking deleteCooking(@ApiParam(value = "Cooking content", required = true) @PathParam("content") String content) {
    	return map.remove(content);
    }
    
    

}
