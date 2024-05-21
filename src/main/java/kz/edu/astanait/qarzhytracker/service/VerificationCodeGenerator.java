package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.TemporaryVerificationCode;

public interface VerificationCodeGenerator {

    String generateVerificationCode();

    TemporaryVerificationCode generateTemporaryVerificationCode();
}
