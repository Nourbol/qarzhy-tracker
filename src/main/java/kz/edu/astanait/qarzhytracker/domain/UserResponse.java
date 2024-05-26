package kz.edu.astanait.qarzhytracker.domain;

import java.util.UUID;

public record UserResponse(UUID id,
                           String name,
                           String email,
                           boolean verified) {
}
