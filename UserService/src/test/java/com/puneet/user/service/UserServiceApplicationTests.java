package com.puneet.user.service;

import com.puneet.user.service.entities.Rating;
import com.puneet.user.service.external.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {
	@Autowired
	private RatingService ratingService ;


	@Test
	void contextLoads() {
	}

	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(10).userId("971c7f94-3977-475a-a27c-32900f766466").hotelId("92a71b52-4e91-4892-983a-e6e4346aa56f").feedback("this is created using feign client").build() ;
		Rating savedRating = ratingService.createRating(rating) ;
		System.out.println("new Ratings Created");
	}

}
