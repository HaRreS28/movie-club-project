package com.example.movieclub.domain.user;

import com.example.movieclub.domain.user.dto.AppUserRegistrationDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String saveUser(AppUserRegistrationDto registrationDto){
        AppUserRole userRole = appUserRoleRepository.findByName(Roles.USER.name()).orElseThrow();
        AppUser appUser = new AppUser();
        appUser.getAppUserRole().add(userRole);
        appUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        appUser.setEmail(registrationDto.getEmail());

//        TODO token with mail
        return "";
    }
}
