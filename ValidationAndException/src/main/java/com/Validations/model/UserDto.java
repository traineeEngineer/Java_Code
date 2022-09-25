package com.Validations.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	@NotEmpty(message = "user name must not be null")
	private String name;
	
	@Min(18)
	@Max(65)
	private int age;

	@Email
	private String email;
	
	private String gender;
	@NotBlank(message = "Nationality must be enterd")
	private String nationality;
	
	@Pattern(regexp = "^\\d{10}$",message = "Invalid mobile number")
	
	private String mobile;
}
