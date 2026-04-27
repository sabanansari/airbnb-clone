package com.ansari.projects.airBnbApp.service;

import com.ansari.projects.airBnbApp.dto.ProfileUpdateRequestDto;
import com.ansari.projects.airBnbApp.dto.UserDto;
import com.ansari.projects.airBnbApp.entity.User;
import org.jspecify.annotations.Nullable;

public interface UserService {
    User getUserById(Long userId);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
