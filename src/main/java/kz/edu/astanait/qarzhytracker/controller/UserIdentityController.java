package kz.edu.astanait.qarzhytracker.controller;

import kz.edu.astanait.qarzhytracker.domain.GeneratedToken;
import kz.edu.astanait.qarzhytracker.domain.LoginRequest;
import kz.edu.astanait.qarzhytracker.domain.UserRegistrationRequest;
import kz.edu.astanait.qarzhytracker.service.UserIdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserIdentityController {

    private final UserIdentityService userIdentityService;

    @PostMapping("/register")
    public ResponseEntity<GeneratedToken> register(final @RequestBody UserRegistrationRequest registrationRequest) {
        var generatedToken = userIdentityService.register(registrationRequest);
        return ResponseEntity.ok(generatedToken);
    }

    @PostMapping("/login")
    public ResponseEntity<GeneratedToken> createToken(final @RequestBody LoginRequest loginRequest) {
        var generatedToken = userIdentityService.login(loginRequest);
        return ResponseEntity.ok(generatedToken);
    }
}
