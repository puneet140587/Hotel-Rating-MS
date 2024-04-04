package com.puneet.hotel.controller;

import com.puneet.hotel.entities.Hotel;
import com.puneet.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService ;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
      return  ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel)) ;
    }

    @PreAuthorize("hasAuhority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
        return  ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId)) ;
    }

    @PreAuthorize("hasAuhority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public  ResponseEntity<List<Hotel>> getAllHotels() {
        return  ResponseEntity.status(HttpStatus.OK).body(hotelService.fetchAllHotels());
    }



}
