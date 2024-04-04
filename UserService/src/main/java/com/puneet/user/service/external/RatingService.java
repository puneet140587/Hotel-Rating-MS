package com.puneet.user.service.external;

import com.puneet.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //Post  Call
    @PostMapping("/ratings")
    public Rating createRating(Rating values)  ;

    //Update call (Need Implementation at Rating service level first)
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating values) ;

    // Delete Call (Need implementation at Rating service level first)
    @DeleteMapping("/ratings/ratingId")
    void deleteRating(@PathVariable String ratingId) ;

}
