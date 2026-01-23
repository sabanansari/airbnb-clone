package com.ansari.projects.airBnbApp.dto;

import com.ansari.projects.airBnbApp.entity.Hotel;
import com.ansari.projects.airBnbApp.entity.Room;
import com.ansari.projects.airBnbApp.entity.User;
import com.ansari.projects.airBnbApp.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {

    private Long id;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
