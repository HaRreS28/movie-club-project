package com.example.movieclub;

import com.example.movieclub.domain.user.AppUserMapper;
import com.example.movieclub.domain.user.AppUserRepository;
import com.example.movieclub.domain.user.dto.AppUserRegistrationDto;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AllArgsConstructor
class MovieClubApplicationTests {

    private final AppUserRepository appUserRepository;

    @Test
    void contextLoads() {
        AppUserRegistrationDto appUserCredentialsDto = appUserRepository
                .findByEmail("admin@example.com")
                .map(AppUserMapper::map).orElseThrow();

    }

}
