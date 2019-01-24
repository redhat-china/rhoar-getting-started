package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class FruitController {
    
    static List<Fruit> repository = Collections.synchronizedList(new ArrayList<>());
    
    static {
        repository.add(new Fruit(1L, "Cherry"));
        repository.add(new Fruit(2L, "Apple"));
        repository.add(new Fruit(3L, "Banana"));
    }

    @RequestMapping(path = "/api/fruits", method = RequestMethod.GET)
    @ApiOperation(value = "Get all fruits", notes = "Return all fruits in repositories")
    public List<Fruit> getAll() {
        return repository;
    }

    @RequestMapping(path = "/api/fruits/{id}", method = RequestMethod.GET)
    @ApiOperation(
            value = "Get fruit by id",
            notes = "Returns fruit for id specified.")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Fruit not found")})
    public Fruit getFruit(@ApiParam("Fruit id") @PathVariable("id") Long id) {
        for(int i = 0 ; i < repository.size() ; i ++) {
            if(repository.get(i).getId() == id) {
                return repository.get(i);
            }
        }
        return new Fruit("New");
    }
    
    @RequestMapping(path = "/api/fruits", method = RequestMethod.POST)
    @ApiOperation(
            value = "Add a fruit",
            notes = "Add a fruit to repositories")
    public Fruit add(@RequestBody Fruit newFruit) {
    	repository.add(newFruit);
    	return newFruit;
    }
    
    @RequestMapping(path = "/api/fruits/{id}", method = RequestMethod.PUT)
    @ApiOperation(
            value = "Update a exist fruit",
            notes = "Update a exist fruit in repositories, return the updated value")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Fruit not found")})
    public Fruit update(@RequestBody  Fruit fruit, @ApiParam("Fruit id") @PathVariable Long id) {
    	 for(int i = 0 ; i < repository.size() ; i ++) {
             if(repository.get(i).getId() == id) {
            	 repository.get(i).setName(fruit.getName());
            	 repository.get(i).setId(fruit.getId());
                 return repository.get(i);
             }
         }
    	 repository.add(fruit);
    	 if(fruit.getId() == null) {
    		 fruit.setId(id);
    	 }
    	 return fruit;
    }
    
    @RequestMapping(path = "/api/fruits/{id}", method = RequestMethod.DELETE)
    @ApiOperation(
            value = "Delete a exist fruit",
            notes = "Delete a exist fruit in repositories")
    public void delete(@ApiParam("Fruit id") @PathVariable Long id) {
    	Fruit fruit = null;
    	for(int i = 0 ; i < repository.size() ; i ++) {
            if(repository.get(i).getId() == id) {
            	fruit =  repository.get(i);
            }
        }
    	
    	if(null != fruit) {
    		repository.remove(fruit);
    	}
    }
}
