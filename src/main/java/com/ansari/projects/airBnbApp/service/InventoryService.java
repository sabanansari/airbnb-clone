package com.ansari.projects.airBnbApp.service;

import com.ansari.projects.airBnbApp.dto.HotelDto;
import com.ansari.projects.airBnbApp.dto.HotelPriceDto;
import com.ansari.projects.airBnbApp.dto.HotelSearchRequest;
import com.ansari.projects.airBnbApp.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
