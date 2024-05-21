package kz.edu.astanait.qarzhytracker.domain;

import java.time.LocalDateTime;

public record TemporaryVerificationCode(String verificationCode, LocalDateTime expireAt) {
}
