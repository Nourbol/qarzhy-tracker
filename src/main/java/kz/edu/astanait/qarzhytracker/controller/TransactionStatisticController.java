package kz.edu.astanait.qarzhytracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.astanait.qarzhytracker.configuration.OpenApiConfig;
import kz.edu.astanait.qarzhytracker.domain.TransactionStatistic;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.service.TransactionStatisticReader;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@Tag(name = "Transaction statistics")
@RestController
@RequestMapping("/v1/transactions/statistics")
@RequiredArgsConstructor
public class TransactionStatisticController {

    private final TransactionStatisticReader transactionStatisticReader;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Get transaction statistic of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    @PageableAsQueryParam
    public ResponseEntity<TransactionStatistic> getUserTransactionStatisticInRange(final @RequestParam("from") LocalDate from,
                                                                                   final @RequestParam("to") LocalDate to,
                                                                                   final @AuthenticationPrincipal UserResponse userResponse) {
        var transactionStatistic = transactionStatisticReader.getUserStatisticInRange(userResponse.getId(), from, to);
        return ResponseEntity.ok(transactionStatistic);
    }
}
