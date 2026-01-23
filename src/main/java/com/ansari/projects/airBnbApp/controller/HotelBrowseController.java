package com.ansari.projects.airBnbApp.controller;

import com.ansari.projects.airBnbApp.dto.HotelDto;
import com.ansari.projects.airBnbApp.dto.HotelInfoDto;
import com.ansari.projects.airBnbApp.dto.HotelSearchRequest;
import com.ansari.projects.airBnbApp.service.HotelService;
import com.ansari.projects.airBnbApp.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;
    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest){
        Page<HotelDto> page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        HotelInfoDto hotelInfoDto = hotelService.getHotelInfo(hotelId);
        return ResponseEntity.ok(hotelInfoDto);
    }
}
