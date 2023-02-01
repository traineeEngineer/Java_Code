package com.spring.usersservice.external;

import com.spring.usersservice.model.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {


    @PostMapping("/ratings/create")
    public Rating createRating(Rating values);

    @PutMapping("/ratings/{id}")
    public Rating updateRating(Rating rating, @PathVariable("id") String ratingId);

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable("id") String ratingId);

}
