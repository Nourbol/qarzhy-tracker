package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.entity.CategoryEntity;
import kz.edu.astanait.qarzhytracker.repository.CategoryRepository;
import kz.edu.astanait.qarzhytracker.service.CategorySuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategorySuggestionServiceImpl implements CategorySuggestionService {

    private final CategoryRepository categoryRepository;

    @Override
    @Cacheable("suggested-category")
    public Optional<String> suggest(final String transactionDetails, final UUID userId) {
        return categoryRepository.findByDetailsAndUserId(transactionDetails.toLowerCase(), userId)
                                 .map(CategoryEntity::getName);
    }
}
