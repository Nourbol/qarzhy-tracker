package kz.edu.astanait.qarzhytracker.repository;

import kz.edu.astanait.qarzhytracker.entity.UserVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserVerificationRepository extends JpaRepository<UserVerificationEntity, UUID> {
}
