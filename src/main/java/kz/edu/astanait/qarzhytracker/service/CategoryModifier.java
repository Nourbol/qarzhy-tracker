package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.Category;
import kz.edu.astanait.qarzhytracker.domain.SaveCategoryRequest;
import java.util.UUID;

public interface CategoryModifier {

    Category create(SaveCategoryRequest request, UUID userId);

    void update(UUID categoryId, SaveCategoryRequest request, UUID userId);

    void delete(UUID categoryId, UUID userId);
}
