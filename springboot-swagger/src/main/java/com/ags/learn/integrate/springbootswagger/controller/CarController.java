package com.ags.learn.integrate.springbootswagger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ags.learn.integrate.springbootswagger.dto.CarDTO;
import com.ags.learn.integrate.springbootswagger.service.CarService;
import com.ags.learn.integrate.springbootswagger.util.ResourceNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


@Api(value="Car Management System", 
description="Springboot Rest Swagger Integration")
@RestController("/api")
public class CarController {

	@Autowired
	CarService carService;

	@ApiOperation(value = "View a list of available cars", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/cars")
	public ResponseEntity<List<CarDTO>> getAll(){
		return ResponseEntity.ok().body(carService.getAll());
	}

	@ApiOperation(value = "View available car by id", response = CarDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/cars/{id}")
	public ResponseEntity<CarDTO> getById(
			@ApiParam(value = "Retrieve object using ID", required = true)
			@PathVariable(value = "id") Integer carId)
					throws ResourceNotFoundException {
		CarDTO carDTO = new CarDTO(); 
		carDTO = carService.getById(carId);

		if(carDTO == null){
			throw new ResourceNotFoundException("Car not found for this id :: " + carId);
		}
		return ResponseEntity.ok().body(carDTO);
	}

	@ApiOperation(value = "Add new car", response = CarDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully added new car details."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping("/cars")
	public ResponseEntity<CarDTO> add(
			@ApiParam(value = "Add car", required = true)
			@RequestBody CarDTO car){
		CarDTO carDTO = new CarDTO(); 
		carDTO = carService.add(car);
		return ResponseEntity.ok().body(carDTO);
	}


	@ApiOperation(value = "Update existing car details", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully updated car details"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PutMapping("/cars/{id}")
	public ResponseEntity<String> update(
			@ApiParam(value = "Update object using ID", required = true)
			@PathVariable(value = "id") Integer carId,
			@ApiParam(value = "Update Car object", required = true)
			@RequestBody CarDTO car){
		CarDTO carDTO = carService.getById(carId);
		if(carDTO == null){
			return new ResponseEntity<>("Car not found for this id :: " + carId, HttpStatus.NOT_FOUND);
		}
		carDTO.setMake(car.getMake());
		carDTO.setModel(car.getModel());
		return new ResponseEntity<>(carService.update(carDTO), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete existing car details", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted car details"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@DeleteMapping("/cars/{id}")
	public ResponseEntity<String> delete(
			@ApiParam(value = "Delete object using ID", required = true)
			@PathVariable(value = "id") Integer carId){
		CarDTO carDTO = carService.getById(carId);
		if(carDTO == null){
			return new ResponseEntity<>("Car not found for this id :: " + carId, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(carService.delete(carId), HttpStatus.OK);
	}
}
