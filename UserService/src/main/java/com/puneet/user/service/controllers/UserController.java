package com.puneet.user.service.controllers;

import com.puneet.user.service.entities.User;
import com.puneet.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService ;

    private Logger logger = LoggerFactory.getLogger(UserController.class) ;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser) ;
    }

    int retryCount = 1 ;

    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelfallback")
    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelfallback" )
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelfallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        logger.info("Get Single User Handler: UserController");
        logger.info("Retry Count : {}", retryCount);
        retryCount++ ;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user) ;
    }

    // Creating fallback method for circuit breaker
    public ResponseEntity<User> ratingHotelfallback(String userId, Exception ex) {
        logger.info("Fallback is executed because service is down");
        User user = User.builder()
                .email("dummy@gmail.com")
                .about("This is dummy user since some service was down")
                .name("dummy")
                .userId("123456789")
                .build();
        return  new ResponseEntity<>(user, HttpStatus.OK) ;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser) ;
    }



}
