package kz.edu.astanait.qarzhytracker.domain;

import java.util.UUID;

public record AuthenticatedUser(UUID id,
                                String name,
                                String email) {
}
