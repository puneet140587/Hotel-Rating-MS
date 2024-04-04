package com.puneet.hotel.repositories;

import com.puneet.hotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository <Hotel, String>{
}
