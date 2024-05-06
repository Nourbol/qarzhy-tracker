package kz.edu.astanait.qarzhytracker.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record SaveTransactionsRequest(@NotNull @Size(min = 1)
                                      List<@Valid SaveTransactionRequest> transactions) {
}
