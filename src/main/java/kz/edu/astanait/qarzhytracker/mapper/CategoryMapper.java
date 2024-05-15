package kz.edu.astanait.qarzhytracker.mapper;

import kz.edu.astanait.qarzhytracker.domain.Budget;
import kz.edu.astanait.qarzhytracker.domain.Category;
import kz.edu.astanait.qarzhytracker.domain.SaveCategoryRequest;
import kz.edu.astanait.qarzhytracker.entity.BudgetEntity;
import kz.edu.astanait.qarzhytracker.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final BudgetMapper budgetMapper;

    public CategoryEntity mapToCategoryEntity(final SaveCategoryRequest request) {
        var category = new CategoryEntity();
        category.setName(request.name());
        return category;
    }

    public void mapToCategoryEntity(final SaveCategoryRequest request, final CategoryEntity categoryEntity) {
        categoryEntity.setName(request.name());
    }

    public Category mapToCategory(final CategoryEntity categoryEntity) {
        return new Category(categoryEntity.getId(), categoryEntity.getName());
    }

    public Category mapToCategory(final CategoryEntity categoryEntity, final BudgetEntity budgetEntity) {
        var budget = budgetMapper.mapToBudget(budgetEntity);
        return new Category(categoryEntity.getId(), categoryEntity.getName(), budget);
    }

    public Category mapToCategory(final CategoryEntity categoryEntity, final Budget budget) {
        return new Category(categoryEntity.getId(), categoryEntity.getName(), budget);
    }
}
