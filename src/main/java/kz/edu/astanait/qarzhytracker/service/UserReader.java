package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import java.util.Optional;

public interface UserReader {

    UserResponse getByEmail(String token);

    Optional<UserResponse> getByToken(String token);
}
