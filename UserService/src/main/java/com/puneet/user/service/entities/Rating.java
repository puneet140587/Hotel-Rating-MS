package com.puneet.user.service.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    private String ratingId ;
    private String userId ;
    private String hotelId;
    private Integer rating ;
    private String feedback ;
    private Hotel hotel ;








;}
