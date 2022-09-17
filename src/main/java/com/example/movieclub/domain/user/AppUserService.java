package com.example.movieclub.domain.user;

import com.example.movieclub.domain.user.dto.AppUserCredentialsDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public AppUserCredentialsDto findUserByEmail(String email){
      return  appUserRepository.findByEmail(email).map(AppUserMapper::map).orElseThrow(() ->
              new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }
}
