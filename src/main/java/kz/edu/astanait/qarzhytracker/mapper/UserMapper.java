package kz.edu.astanait.qarzhytracker.mapper;

import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.domain.UserRegistrationRequest;
import kz.edu.astanait.qarzhytracker.domain.AuthenticatedUser;
import kz.edu.astanait.qarzhytracker.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity mapToUserEntity(final UserRegistrationRequest registrationRequest,
                                      final String hashedPassword) {
        var user = new UserEntity();
        user.setName(registrationRequest.name());
        user.setEmail(registrationRequest.email());
        user.setPassword(hashedPassword);
        return user;
    }

    public UserResponse mapToUserResponse(final UserEntity userEntity) {
        return new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.isVerified());
    }

    public UserResponse mapToUserResponse(final AuthenticatedUser authenticatedUser) {
        return new UserResponse(authenticatedUser.getId(), authenticatedUser.getName(), authenticatedUser.getEmail(), authenticatedUser.isVerified());
    }

    public AuthenticatedUser mapToAuthenticatedUser(final UserEntity userEntity) {
        return new AuthenticatedUser(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.isVerified());
    }
}
