package kz.edu.astanait.qarzhytracker.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserVerificationResponse(Boolean isValid,
                                       Integer remainingAttempts) {

    public static UserVerificationResponse invalid(int remainingAttempts) {
        return new UserVerificationResponse(false, remainingAttempts);
    }

    public static UserVerificationResponse valid() {
        return new UserVerificationResponse(true, null);
    }
}
