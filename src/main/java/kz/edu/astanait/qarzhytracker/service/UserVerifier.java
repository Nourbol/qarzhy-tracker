package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.UserVerificationRequest;
import kz.edu.astanait.qarzhytracker.domain.UserVerificationResponse;

public interface UserVerifier {

    UserVerificationResponse verify(UserVerificationRequest request);
}
