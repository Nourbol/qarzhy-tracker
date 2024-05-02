package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.GeneratedToken;
import kz.edu.astanait.qarzhytracker.domain.LoginRequest;
import kz.edu.astanait.qarzhytracker.domain.UserRegistrationRequest;

public interface UserIdentityService {

    GeneratedToken register(UserRegistrationRequest registrationRequest);

    GeneratedToken login(LoginRequest request);
}
