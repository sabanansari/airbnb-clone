package com.ansari.projects.airBnbApp.service;

import com.ansari.projects.airBnbApp.dto.GuestDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface GuestService {
    List<GuestDto> getAllGuests();

    GuestDto addNewGuest(GuestDto guestDto);

    void updateGuest(Long guestId,GuestDto guestDto);

    void deleteGuest(Long guestId);
}
