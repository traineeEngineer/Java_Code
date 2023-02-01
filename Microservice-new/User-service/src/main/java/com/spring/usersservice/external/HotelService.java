package com.spring.usersservice.external;

import com.spring.usersservice.model.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")

public interface HotelService {

    @GetMapping("/hotels/{hotel_id}")
    public Hotel getHotel(@PathVariable("hotel_id") String id);


}
