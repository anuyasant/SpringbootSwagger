package com.ags.learn.integrate.springbootswagger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "All details about the Car. ")
public class CarDTO {
	@ApiModelProperty(notes = "The database generated car ID.")
	private int id;
	@ApiModelProperty(notes = "The car model name.")
	private String model;
	@ApiModelProperty(notes = "The company name to which the car belongs.")
	private String make;
}
