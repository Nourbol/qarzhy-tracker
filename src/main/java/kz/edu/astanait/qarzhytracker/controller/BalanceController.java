package kz.edu.astanait.qarzhytracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.edu.astanait.qarzhytracker.configuration.OpenApiConfig;
import kz.edu.astanait.qarzhytracker.domain.BalanceHistory;
import kz.edu.astanait.qarzhytracker.domain.BalanceHistoryRecord;
import kz.edu.astanait.qarzhytracker.domain.SaveBalanceHistoryRecordRequest;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.service.BalanceHistoryModifier;
import kz.edu.astanait.qarzhytracker.service.BalanceHistoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@Tag(name = "Balance")
@RestController
@RequestMapping("/v1/balances")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceHistoryModifier balanceHistoryModifier;
    private final BalanceHistoryReader balanceHistoryReader;

    @PostMapping(path = "/history", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Add new history record of the balance state of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    public ResponseEntity<BalanceHistoryRecord> addBalanceHistoryRecord(final @RequestBody @Valid SaveBalanceHistoryRecordRequest request,
                                                                        final @AuthenticationPrincipal UserResponse userResponse) {
        var balanceHistoryRecord = balanceHistoryModifier.addNewRecord(request, userResponse.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(balanceHistoryRecord);
    }

    @GetMapping(path = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Get the balance history of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    public ResponseEntity<BalanceHistory> getUserBalanceHistory(final @AuthenticationPrincipal UserResponse userResponse) {
        var balanceHistory = balanceHistoryReader.getUserBalanceHistory(userResponse.getId());
        return ResponseEntity.ok(balanceHistory);
    }

    @PutMapping(path = "/history/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Update the specified balance record of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    public ResponseEntity<Void> updateBalanceHistoryRecord(final @PathVariable("id") UUID recordId,
                                                           final @RequestBody @Valid SaveBalanceHistoryRecordRequest request,
                                                           final @AuthenticationPrincipal UserResponse userResponse) {
        balanceHistoryModifier.updateRecord(recordId, request, userResponse.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/history/{id}")
    @Operation(
        summary = "Delete the specified balance record of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    public ResponseEntity<Void> deleteBalanceHistoryRecord(final @PathVariable("id") UUID recordId,
                                                           final @AuthenticationPrincipal UserResponse userResponse) {
        balanceHistoryModifier.deleteRecord(recordId, userResponse.getId());
        return ResponseEntity.noContent().build();
    }
}
