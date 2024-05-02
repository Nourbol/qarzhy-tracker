package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.entity.TokenEntity;
import kz.edu.astanait.qarzhytracker.mapper.UserMapper;
import kz.edu.astanait.qarzhytracker.repository.TokenRepository;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import kz.edu.astanait.qarzhytracker.service.UserReader;
import kz.edu.astanait.qarzhytracker.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {

    private final TokenRepository tokenRepository;
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final Clock clock;

    @Override
    public UserResponse getByEmail(String email) {
        return repository.findByEmail(email)
            .map(userMapper::mapToUserResponse)
            .orElseThrow(() -> new UsernameNotFoundException("User with email %s was not found".formatted(email)));
    }

    @Override
    public Optional<UserResponse> getByToken(final String token) {
        var hashedToken = TokenUtils.hash(token);
        return tokenRepository.findByHashAndExpiredAtAfter(hashedToken, LocalDateTime.now(clock))
            .map(TokenEntity::getUser)
            .map(userMapper::mapToUserResponse);
    }
}
