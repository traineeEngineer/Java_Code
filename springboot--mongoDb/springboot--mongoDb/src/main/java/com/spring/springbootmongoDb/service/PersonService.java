package com.spring.springbootmongoDb.service;

import com.spring.springbootmongoDb.model.Person;
import com.spring.springbootmongoDb.repositroy.PersonRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonService {

    @Autowired
    private PersonRepository prepo;
    @Autowired
    private MongoTemplate template;

    public String save(Person person) {
        return  prepo.save(person).getId();
    }

    public List<Person> getPersonStartWith(String name) {
        return prepo.finByName(name);
    }

    public void delete(String id) {
        prepo.deleteById(id);
    }


    public List<Person> getByPersonAge(int minAge, int maxAge) {
        return  prepo.findByAgeBetween(minAge, maxAge);
    }

    public Page<Person> seach(String name, Integer minAge, Integer maxAge, String city, Pageable pageable) {
        Query query=new Query().with(pageable);
        List<Criteria> criteria=new ArrayList<>();
        if(name!=null && !name.isEmpty()){
            criteria.add(Criteria.where("fname").regex(name,"i"));
        }
        if(minAge!=null && maxAge!=null){
            criteria.add(Criteria.where("age").gte(minAge).lte(maxAge));
        }
        if(city!=null && !city.isEmpty()){
            criteria.add(Criteria.where("address.city").is(city));
        }
        if(!criteria.isEmpty()){
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }
        Page<Person> people= PageableExecutionUtils.getPage(template.find(query, Person.class), pageable,
                                        ()->template.count(query.skip(0).limit(0), Person.class));
        return people;
    }

    public List<Document> getOldestPersonByCity() {
        UnwindOperation operation= Aggregation.unwind("address");
        SortOperation sortOperation=Aggregation.sort(Sort.Direction.DESC,"age");
        GroupOperation groupOperation=Aggregation.group("address.city").first(Aggregation.ROOT).as("oldestPerson");

        Aggregation aggregation=Aggregation.newAggregation(operation,sortOperation,groupOperation);
        List<Document> person=template.aggregate(aggregation, Person.class,Document.class).getMappedResults();
        return  person;
    }

    public List<Document> getPopulationByCity() {
        UnwindOperation unwindOperation=Aggregation.unwind("address");
        GroupOperation groupOperation=Aggregation.group("address.city").count().as("popCount");
        SortOperation sortOperation=Aggregation.sort(Sort.Direction.DESC,"popCount");
        ProjectionOperation projectionOperation=Aggregation.project().andExpression("_id").as("city")
                                                           .andExpression("popCount").as("count")
                                                           .andExclude("_id");
        Aggregation aggregation=Aggregation.newAggregation(unwindOperation,groupOperation,sortOperation,projectionOperation);
        List<Document> documents=template.aggregate(aggregation, Person.class,Document.class).getMappedResults();
        return  documents;
    }
}
