package kz.edu.astanait.qarzhytracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.edu.astanait.qarzhytracker.annotation.TransactionFilterAsQueryParam;
import kz.edu.astanait.qarzhytracker.configuration.OpenApiConfig;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionRequest;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionsRequest;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.domain.TransactionFilter;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.service.TransactionModifier;
import kz.edu.astanait.qarzhytracker.service.TransactionReader;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.util.List;
import java.util.UUID;

@Tag(name = "Transactions")
@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionModifier transactionModifier;
    private final TransactionReader transactionReader;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Create multiple transactions for the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    public ResponseEntity<List<Transaction>> create(final @RequestBody @Valid SaveTransactionsRequest request,
                                                    final @AuthenticationPrincipal UserResponse userResponse) {
        var savedTransactions = transactionModifier.create(request, userResponse.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransactions);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Get transactions of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    @PageableAsQueryParam
    @TransactionFilterAsQueryParam
    public ResponseEntity<Page<Transaction>> getUserTransactions(final @Parameter(hidden = true) TransactionFilter filter,
                                                                 final @Parameter(hidden = true) Pageable pageable,
                                                                 final @AuthenticationPrincipal UserResponse userResponse) {
        var transactions = transactionReader.getUserTransactions(userResponse.getId(), filter, pageable);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Get the specified transaction of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    public ResponseEntity<Transaction> getUserTransaction(final @PathVariable("id") UUID transactionId,
                                                          final @AuthenticationPrincipal UserResponse userResponse) {
        var transaction = transactionReader.getUserTransaction(transactionId, userResponse.getId());
        return ResponseEntity.ok(transaction);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Update the specified transaction of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    public ResponseEntity<Void> update(final @PathVariable("id") UUID transactionId,
                                       final @RequestBody @Valid SaveTransactionRequest request,
                                       final @AuthenticationPrincipal UserResponse userResponse) {
        transactionModifier.update(transactionId, userResponse.getId(), request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    @Operation(
        summary = "Delete the specified transaction of the authenticated user",
        security = @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    )
    public ResponseEntity<Void> delete(final @PathVariable("id") UUID transactionId,
                                       final @AuthenticationPrincipal UserResponse userResponse) {
        transactionModifier.delete(transactionId, userResponse.getId());
        return ResponseEntity.noContent().build();
    }
}
