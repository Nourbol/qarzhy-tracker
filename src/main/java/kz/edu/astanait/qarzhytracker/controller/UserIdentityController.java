package kz.edu.astanait.qarzhytracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.astanait.qarzhytracker.configuration.OpenApiConfig;
import kz.edu.astanait.qarzhytracker.domain.AuthenticatedUser;
import kz.edu.astanait.qarzhytracker.domain.GeneratedToken;
import kz.edu.astanait.qarzhytracker.domain.LoginRequest;
import kz.edu.astanait.qarzhytracker.domain.UserVerificationRequest;
import kz.edu.astanait.qarzhytracker.domain.UserVerificationResponse;
import kz.edu.astanait.qarzhytracker.domain.UserRegistrationRequest;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.service.UserVerifier;
import kz.edu.astanait.qarzhytracker.service.UserAuthenticationService;
import kz.edu.astanait.qarzhytracker.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Identity")
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserIdentityController {

    private final UserRegistrationService userRegistrationService;
    private final UserAuthenticationService userAuthenticationService;
    private final UserVerifier userVerifier;

    @Operation(summary = "Register as a new user")
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> register(final @RequestBody UserRegistrationRequest registrationRequest) {
        var user = userRegistrationService.register(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(summary = "Authenticate as a user")
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneratedToken> createToken(final @RequestBody LoginRequest loginRequest) {
        var generatedToken = userAuthenticationService.login(loginRequest);
        return ResponseEntity.ok(generatedToken);
    }

    @Operation(summary = "Verify a new user")
    @PostMapping(path = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserVerificationResponse> activateUser(final @RequestBody UserVerificationRequest request) {
        var response = userVerifier.verify(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get the authenticated user information", security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticatedUser> getAuthenticatedUser(final @AuthenticationPrincipal UserResponse userResponse) {
        var authenticatedUser = userAuthenticationService.getAuthenticatedUser(userResponse);
        return ResponseEntity.ok(authenticatedUser);
    }
}
