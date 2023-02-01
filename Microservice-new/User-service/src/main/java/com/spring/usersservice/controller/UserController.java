package com.spring.usersservice.controller;


import com.spring.usersservice.model.User;
import com.spring.usersservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService service;

    int retryCount=1;


    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = service.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUser = service.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(allUser);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable("id") Integer id){
        User userId = service.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }

    public ResponseEntity<User> ratingHotelFallback(Integer id,Exception e){
       // log.info("fall back is executed because service is down:" ,e.getMessage());
        log.info("retry count :{}" ,retryCount);
        retryCount++;
        User user = User.builder().email("dummy@gamil.com")
                .name("dummy")
                .about("This user is created dummy because service is down")
                .id(12345)
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
