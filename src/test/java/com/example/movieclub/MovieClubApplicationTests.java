package com.example.movieclub;

import com.example.movieclub.domain.user.AppUser;
import com.example.movieclub.domain.user.AppUserMapper;
import com.example.movieclub.domain.user.AppUserRepository;
import com.example.movieclub.domain.user.dto.AppUserCredentialsDto;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@AllArgsConstructor
class MovieClubApplicationTests {

    private final AppUserRepository appUserRepository;

    @Test
    void contextLoads() {
        AppUserCredentialsDto appUserCredentialsDto = appUserRepository
                .findByEmail("admin@example.com")
                .map(AppUserMapper::map).orElseThrow();

        Assert.hasText("aa","aa");
    }

}
