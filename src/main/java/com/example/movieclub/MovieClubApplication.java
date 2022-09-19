package com.example.movieclub;

import com.example.movieclub.domain.user.AppUserRepository;
import com.example.movieclub.domain.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MovieClubApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MovieClubApplication.class, args);
//        AppUserService userService = context.getBean(AppUserService.class);
//        System.out.println(userService.findUserByEmail("admin@example.com").getRoles());

    }

}
