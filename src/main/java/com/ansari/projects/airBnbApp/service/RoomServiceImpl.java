package com.ansari.projects.airBnbApp.service;

import com.ansari.projects.airBnbApp.dto.RoomDto;
import com.ansari.projects.airBnbApp.entity.Hotel;
import com.ansari.projects.airBnbApp.entity.Room;
import com.ansari.projects.airBnbApp.entity.User;
import com.ansari.projects.airBnbApp.exception.ResourceNotFoundException;
import com.ansari.projects.airBnbApp.exception.UnauthorizedException;
import com.ansari.projects.airBnbApp.repository.HotelRepository;
import com.ansari.projects.airBnbApp.repository.RoomRepository;
import com.ansari.projects.airBnbApp.util.AppUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;

    @Override
    @Transactional
    public RoomDto createNewRoom(Long hotelId,RoomDto roomDto) {
        log.info("Creating new room in a hotel with id:"+hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel doesn't exist with id:"+hotelId));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("This user does not own this hotel with id:"+hotelId);
        }

        Room room  = modelMapper.map(roomDto,Room.class);

        room.setHotel(hotel);

        room = roomRepository.save(room);

        //TODO: Create inventory as soon as room is created and hotel is active
        if(hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room,RoomDto.class);

    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms in a hotel with id:"+hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel doesn't exist with id:"+hotelId));

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("This user does not own this hotel with id:"+hotelId);
        }

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element,RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting room with id:"+roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id:"+roomId));



        return modelMapper.map(room,RoomDto.class);

    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room with id:"+roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id:"+roomId));

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!user.equals(room.getHotel().getOwner())){
            throw new UnauthorizedException("This user does not own this room with id:"+roomId);
        }

        inventoryService.deleteAllInventories(room);
        roomRepository.deleteById(roomId);


    }

    @Override
    public RoomDto updateRoomById(Long hotelId, Long roomId, RoomDto roomDto) {

        log.info("Updating room with id:"+roomId);

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID:"+hotelId));

        User user = AppUtils.getCurrentUser();

        if(!user.equals(hotel.getOwner())){
            throw new UnauthorizedException("This user does not own this hotel with id:"+hotelId);
        }

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found with ID:"+roomId));

        modelMapper.map(roomDto,room);

        room.setId(roomId);

        //TODO: If price or inventory is updated, then update the inverntory for this room

        room = roomRepository.save(room);

        return modelMapper.map(room,RoomDto.class);
    }
}
