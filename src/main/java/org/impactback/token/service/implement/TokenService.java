package org.impactback.token.service.implement;

import org.impactback.mail.service.implement.SendMessageService;
import org.impactback.token.entity.Token;
import org.impactback.token.repository.TokenRepository;
import org.impactback.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Isaac F. B. C.
 * @since 7/11/2024 - 5:21 PM
 */
@Service
public class TokenService {
    private final TokenRepository tokenRepository;
    private final SendMessageService emailService;
    @Autowired
    public TokenService(TokenRepository tokenRepository, SendMessageService emailService) {
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }
    public Token findByToken(String token) {
        return tokenRepository.findTokenByToken(token);
    }

}
