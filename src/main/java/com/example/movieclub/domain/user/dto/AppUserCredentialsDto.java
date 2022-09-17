package com.example.movieclub.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
public class AppUserCredentialsDto {
    private String email;
    private String password;
    private Set<String> roles;
}
