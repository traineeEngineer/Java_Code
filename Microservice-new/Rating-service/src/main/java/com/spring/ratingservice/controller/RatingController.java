package com.spring.ratingservice.controller;

import com.spring.ratingservice.model.Rating;
import com.spring.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService rService;


    @PostMapping("/create")
    public ResponseEntity<Rating> saveRating(@RequestBody Rating rating){
        Rating newRatings = rService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRatings);
    }

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> allRatings = rService.getAllRatings();
        return ResponseEntity.status(HttpStatus.OK).body(allRatings);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Rating>> getByUserId(@PathVariable("user_id") String  userId){
        return ResponseEntity.ok(rService.getRatingByUserId(userId));
    }

    @GetMapping("hotel/{hotel_id}")
    public ResponseEntity<List<Rating>> getByHotelId(@PathVariable("hotel_id") String hotelId){
        return ResponseEntity.ok(rService.getRatingByHotelId(hotelId));
    }

}
