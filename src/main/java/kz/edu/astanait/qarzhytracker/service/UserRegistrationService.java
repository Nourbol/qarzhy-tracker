package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.UserRegistrationRequest;

public interface UserRegistrationService {

    void register(final UserRegistrationRequest registrationRequest);
}
