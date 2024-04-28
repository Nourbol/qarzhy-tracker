package kz.edu.astanait.qarzhytracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.astanait.qarzhytracker.domain.BankStatementType;
import kz.edu.astanait.qarzhytracker.domain.Transaction;
import kz.edu.astanait.qarzhytracker.service.BankStatementUploader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Tag(name = "Bank statement")
@RestController
@RequestMapping("/v1/statements")
@RequiredArgsConstructor
public class BankStatementController {

    private final BankStatementUploader bankStatementReaderMediator;

    @SneakyThrows
    @Operation(summary = "Save transactions from a bank statement")
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Transaction> uploadBankStatement(final @RequestParam("statement") MultipartFile statement,
                                                 final @RequestParam("type") BankStatementType type,
                                                 final @AuthenticationPrincipal UserDetails userDetails) {
        return bankStatementReaderMediator.upload(statement, type, userDetails);
    }
}
