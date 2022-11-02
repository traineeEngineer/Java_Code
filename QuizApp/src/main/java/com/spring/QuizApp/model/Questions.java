package com.spring.QuizApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String questionContent;
    @OneToMany(mappedBy = "questions",cascade = CascadeType.ALL)
    private Set<Answers> answers;
}
