package kz.edu.astanait.qarzhytracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import kz.edu.astanait.qarzhytracker.annotation.TransactionFilterAsQueryParam;
import kz.edu.astanait.qarzhytracker.domain.SaveTransactionsRequest;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.domain.TransactionFilter;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.service.TransactionFactory;
import kz.edu.astanait.qarzhytracker.service.TransactionReader;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionFactory transactionFactory;
    private final TransactionReader transactionReader;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create multiple transactions for the authenticated user", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Transaction>> create(final @RequestBody @Valid SaveTransactionsRequest request,
                                                    final @AuthenticationPrincipal UserResponse userResponse) {
        var savedTransactions = transactionFactory.create(request, userResponse.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransactions);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get transactions of the authenticated user", security = @SecurityRequirement(name = "bearerAuth"))
    @PageableAsQueryParam
    @TransactionFilterAsQueryParam
    public ResponseEntity<Page<Transaction>> getUsersTransactions(final @Parameter(hidden = true) TransactionFilter filter,
                                                                  final @Parameter(hidden = true) Pageable pageable,
                                                                  final @AuthenticationPrincipal UserResponse userResponse) {
        var transactions = transactionReader.getUserTransactions(userResponse.getId(), filter, pageable);
        return ResponseEntity.ok(transactions);
    }
}
