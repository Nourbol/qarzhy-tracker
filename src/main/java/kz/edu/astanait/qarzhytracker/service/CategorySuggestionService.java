package kz.edu.astanait.qarzhytracker.service;

import java.util.Optional;
import java.util.UUID;

public interface CategorySuggestionService {

    Optional<String> suggest(String transactionDetails, UUID userId);
}
