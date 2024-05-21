package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.TemporaryVerificationCode;
import kz.edu.astanait.qarzhytracker.service.VerificationCodeGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class VerificationCodeGeneratorImpl implements VerificationCodeGenerator {

    private static final int DIGITS_EXCLUSIVE_UPPER_BOUND = 10;

    private final int passwordLength;
    private final Duration expireAfter;
    private final Clock clock;

    public VerificationCodeGeneratorImpl(final @Value("${qarzhy-tracker.user-activation.verification.code-length}") int verificationCodeLength,
                                         final @Value("${qarzhy-tracker.user-activation.verification.expire-after}") Duration expireAfter,
                                         final Clock clock) {
        this.passwordLength = verificationCodeLength;
        this.expireAfter = expireAfter;
        this.clock = clock;
    }

    public String generateVerificationCode() {
        return ThreadLocalRandom.current()
                                .ints(passwordLength, 0, DIGITS_EXCLUSIVE_UPPER_BOUND)
                                .mapToObj(Integer::toString)
                                .collect(Collectors.joining());
    }

    public TemporaryVerificationCode generateTemporaryVerificationCode() {
        return new TemporaryVerificationCode(generateVerificationCode(), LocalDateTime.now(clock).plus(expireAfter));
    }
}
