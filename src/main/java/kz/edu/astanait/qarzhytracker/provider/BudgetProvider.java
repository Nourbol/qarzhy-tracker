package kz.edu.astanait.qarzhytracker.provider;

import kz.edu.astanait.qarzhytracker.entity.BaseEntity;
import kz.edu.astanait.qarzhytracker.entity.BudgetEntity;
import kz.edu.astanait.qarzhytracker.entity.CategoryEntity;
import kz.edu.astanait.qarzhytracker.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BudgetProvider {

    private final BudgetRepository budgetRepository;

    public Map<UUID, BudgetEntity> getIdBudgetMap(final Streamable<CategoryEntity> categories) {
        var categoryIds = categories.stream()
                                    .map(BaseEntity::getId)
                                    .toList();
        return budgetRepository.findAllById(categoryIds)
                               .stream()
                               .collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    }
}
