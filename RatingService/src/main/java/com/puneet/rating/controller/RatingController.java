package com.puneet.rating.controller;

import com.puneet.rating.entities.Rating;
import com.puneet.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    //create rating
    @PreAuthorize("hasAuthority('Admin')")

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    //get all ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    //get all ratings of User
    @PreAuthorize("hasAuhority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    //get all ratings of a hotel
    @PreAuthorize("hasAuhority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

}
