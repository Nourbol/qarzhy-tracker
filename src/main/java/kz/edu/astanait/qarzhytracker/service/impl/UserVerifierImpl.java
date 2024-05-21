package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.UserVerificationRequest;
import kz.edu.astanait.qarzhytracker.domain.UserVerificationResponse;
import kz.edu.astanait.qarzhytracker.exception.ExpiredVerificationCodeException;
import kz.edu.astanait.qarzhytracker.exception.ExceededAttemptsException;
import kz.edu.astanait.qarzhytracker.exception.ResourceNotFoundException;
import kz.edu.astanait.qarzhytracker.repository.UserVerificationRepository;
import kz.edu.astanait.qarzhytracker.service.UserVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional
public class UserVerifierImpl implements UserVerifier {

    private final UserVerificationRepository userVerificationRepository;
    private final Clock clock;
    private final int maxVerificationAttempts;

    public UserVerifierImpl(final UserVerificationRepository userVerificationRepository,
                            final Clock clock,
                            final @Value("${qarzhy-tracker.user-activation.verification.max-attempts}") int maxVerificationAttempts) {
        this.userVerificationRepository = userVerificationRepository;
        this.clock = clock;
        this.maxVerificationAttempts = maxVerificationAttempts;
    }

    public UserVerificationResponse verify(final UserVerificationRequest request) {
        var userId = request.userId();
        var userVerification = userVerificationRepository.findById(userId)
                                                         .orElseThrow(() -> ResourceNotFoundException.verificationCodeNotFound(userId));
        if (userVerification.getAttempts() >= maxVerificationAttempts) {
            throw new ExceededAttemptsException("Exceeded maximum verification attempts");
        }
        if (userVerification.getExpiredAt().isBefore(LocalDateTime.now(clock))) {
            throw new ExpiredVerificationCodeException("Verification code is expired");
        }
        userVerification.incrementAttempts();
        if (Objects.equals(userVerification.getCode(), request.verificationCode())) {
            userVerification.makeUserVerified();
            userVerificationRepository.delete(userVerification);
            return UserVerificationResponse.valid();
        }
        return UserVerificationResponse.invalid(maxVerificationAttempts - userVerification.getAttempts());
    }
}
