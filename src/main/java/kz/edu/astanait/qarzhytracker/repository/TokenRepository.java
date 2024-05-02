package kz.edu.astanait.qarzhytracker.repository;

import kz.edu.astanait.qarzhytracker.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {

    Optional<TokenEntity> findByHashAndExpiredAtAfter(byte[] hash, LocalDateTime expiredAtBefore);
}
