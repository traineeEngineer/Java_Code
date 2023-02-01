package com.spring.hotelservice.service;

import com.spring.hotelservice.exception.ResourceNotFoundException;
import com.spring.hotelservice.model.Hotel;
import com.spring.hotelservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepo;


    public Hotel create(Hotel hotel){
        return  hotelRepo.save(hotel);
    }

    public List<Hotel> getAll(){
        return hotelRepo.findAll();
    }

    public Hotel getHotelById(Integer hId){
        return hotelRepo.findById(hId)
                .orElseThrow(()->new ResourceNotFoundException("hotel id Not found"));
    }

}
