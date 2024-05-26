package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.ErrorType;
import kz.edu.astanait.qarzhytracker.domain.UserRegistrationRequest;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.exception.AlreadyExistsException;
import kz.edu.astanait.qarzhytracker.mapper.UserMapper;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import kz.edu.astanait.qarzhytracker.service.UserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFactoryImpl implements UserFactory {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(final UserRegistrationRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new AlreadyExistsException(ErrorType.EMAIL_ALREADY_TAKEN, "Email is already taken by another user");
        }
        var encodedPassword = passwordEncoder.encode(request.password());
        var user = userRepository.save(userMapper.mapToUserEntity(request, encodedPassword));
        return userMapper.mapToUserResponse(user);
    }
}
