package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.Budget;
import kz.edu.astanait.qarzhytracker.domain.SaveBudgetRequest;
import kz.edu.astanait.qarzhytracker.mapper.BudgetMapper;
import kz.edu.astanait.qarzhytracker.repository.BudgetRepository;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import kz.edu.astanait.qarzhytracker.service.BudgetModifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BudgetModifierImpl implements BudgetModifier {

    private final UserRepository userRepository;
    private final BudgetRepository repository;
    private final BudgetMapper mapper;

    @Override
    public Budget create(final SaveBudgetRequest request, final UUID categoryId, final UUID userId) {
        var user = userRepository.getReferenceById(userId);
        var budget = mapper.mapToBudgetEntity(request, user, categoryId);
        repository.incrementPrioritiesLowerOrEqual(request.priority(), userId);
        repository.save(budget);
        return mapper.mapToBudget(budget);
    }

    @Override
    public void update(final UUID categoryId, final SaveBudgetRequest request, final UUID userId) {
        repository.findByIdAndUserId(categoryId, userId)
                  .ifPresentOrElse(
                            budget -> {
                                var currentPriority = budget.getPriority();
                                var newPriority = request.priority();
                                if (!Objects.equals(currentPriority, newPriority)) {
                                    repository.incrementPrioritiesLowerOrEqual(newPriority, userId);
                                    repository.decrementPrioritiesLowerOrEqual(currentPriority, userId);
                                }
                                mapper.mapToBudgetEntity(request, budget);
                                repository.save(budget);
                            },
                            () -> create(request, categoryId, userId)
                        );
    }

    @Override
    public void delete(final UUID id, final UUID userId) {
        repository.findByIdAndUserId(id, userId)
                  .ifPresent(repository::delete);
    }
}
