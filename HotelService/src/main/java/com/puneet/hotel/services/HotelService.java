package com.puneet.hotel.services;


import com.puneet.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel) ;

    List<Hotel> fetchAllHotels() ;

    Hotel getHotel(String id) ;



}
