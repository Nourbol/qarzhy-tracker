package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.BankStatementType;
import kz.edu.astanait.qarzhytracker.domain.TransactionSuggestion;
import kz.edu.astanait.qarzhytracker.domain.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BankStatementUploader {

    List<TransactionSuggestion> upload(MultipartFile statement, BankStatementType type, UserResponse userDetails) throws IOException;
}
