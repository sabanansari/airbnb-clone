package com.ansari.projects.airBnbApp.service;

import com.ansari.projects.airBnbApp.dto.BookingDto;
import com.ansari.projects.airBnbApp.dto.BookingRequest;
import com.ansari.projects.airBnbApp.dto.GuestDto;
import com.ansari.projects.airBnbApp.dto.HotelReportDto;
import com.stripe.model.Event;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {

    BookingDto initializeBooking(BookingRequest bookingRequest);

    @Nullable BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    String getBookingStatus(Long bookingId);

    List<BookingDto> getAllBookingsByHotelId(Long hotelId);

    HotelReportDto getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);

    List<BookingDto> getMyBookings();
}
