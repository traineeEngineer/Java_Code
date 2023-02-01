package com.spring.usersservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private Integer hId;
    private String name;
    private String location;
    private String about;
}
