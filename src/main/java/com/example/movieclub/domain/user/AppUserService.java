package com.example.movieclub.domain.user;

import com.example.movieclub.domain.user.dto.AppUserRegistrationDto;

import com.example.movieclub.domain.user.roles.AppUserRole;
import com.example.movieclub.domain.user.roles.AppUserRoleRepository;
import com.example.movieclub.domain.user.roles.Roles;
import com.example.movieclub.domain.user.token.Token;
import com.example.movieclub.domain.user.token.TokenService;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;
    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }


    public boolean userEnabled(String email){
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if(appUser.isPresent()){
            return appUser.get().getEnabled();
        }
        return false;
    }


    public boolean userExists(String email){
        return appUserRepository.existsByEmail(email);
    }
    public AppUser getUser(String email){
       return appUserRepository.findByEmail(email).orElseThrow();
    }

    public AppUserRegistrationDto getDto(String email){
        return AppUserMapper.map(getUser(email));
    }

    @Transactional
    public String changePassword(String email,String newPassword,String confirmPassword){
        AppUser user = getUser(email);
        user.setPassword(passwordEncoder.encode(newPassword));
        return "Hasło zostało zmienione";
    }
    public String createToken(String email,AppUser appUser){
        String token = tokenService.createToken();
        tokenService.deleteOldToken(email);
        LocalDateTime createdAt=LocalDateTime.now();
        Token tokenToSave = new Token(token, createdAt, createdAt.plusMinutes(15), null, appUser);
        tokenService.saveToken(tokenToSave);
        return token;
    }

    public String saveUser(AppUserRegistrationDto registrationDto){
        AppUserRole userRole = appUserRoleRepository.findByName(Roles.USER.name()).orElseThrow();
        AppUser appUser = new AppUser();
        appUser.getAppUserRole().add(userRole);
        appUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        appUser.setEmail(registrationDto.getEmail());
        appUserRepository.save(appUser);
       return createToken(registrationDto.getEmail(), appUser);
    }

    @Transactional
    public String confirmToken(String token){
        Optional<Token> optionalToken = tokenService.getToken(token);
        if(optionalToken.isPresent()) {
//            Token tokenExisting=optionalToken.get();
            return tokenService.confirmToken(token);
        }
        return "Token doesnt exist";
    }
}
