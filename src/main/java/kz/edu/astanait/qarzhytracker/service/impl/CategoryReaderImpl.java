package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.Category;
import kz.edu.astanait.qarzhytracker.mapper.CategoryMapper;
import kz.edu.astanait.qarzhytracker.provider.BudgetProvider;
import kz.edu.astanait.qarzhytracker.repository.CategoryRepository;
import kz.edu.astanait.qarzhytracker.service.CategoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryReaderImpl implements CategoryReader {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final BudgetProvider budgetProvider;

    @Override
    public Page<Category> getUserCategories(final UUID userId, final Pageable pageable) {
        var categories = categoryRepository.findAllByUserId(userId, pageable);
        var idBudgetMap = budgetProvider.getIdBudgetMap(categories);
        return categories.map(category -> {
            var categoryId = category.getId();
            return idBudgetMap.containsKey(categoryId)
                ? categoryMapper.mapToCategory(category, idBudgetMap.get(categoryId))
                : categoryMapper.mapToCategory(category);
        });
    }
}
