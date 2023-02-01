package com.spring.ratingservice.service;

import com.spring.ratingservice.model.Rating;
import com.spring.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepo;

    public Rating createRating(Rating rating){
        return ratingRepo.save(rating);
    }

    public List<Rating> getAllRatings(){
        return ratingRepo.findAll();
    }

    public List<Rating> getRatingByUserId(String userId){
        return ratingRepo.findByUserId(userId);
    }

    public List<Rating> getRatingByHotelId(String hotelId){
        return ratingRepo.findByHotelId(hotelId);
    }
}
