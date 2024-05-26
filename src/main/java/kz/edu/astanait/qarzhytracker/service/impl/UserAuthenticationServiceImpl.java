package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.domain.GeneratedToken;
import kz.edu.astanait.qarzhytracker.domain.LoginRequest;
import kz.edu.astanait.qarzhytracker.domain.AuthenticatedUser;
import kz.edu.astanait.qarzhytracker.exception.ResourceNotFoundException;
import kz.edu.astanait.qarzhytracker.mapper.UserMapper;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import kz.edu.astanait.qarzhytracker.service.TokenFactory;
import kz.edu.astanait.qarzhytracker.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenFactory tokenFactory;

    @Override
    public GeneratedToken login(final LoginRequest request) {
        var email = request.email();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.password()));
        var user = userRepository.findByEmail(email)
                                 .orElseThrow(() -> new ResourceNotFoundException("User with email %s was not found".formatted(email)));
        return tokenFactory.create(user.getId());
    }

    @Override
    public UserResponse getAuthenticatedUser(final AuthenticatedUser user) {
        return userMapper.mapToUserResponse(user);
    }
}
