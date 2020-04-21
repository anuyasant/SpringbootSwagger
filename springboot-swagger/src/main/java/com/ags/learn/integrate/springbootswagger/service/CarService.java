package com.ags.learn.integrate.springbootswagger.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ags.learn.integrate.springbootswagger.dao.CarDao;
import com.ags.learn.integrate.springbootswagger.dto.CarDTO;


@Service
public class CarService {

	@Autowired
	CarDao carDao;
	
	public CarDTO getById(Integer id){
		return carDao.getById(id);
	}
	
	public List<CarDTO> getAll(){
		return carDao.getAll().values().stream()
		.collect(Collectors.toList());
	}

	public CarDTO add(CarDTO car) {
		return carDao.add(car);
	}

	public String update(CarDTO carDTO) {
		return carDao.update(carDTO);
	}

	public String delete(Integer carId) {
		return carDao.delete(carId);
	}
}
