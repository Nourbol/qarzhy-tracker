package kz.edu.astanait.qarzhytracker.mapper;

import kz.edu.astanait.qarzhytracker.domain.AuthenticatedUser;
import kz.edu.astanait.qarzhytracker.domain.UserRegistrationRequest;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
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
        return new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.isVerified());
    }

    public AuthenticatedUser mapToAuthenticatedUser(final UserResponse userResponse) {
        return new AuthenticatedUser(userResponse.getId(), userResponse.getName(), userResponse.getEmail());
    }
}
