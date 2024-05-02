package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.GeneratedToken;
import kz.edu.astanait.qarzhytracker.entity.TokenEntity;
import kz.edu.astanait.qarzhytracker.repository.TokenRepository;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import kz.edu.astanait.qarzhytracker.service.TokenFactory;
import kz.edu.astanait.qarzhytracker.util.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenFactoryImpl implements TokenFactory {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final Clock clock;
    private final Duration expireAfter;

    public TokenFactoryImpl(final UserRepository userRepository,
                            final TokenRepository tokenRepository,
                            final Clock clock,
                            final @Value("${spring.security.token.expire-after}") Duration expireAfter) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.clock = clock;
        this.expireAfter = expireAfter;
    }

    @Override
    public GeneratedToken create(final UUID userId) {
        var plainText = TokenUtils.createPlainTextToken();
        var hashedToken = TokenUtils.hash(plainText);
        var user = userRepository.getReferenceById(userId);
        var expiredAt = LocalDateTime.now(clock).plus(expireAfter);
        var token = new TokenEntity(hashedToken, expiredAt, user);
        tokenRepository.save(token);
        return new GeneratedToken(new String(plainText, StandardCharsets.UTF_8), expiredAt);
    }
}
