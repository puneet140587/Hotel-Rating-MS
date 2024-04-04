package com.puneet.user.service.services;

import com.puneet.user.service.entities.User;


import java.util.List;


public interface UserService {

    // Create User
     User saveUser (User user) ;

    // get all user
    List<User> getAllUser()  ;

    // get single User
    User getUser(String userId) ;



}
