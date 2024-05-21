package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.UserRegistrationRequest;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;

public interface UserFactory {

    UserResponse create(UserRegistrationRequest request);
}
