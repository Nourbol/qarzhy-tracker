package kz.edu.astanait.qarzhytracker.repository;

import kz.edu.astanait.qarzhytracker.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    Optional<CategoryEntity> findByIdAndUserId(UUID id, UUID userId);

    Optional<CategoryEntity> findByNameAndUserId(String name, UUID userId);

    int deleteByIdAndUserId(UUID id, UUID userId);

    Page<CategoryEntity> findAllByUserId(UUID userId, Pageable pageable);

    boolean existsByNameIgnoreCaseAndUserId(String name, UUID userId);

    @Query("""
        SELECT t.category
        FROM TransactionEntity t
        WHERE LOWER(t.details) LIKE %:details%
        AND t.user.id = :userId
        ORDER BY t.createdAt
        LIMIT 1
        """)
    Optional<CategoryEntity> findByDetailsAndUserId(String details, UUID userId);
}
