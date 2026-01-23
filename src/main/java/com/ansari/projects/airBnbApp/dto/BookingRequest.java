package com.ansari.projects.airBnbApp.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class BookingRequest {

    private Long hotelId;
    private Long roomId;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    //private Set<Long> guestIds;

}
