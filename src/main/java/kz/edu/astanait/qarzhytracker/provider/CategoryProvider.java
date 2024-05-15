package kz.edu.astanait.qarzhytracker.provider;

import kz.edu.astanait.qarzhytracker.entity.CategoryEntity;
import kz.edu.astanait.qarzhytracker.exception.ResourceNotFoundException;
import kz.edu.astanait.qarzhytracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryProvider {

    private final CategoryRepository categoryRepository;

    public CategoryEntity getUserCategoryById(final UUID id, final UUID userId) {
        return categoryRepository.findByIdAndUserId(id, userId)
                                 .orElseThrow(() -> ResourceNotFoundException.categoryNotFound(id));
    }
}
