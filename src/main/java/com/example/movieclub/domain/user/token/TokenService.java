package com.example.movieclub.domain.user.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    public Optional<Token> getToken(String token){
        return tokenRepository.findByToken(token);
    }
    public String createToken() {
        String token;
        do {
            token = UUID.randomUUID().toString();
        } while (tokenRepository.existsByToken(token));
        return token;
    }

    public void deleteOldToken(String email) {
        tokenRepository.deleteByAppUser_Email(email);
    }

    @Transactional
    public String confirmToken(String token) {
        Token confirmationToken = tokenRepository.findByToken(token).orElseThrow();
        LocalDateTime confirmationDate = LocalDateTime.now();
        if (confirmationToken.getConfirmedAt() != null) {
            return "Email already confirmed";
        }

        if (confirmationToken.getExpiresAt().isBefore(confirmationDate)) {
            return "Token has expired";
        }
        confirmationToken.setConfirmedAt(confirmationDate);
        confirmationToken.getAppUser().setEnabled(true);
        return "Account confirmed";
    }
}
