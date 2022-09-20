package com.example.movieclub.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class AppUserRegistrationDto {
    @Email
    private String email;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*(!_#)?).{5,200}",
            message = "Password must have minimum 5 chars,containt capital letter, small letter," +
                    "digit and may have special char like '! _ #'")
    private String password;

}
