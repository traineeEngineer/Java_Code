package com.Users.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	
	private Long departmentId;
	private String name;
	private String code;
	private String address;
	
}
