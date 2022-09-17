package com.example.movieclub.domain.user;

import com.example.movieclub.domain.user.dto.AppUserCredentialsDto;

import java.util.stream.Collectors;

public class AppUserMapper {
    public static AppUserCredentialsDto map(AppUser appUser) {
        return new AppUserCredentialsDto(appUser.getEmail(), appUser.getPassword(),
                appUser.getAppUserRole()
                        .stream()
                        .map(AppUserRole::getName)
                        .collect(Collectors.toSet()));
    }
}
