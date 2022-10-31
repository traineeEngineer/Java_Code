package com.spring.springbootmongoDb.repositroy;

import com.spring.springbootmongoDb.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person,String > {

    List<Person> finByName(String name);

    @Query(value = "{'age':{$gt:?0,$lt:?1}}",fields = "{address:0}")
    List<Person> findByAgeBetween(int min, int max);
}
