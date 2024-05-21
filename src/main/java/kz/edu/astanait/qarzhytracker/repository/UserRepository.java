package kz.edu.astanait.qarzhytracker.repository;

import kz.edu.astanait.qarzhytracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmailIgnoreCase(String email);

    void deleteAllByVerifiedIsFalseAndCreatedAtBefore(LocalDateTime createdAtBefore);
}
