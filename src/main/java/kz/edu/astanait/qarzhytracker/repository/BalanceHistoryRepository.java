package kz.edu.astanait.qarzhytracker.repository;

import kz.edu.astanait.qarzhytracker.entity.BalanceHistoryRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BalanceHistoryRepository extends JpaRepository<BalanceHistoryRecordEntity, UUID> {

    Optional<BalanceHistoryRecordEntity> findByIdAndUserId(UUID id, UUID userId);

    Optional<BalanceHistoryRecordEntity> findFirstByUserIdOrderByRecordedAtDesc(UUID userId);
}
