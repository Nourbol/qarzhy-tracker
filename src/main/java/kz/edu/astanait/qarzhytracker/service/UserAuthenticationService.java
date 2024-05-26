package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.domain.GeneratedToken;
import kz.edu.astanait.qarzhytracker.domain.LoginRequest;
import kz.edu.astanait.qarzhytracker.domain.AuthenticatedUser;

public interface UserAuthenticationService {

    GeneratedToken login(LoginRequest request);

    UserResponse getAuthenticatedUser(AuthenticatedUser user);
}
