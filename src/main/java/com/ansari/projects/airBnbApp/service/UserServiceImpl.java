package com.ansari.projects.airBnbApp.service;

import com.ansari.projects.airBnbApp.dto.ProfileUpdateRequestDto;
import com.ansari.projects.airBnbApp.dto.UserDto;
import com.ansari.projects.airBnbApp.entity.User;
import com.ansari.projects.airBnbApp.exception.ResourceNotFoundException;
import com.ansari.projects.airBnbApp.repository.UserRepository;
import com.ansari.projects.airBnbApp.util.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    @Override
    public void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto) {
        User user = AppUtils.getCurrentUser();

        if(profileUpdateRequestDto.getDateOfBirth() != null){
            user.setDateOfBirth(profileUpdateRequestDto.getDateOfBirth());
        }

        if(profileUpdateRequestDto.getName() != null){
            user.setName(profileUpdateRequestDto.getName());
        }

        if(profileUpdateRequestDto.getGender() != null){
            user.setGender(profileUpdateRequestDto.getGender());
        }

        userRepository.save(user);
    }

    @Override
    public UserDto getMyProfile() {

        User user = AppUtils.getCurrentUser();
        log.info("Getting my profile for User: {}", user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
}
