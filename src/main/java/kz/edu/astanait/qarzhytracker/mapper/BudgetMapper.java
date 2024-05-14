package kz.edu.astanait.qarzhytracker.mapper;

import kz.edu.astanait.qarzhytracker.domain.Budget;
import kz.edu.astanait.qarzhytracker.domain.SaveBudgetRequest;
import kz.edu.astanait.qarzhytracker.entity.BudgetEntity;
import kz.edu.astanait.qarzhytracker.entity.CategoryEntity;
import kz.edu.astanait.qarzhytracker.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BudgetMapper {

    public void mapToBudgetEntity(final SaveBudgetRequest request, final BudgetEntity budgetEntity) {
        budgetEntity.setAmount(request.amount());
        budgetEntity.setRepeatCron(request.repeatCron());
        budgetEntity.setPriority(request.priority());
        budgetEntity.setActive(request.active());
    }

    public BudgetEntity mapToBudgetEntity(final SaveBudgetRequest request, final UserEntity user, final UUID categoryId) {
        var budgetEntity = new BudgetEntity();
        budgetEntity.setId(categoryId);
        budgetEntity.setAmount(request.amount());
        budgetEntity.setRepeatCron(request.repeatCron());
        budgetEntity.setPriority(request.priority());
        budgetEntity.setActive(request.active());
        budgetEntity.setUser(user);
        return budgetEntity;
    }

    public Budget mapToBudget(final BudgetEntity budgetEntity) {
        return new Budget(
            budgetEntity.getId(),
            budgetEntity.getAmount(),
            budgetEntity.getRepeatCron(),
            budgetEntity.getPriority(),
            budgetEntity.getActive()
        );
    }
}
