package com.puneet.user.service.services.impl;

import com.puneet.user.service.entities.Hotel;
import com.puneet.user.service.entities.Rating;
import com.puneet.user.service.entities.User;
import com.puneet.user.service.exception.ResourceNotFoundException;
import com.puneet.user.service.external.HotelService;
import com.puneet.user.service.repository.UserRepository;
import com.puneet.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private HotelService hotelService ;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class) ;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
       // Fetch user data from repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given userId is not found on server :" + userId));//
       // Fetch Rating of the user from Rating service
    //    http://localhost:7071/ratings/users/25518e4b-3743-4a28-b5f8-43797d8bbf9d

        Rating [] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+ user.getUserId(), Rating[].class);
        logger.info(" {} ",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {

            //   Api Call to hotel service to get the details of Hotel
            // http://localhost:9091/hotels/f5b6a4cd-c483-43c0-b60e-7dc1c214f6b9
          //  ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
        //    Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId()) ;

        //    logger.info("response status code", forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);

        return user ;
    }
}
