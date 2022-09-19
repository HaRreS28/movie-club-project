package com.example.movieclub.domain.user;

import com.example.movieclub.domain.user.dto.AppUserRegistrationDto;


public class AppUserMapper {
    public static AppUserRegistrationDto map(AppUser appUser) {
        return new AppUserRegistrationDto(appUser.getEmail(), appUser.getPassword());
    }


}
