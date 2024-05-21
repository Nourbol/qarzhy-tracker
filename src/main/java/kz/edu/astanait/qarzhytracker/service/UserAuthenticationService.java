package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.AuthenticatedUser;
import kz.edu.astanait.qarzhytracker.domain.GeneratedToken;
import kz.edu.astanait.qarzhytracker.domain.LoginRequest;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;

public interface UserAuthenticationService {

    GeneratedToken login(LoginRequest request);

    AuthenticatedUser getAuthenticatedUser(UserResponse userResponse);
}
