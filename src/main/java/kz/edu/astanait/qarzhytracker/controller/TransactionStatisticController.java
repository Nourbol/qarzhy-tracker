package kz.edu.astanait.qarzhytracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.astanait.qarzhytracker.configuration.OpenApiConfig;
import kz.edu.astanait.qarzhytracker.domain.CategorizedFinancialOverview;
import kz.edu.astanait.qarzhytracker.domain.GeneralFinancialSummary;
import kz.edu.astanait.qarzhytracker.domain.AuthenticatedUser;
import kz.edu.astanait.qarzhytracker.service.TransactionStatisticReader;
import lombok.RequiredArgsConstructor;
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

    @Operation(
        summary = "Get general financial summary of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneralFinancialSummary> getUserTransactionStatisticInRange(final @RequestParam("from") LocalDate from,
                                                                                      final @RequestParam("to") LocalDate to,
                                                                                      final @AuthenticationPrincipal AuthenticatedUser user) {
        var transactionStatistic = transactionStatisticReader.getUserGeneralFinancialSummaryInRange(user.getId(), from, to);
        return ResponseEntity.ok(transactionStatistic);
    }

    @Operation(
        summary = "Get categories financial overview of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    @GetMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategorizedFinancialOverview> getUserCategoriesFinancialOverview(final @RequestParam("from") LocalDate from,
                                                                                           final @RequestParam("to") LocalDate to,
                                                                                           final @AuthenticationPrincipal AuthenticatedUser user) {
        var categoriesFinancialOverview = transactionStatisticReader.getUserCategorizedFinancialOverview(user.getId(), from, to);
        return ResponseEntity.ok(categoriesFinancialOverview);
    }
}
