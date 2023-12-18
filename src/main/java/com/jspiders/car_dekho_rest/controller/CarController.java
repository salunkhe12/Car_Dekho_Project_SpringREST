package com.jspiders.car_dekho_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.car_dekho_rest.pojo.CarPOJO;
import com.jspiders.car_dekho_rest.response.CarResponse;
import com.jspiders.car_dekho_rest.service.CarService;

@RestController
public class CarController {
	@Autowired
	private CarService service;
	
	  // To Add the car
	@PostMapping(path="/add",
			     consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> addCar(@RequestBody CarPOJO pojo){
		CarPOJO car=service.addCar(pojo);
		
		  //If Success
		if (car != null) {
			return new ResponseEntity<CarResponse>(new CarResponse("Car Details Added Successfully",car,null),HttpStatus.ACCEPTED);
		}
		
		//If Failure
		return new ResponseEntity<CarResponse>(new CarResponse("Car Details not Added**",null,null),HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	//Search The Car Details
	@GetMapping(path = "/search/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> searchStudent(@PathVariable int id) {
		CarPOJO car = service.searchCar(id);

		// If Success
		if (car != null) {
			return new ResponseEntity<CarResponse>(new CarResponse("Car Details found successfully. ", car, null),
					HttpStatus.FOUND);
		}

		// IF Failure
		return new ResponseEntity<CarResponse>(new CarResponse("Car Details not found. ", car, null),
				HttpStatus.NOT_FOUND);
	}
	
	//Search All the Car details
	@GetMapping(path = "/searchAll", produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<CarResponse> searchAllCars() {
		
		List<CarPOJO> cars = service.searchAllCars();

		                   // If Success   
		if (cars.isEmpty() == false) {
			return new ResponseEntity<CarResponse>(new CarResponse("All Car's Details found successfully!!. ", null, cars),
					HttpStatus.FOUND);
		}

		                    //If Failure
		return new ResponseEntity<CarResponse>(new CarResponse("Car's Details not found**. ", null, null),
				HttpStatus.NOT_FOUND);
	}
	
	
	    //For Remove the car details with id
     @DeleteMapping(path = "/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<CarResponse> removeStudent(@PathVariable int id) {
		
		CarPOJO car = service.removeCar(id);

		                     //If  Success
		if (car != null) {
			return new ResponseEntity<CarResponse>(new CarResponse("Car Details removed successfully.!! ", car, null), HttpStatus.OK);
		}

		                               //If Failure
		return new ResponseEntity<CarResponse>(new CarResponse("Car Details not removed.** ", null, null),
				HttpStatus.BAD_REQUEST);
	}
     
     @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<CarResponse> updateCar(@RequestBody CarPOJO pojo) {
 		CarPOJO car = service.updateCar(pojo);

 		// SUCCESS
 		if (car != null) {
 			return new ResponseEntity<CarResponse>(
 					new CarResponse("Data updated successfully. ", car, null), HttpStatus.ACCEPTED);
 		}

 		// FAILURE
 		return new ResponseEntity<CarResponse>(new CarResponse("Data not updated. ", null, null),
 				HttpStatus.NOT_ACCEPTABLE);
 	}

}

