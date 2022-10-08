package com.example.movieclub.domain.verifyCode;


import com.example.movieclub.domain.email.EmailSender;
import com.example.movieclub.domain.user.AppUser;
import com.example.movieclub.domain.user.AppUserRepository;
import com.example.movieclub.domain.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class CodeService {
    private final CodeRepository codeRepository;
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;
    private final EmailSender emailSender;


    private String createCode(){
        StringBuilder code = new StringBuilder();
        do{
            List<Integer> integers = IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList()).subList(1,6);
            Collections.shuffle(integers);
            integers.stream().map(Object::toString).forEach(code::append);
        }while(codeRepository.existsByCode(code.toString()));
        return code.toString();
    }
    @Transactional
    public String saveCode(String email){
        codeRepository.deleteAllByAppUser_Email(email);
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow();
        Code code = new Code();
        code.setCode(createCode());
        code.setAppUser(appUser);
        code.setCreatedAt(LocalDateTime.now());
        code.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        codeRepository.save(code);
        String codeToConfirm = code.getCode();
        emailSender.sendEmailWithCode(email,codeToConfirm);
        return codeToConfirm;
    }

    @Transactional
    public String updateCode(String code,String email,String password){
        Optional<Code> optionalCode = codeRepository.findByCode(code);
        if(optionalCode.isPresent()){
            Code userCode = optionalCode.get();
            LocalDateTime expiresAt = userCode.getExpiresAt();
            LocalDateTime now = LocalDateTime.now();
            if(expiresAt.isBefore(now)){
                return "Code has been expired";
            }
            userCode.setConfirmedAt(now);
            appUserService.changePassword(email,password);
            return "Password changed successfully";
        }
        return "Code does not exist";
    }
}
