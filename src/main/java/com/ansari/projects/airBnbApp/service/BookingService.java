package com.ansari.projects.airBnbApp.service;

import com.ansari.projects.airBnbApp.dto.BookingDto;
import com.ansari.projects.airBnbApp.dto.BookingRequest;
import com.ansari.projects.airBnbApp.dto.GuestDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface BookingService {

    BookingDto initializeBooking(BookingRequest bookingRequest);

    @Nullable BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
