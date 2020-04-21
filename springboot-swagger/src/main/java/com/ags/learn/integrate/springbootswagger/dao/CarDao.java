package com.ags.learn.integrate.springbootswagger.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ags.learn.integrate.springbootswagger.dto.CarDTO;


@Component
public class CarDao {
	static Map<Integer, CarDTO> cars;
	static Integer carId = 0;
	static{
		cars = new HashMap<>();
		cars.put(1, new CarDTO(1, "Tiago", "TATA"));
		cars.put(2, new CarDTO(2, "Civic", "Honda"));
		cars.put(3, new CarDTO(3, "BMW", "BMW"));
		cars.put(4, new CarDTO(4, "Corvette", "Chevrolet"));
		cars.put(5, new CarDTO(5, "Jaguar", "TATA"));
		cars.put(6, new CarDTO(6, "Viper", "Dodge"));
		cars.put(7, new CarDTO(7, "Mustang GT", "Ford"));
		carId = 8;
	}
	
	public Map<Integer, CarDTO> getAll (){
        return cars ;
    }
	
	public CarDTO getById(Integer id){
		return cars.get(id);
	}

	public CarDTO add(CarDTO car) {
		cars.put(carId, new CarDTO(carId, car.getModel(), car.getMake()));
		car.setId(carId);
		carId = carId+1;
		return car;
	}

	public String update(CarDTO carDTO) {
		cars.put(carDTO.getId(), carDTO);
		return "Car details updated successfully for id :: " + carDTO.getId();
	}

	public String delete(Integer carId) {
		cars.remove(carId);
		return "Car details deleted successfully for id :: " + carId;
	}
	
	
}
