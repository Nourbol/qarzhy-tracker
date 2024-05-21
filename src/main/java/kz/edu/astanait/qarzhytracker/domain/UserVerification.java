package kz.edu.astanait.qarzhytracker.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserVerification(UUID userId, LocalDateTime expireAt) {
}
