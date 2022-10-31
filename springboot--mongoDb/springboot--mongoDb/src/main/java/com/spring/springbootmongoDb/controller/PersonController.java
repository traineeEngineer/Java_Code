package com.spring.springbootmongoDb.controller;

import com.spring.springbootmongoDb.model.Person;
import com.spring.springbootmongoDb.service.PersonService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping("/save")
    public String savePerson(@RequestBody Person person){
        return service.save(person);
    }

   @GetMapping("/{name}")
   public  List<Person> getPersonStartWith(String name){
        return  service.getPersonStartWith(name);
   }

   @DeleteMapping
   public  void deletePerson(@PathVariable String id){
      service.delete(id);
   }

   @GetMapping("/age")
   public  List<Person> getByPersonAge(@RequestParam int minAge,@RequestParam int maxAge){
        return  service.getByPersonAge(minAge,maxAge);
   }

   @GetMapping("/search")
   public Page<Person> searchPerson(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) int minAge,
                                    @RequestParam(required = false) int maxAge,
                                    @RequestParam(required = false) String city,
                                    @RequestParam(defaultValue = "0")Integer page,
                                    @RequestParam(defaultValue = "5")Integer size){
       Pageable pageable= PageRequest.of(page, size);
       return  service.seach(name, minAge,maxAge,city,pageable);
   }

   @GetMapping("/geezers")
   public List<Document> getOldestPerson(){
        return  service.getOldestPersonByCity();
   }

   @GetMapping("/population")
   public  List<Document> getPopulationByCity(){
        return service.getPopulationByCity();
   }
}
