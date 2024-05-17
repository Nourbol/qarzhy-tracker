package kz.edu.astanait.qarzhytracker.repository;

import kz.edu.astanait.qarzhytracker.domain.CategorySummary;
import kz.edu.astanait.qarzhytracker.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID>, JpaSpecificationExecutor<TransactionEntity> {

    Optional<TransactionEntity> findByIdAndUserId(UUID transactionId, UUID userId);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM TransactionEntity t WHERE t.operationDate > :from AND t.user.id = :userId")
    BigDecimal sumAmountStartingOperationDateFrom(UUID userId, LocalDate from);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM TransactionEntity t WHERE t.user.id = :userId")
    BigDecimal sumAmount(UUID userId);

    @Query("""
        SELECT COALESCE(SUM(t.amount), 0)
        FROM TransactionEntity t
        WHERE t.amount < 0
        AND t.user.id = :userId
        AND t.operationDate BETWEEN :from AND :to
        """)
    BigDecimal sumExpensesInRange(UUID userId, LocalDate from, LocalDate to);

    @Query("""
        SELECT COALESCE(SUM(t.amount), 0)
        FROM TransactionEntity t
        WHERE t.amount > 0
        AND t.user.id = :userId
        AND t.operationDate BETWEEN :from AND :to
        """)
    BigDecimal sumRevenuesInRange(UUID userId, LocalDate from, LocalDate to);

    @Query("""
        SELECT new kz.edu.astanait.qarzhytracker.domain.CategorySummary(t.category.name, SUM(t.amount))
        FROM TransactionEntity t
        WHERE t.amount < 0
        AND t.user.id = :userId
        AND t.operationDate BETWEEN :from AND :to
        GROUP BY t.category
        """)
    List<CategorySummary> getUserCategoryExpensesInRange(UUID userId, LocalDate from, LocalDate to);

    @Query("""
        SELECT new kz.edu.astanait.qarzhytracker.domain.CategorySummary(t.category.name, SUM(t.amount))
        FROM TransactionEntity t
        WHERE t.amount > 0
        AND t.user.id = :userId
        AND t.operationDate BETWEEN :from AND :to
        GROUP BY t.category
        """)
    List<CategorySummary> getUserCategoryRevenuesInRange(UUID userId, LocalDate from, LocalDate to);
}
