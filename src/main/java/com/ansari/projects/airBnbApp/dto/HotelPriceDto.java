package com.ansari.projects.airBnbApp.dto;

import com.ansari.projects.airBnbApp.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelPriceDto {

    private Hotel hotel;
    private Double price;
}
