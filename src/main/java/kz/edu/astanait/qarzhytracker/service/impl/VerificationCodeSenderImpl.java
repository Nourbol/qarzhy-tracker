package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.UserVerification;
import kz.edu.astanait.qarzhytracker.entity.UserVerificationEntity;
import kz.edu.astanait.qarzhytracker.repository.UserVerificationRepository;
import kz.edu.astanait.qarzhytracker.service.EmailSender;
import kz.edu.astanait.qarzhytracker.service.VerificationCodeGenerator;
import kz.edu.astanait.qarzhytracker.service.VerificationCodeSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class VerificationCodeSenderImpl implements VerificationCodeSender {

    private final EmailSender emailSender;
    private final VerificationCodeGenerator verificationCodeGenerator;
    private final UserVerificationRepository repository;

    @Override
    public UserVerification sendForUser(final UUID userId, final String email) {
        var temporaryVerificationCode = verificationCodeGenerator.generateTemporaryVerificationCode();
        var verificationCode = temporaryVerificationCode.verificationCode();
        var expireAt = temporaryVerificationCode.expireAt();
        emailSender.sendVerificationCode(email, verificationCode);
        var userVerificationCodeEntity = new UserVerificationEntity(userId, verificationCode, expireAt);
        repository.save(userVerificationCodeEntity);
        return new UserVerification(userId, expireAt);
    }
}
