package com.example.movieclub.domain.user.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public void saveToken(Token token){
        tokenRepository.save(token);
    }

    public String createToken(){
        String token;
        do{
           token = UUID.randomUUID().toString();

        }while (tokenRepository.existsByToken(token));
        return token;
    }

    @Transactional
    public String confirmToken(String token){
        Token confirmationToken = tokenRepository.findByToken(token).orElseThrow();
        LocalDateTime confirmationDate=LocalDateTime.now();
        if(confirmationToken.getConfirmedAt()!=null){
            throw new IllegalStateException("email already confirmed");
        }

        if(confirmationToken.getExpiresAt().isBefore(confirmationDate)){
            throw new IllegalStateException("token has expired");
        }
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        return "confirmed";
    }
}
