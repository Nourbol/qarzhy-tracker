package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.Budget;
import kz.edu.astanait.qarzhytracker.domain.SaveBudgetRequest;
import java.util.UUID;

public interface BudgetModifier {

    Budget create(SaveBudgetRequest request, UUID categoryId, UUID userId);

    void update(UUID categoryId, SaveBudgetRequest request, UUID userId);

    void delete(UUID id, UUID userId);
}
