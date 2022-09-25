package com.validateDTO.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String petname;
	private String petType;
	private long petPrice;
	private String breed;
}
