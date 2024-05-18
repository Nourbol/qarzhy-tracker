package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.BankStatementType;
import kz.edu.astanait.qarzhytracker.domain.TransactionSuggestion;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import kz.edu.astanait.qarzhytracker.service.BankStatementReaderMediator;
import kz.edu.astanait.qarzhytracker.service.BankStatementUploader;
import kz.edu.astanait.qarzhytracker.service.TransactionSuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankStatementUploaderImpl implements BankStatementUploader {

    private final BankStatementReaderMediator bankStatementReaderMediator;
    private final TransactionSuggestionService transactionSuggestionService;

    public List<TransactionSuggestion> upload(final MultipartFile statement,
                                              final BankStatementType type,
                                              final UserResponse user) throws IOException {
        var bankStatementTransactions = bankStatementReaderMediator.read(statement, type);
        return transactionSuggestionService.suggest(bankStatementTransactions, user.getId());
    }
}
