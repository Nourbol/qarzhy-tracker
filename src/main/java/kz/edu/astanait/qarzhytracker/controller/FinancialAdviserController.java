package kz.edu.astanait.qarzhytracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.astanait.qarzhytracker.configuration.OpenApiConfig;
import kz.edu.astanait.qarzhytracker.domain.FinancialAdvice;
import kz.edu.astanait.qarzhytracker.domain.FinancialAdviceRequest;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.service.FinancialAdviser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Financial adviser")
@RestController
@RequestMapping("/v1/advices")
@RequiredArgsConstructor
public class FinancialAdviserController {

    private final FinancialAdviser financialAdviser;

    @Operation(
        description = "Ask for a financial advice based on the statistics in the specified period of time",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FinancialAdvice> askForAdvice(final @RequestBody FinancialAdviceRequest request,
                                                        final @AuthenticationPrincipal UserResponse userResponse) {
        var advice = financialAdviser.askForAdvice(request, userResponse.getId());
        return ResponseEntity.ok(advice);
    }
}
