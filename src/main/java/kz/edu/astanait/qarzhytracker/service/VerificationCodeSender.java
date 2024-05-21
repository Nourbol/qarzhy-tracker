package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.UserVerification;

import java.util.UUID;

public interface VerificationCodeSender {

    UserVerification sendForUser(UUID userId, String email);
}
