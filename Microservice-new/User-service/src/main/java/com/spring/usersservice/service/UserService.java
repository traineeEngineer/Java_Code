package com.spring.usersservice.service;

import com.spring.usersservice.exception.ResourceNotFoundException;
import com.spring.usersservice.external.HotelService;
import com.spring.usersservice.model.Hotel;
import com.spring.usersservice.model.Rating;
import com.spring.usersservice.model.User;
import com.spring.usersservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestTemplate template;

    @Autowired
    private HotelService hotelService;


    public User saveUser(User user){
        return userRepo.save(user);
    }

    public List<User> getAllUser(){

        return userRepo.findAll();
    }

    public User getUserById(Integer id){
        User newUser = userRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("user not found"));


            Rating[] ratingForUser = template.getForObject("http://RATING-SERVICE/ratings/user/"+newUser.getId(), Rating[].class);
        log.info("{}",ratingForUser);

        List<Rating> ratings = Arrays.stream(ratingForUser).toList();


        List<Rating> ratingHotel = ratings.stream().map(rating -> {
          // ResponseEntity<Hotel> forEntity = template.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),
            //                                  Hotel.class);

            Hotel hotel = hotelService.getHotel(rating.getHotelId());

           // log.info("Response status code"+forEntity.getStatusCode());

            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        newUser.setRatings(ratingHotel);

        return newUser;
    }

}
