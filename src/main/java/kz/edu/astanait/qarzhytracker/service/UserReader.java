package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.AuthenticatedUser;

import java.util.Optional;

public interface UserReader {

    AuthenticatedUser getByEmail(String token);

    Optional<AuthenticatedUser> getByToken(String token);
}
