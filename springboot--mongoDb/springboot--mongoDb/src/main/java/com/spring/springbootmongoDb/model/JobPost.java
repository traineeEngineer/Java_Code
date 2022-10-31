package com.spring.springbootmongoDb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "JobPost")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPost {

    private String id;
    private String desc;
    private int exp;
    private String profile;
    private String[] tech;
}
