package kz.edu.astanait.qarzhytracker.domain;

import java.util.UUID;

public record UserVerificationRequest(UUID userId, String verificationCode) {
}
