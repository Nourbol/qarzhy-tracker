package kz.edu.astanait.qarzhytracker.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends User {

    private final UUID id;
    private final String name;
    private final String email;

    public UserResponse(final UUID id,
                        final String name,
                        final String email,
                        final String password) {
        super(email, password, Collections.emptyList());
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
