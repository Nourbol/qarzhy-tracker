package kz.edu.astanait.qarzhytracker.repository;

import kz.edu.astanait.qarzhytracker.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.UUID;

public interface BudgetRepository extends JpaRepository<BudgetEntity, UUID> {

    Optional<BudgetEntity> findByIdAndUserId(UUID id, UUID userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE BudgetEntity b SET b.priority = b.priority - 1 WHERE b.priority > :priority AND b.user.id = :userId")
    void decrementPrioritiesLowerOrEqual(int priority, UUID userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE BudgetEntity b SET b.priority = b.priority + 1 WHERE b.priority >= :priority AND b.user.id = :userId")
    void incrementPrioritiesLowerOrEqual(int priority, UUID userId);
}
