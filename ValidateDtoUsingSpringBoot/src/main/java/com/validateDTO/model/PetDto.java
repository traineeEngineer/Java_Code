package com.validateDTO.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
	
	private int id;
	
	@NotEmpty(message = "pet type is must")
	private String petType;
	
	@NotNull(message="must provide price")
	@Min(value = 500)
	@Max(value = 1000)
	private long petPrice;
}
