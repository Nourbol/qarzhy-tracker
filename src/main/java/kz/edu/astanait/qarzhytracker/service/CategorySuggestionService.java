package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.Category;

import java.util.Optional;
import java.util.UUID;

public interface CategorySuggestionService {

    Optional<Category> suggest(String transactionDetails, UUID userId);
}
