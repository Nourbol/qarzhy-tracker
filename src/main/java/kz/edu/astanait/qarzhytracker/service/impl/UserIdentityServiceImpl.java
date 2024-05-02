package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.GeneratedToken;
import kz.edu.astanait.qarzhytracker.domain.LoginRequest;
import kz.edu.astanait.qarzhytracker.domain.UserRegistrationRequest;
import kz.edu.astanait.qarzhytracker.exception.AlreadyExistsException;
import kz.edu.astanait.qarzhytracker.exception.ResourceNotFoundException;
import kz.edu.astanait.qarzhytracker.mapper.UserMapper;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import kz.edu.astanait.qarzhytracker.service.TokenFactory;
import kz.edu.astanait.qarzhytracker.service.UserIdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserIdentityServiceImpl implements UserIdentityService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenFactory tokenFactory;

    @Override
    public GeneratedToken register(final UserRegistrationRequest registrationRequest) {
        if (userRepository.existsByEmail(registrationRequest.email())) {
            throw new AlreadyExistsException("Email is already taken by another user");
        }
        var encodedPassword = passwordEncoder.encode(registrationRequest.password());
        var user = userRepository.save(userMapper.mapToUserEntity(registrationRequest, encodedPassword));
        return tokenFactory.create(user.getId());
    }

    @Override
    public GeneratedToken login(final LoginRequest request) {
        var email = request.email();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.password()));
        var user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User with email %s was not found".formatted(email)));
        return tokenFactory.create(user.getId());
    }
}
