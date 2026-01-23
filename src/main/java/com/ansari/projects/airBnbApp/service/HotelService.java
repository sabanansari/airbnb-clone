package com.ansari.projects.airBnbApp.service;

import com.ansari.projects.airBnbApp.dto.HotelDto;
import com.ansari.projects.airBnbApp.dto.HotelInfoDto;
import com.ansari.projects.airBnbApp.entity.Hotel;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);

    void activateHotel(Long id);

    HotelInfoDto getHotelInfo(Long hotelId);
}
