package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.Category;
import kz.edu.astanait.qarzhytracker.domain.SaveCategoryRequest;
import kz.edu.astanait.qarzhytracker.exception.AlreadyExistsException;
import kz.edu.astanait.qarzhytracker.exception.ResourceNotFoundException;
import kz.edu.astanait.qarzhytracker.mapper.CategoryMapper;
import kz.edu.astanait.qarzhytracker.repository.CategoryRepository;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import kz.edu.astanait.qarzhytracker.service.BudgetModifier;
import kz.edu.astanait.qarzhytracker.service.CategoryModifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryModifierImpl implements CategoryModifier {

    private final UserRepository userRepository;
    private final BudgetModifier budgetModifier;
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public Category create(final SaveCategoryRequest request, final UUID userId) {
        var user = userRepository.findById(userId)
                                 .orElseThrow(() -> ResourceNotFoundException.userNotFound(userId));
        if (repository.existsByNameIgnoreCaseAndUserId(request.name(), userId)) {
            throw AlreadyExistsException.existingCategoryName();
        }
        var category = mapper.mapToCategoryEntity(request);
        user.addCategory(category);
        var budgetRequest = request.budget();
        return Objects.nonNull(budgetRequest)
            ? mapper.mapToCategory(category, budgetModifier.create(request.budget(), category.getId(), userId))
            : mapper.mapToCategory(category);
    }

    @Override
    public void update(final UUID categoryId, final SaveCategoryRequest request, final UUID userId) {
        var category = repository.findByIdAndUserId(categoryId, userId)
                                 .orElseThrow(() -> ResourceNotFoundException.categoryNotFound(categoryId));
        var newCategoryName = request.name();
        if (!category.getName().equalsIgnoreCase(newCategoryName) && repository.existsByNameIgnoreCaseAndUserId(newCategoryName, userId)) {
            throw AlreadyExistsException.existingCategoryName();
        }
        mapper.mapToCategoryEntity(request, category);
        var budgetRequest = request.budget();
        if (Objects.nonNull(budgetRequest)) {
            budgetModifier.update(categoryId, budgetRequest, userId);
        } else {
            budgetModifier.delete(categoryId, userId);
        }
    }

    @Override
    public void delete(final UUID categoryId, final UUID userId) {
        var affectedRows = repository.deleteByIdAndUserId(categoryId, userId);
        if (affectedRows == 0) {
            throw ResourceNotFoundException.categoryNotFound(categoryId);
        }
    }
}
