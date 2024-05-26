package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.UserRegistrationRequest;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.service.UserFactory;
import kz.edu.astanait.qarzhytracker.service.UserRegistrationService;
import kz.edu.astanait.qarzhytracker.service.VerificationCodeSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserFactory userFactory;
    private final VerificationCodeSender verificationCodeSender;

    @Override
    public UserResponse register(final UserRegistrationRequest registrationRequest) {
        var user = userFactory.create(registrationRequest);
        verificationCodeSender.sendForUser(user.id(), user.email());
        return user;
    }
}
