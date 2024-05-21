package kz.edu.astanait.qarzhytracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_verifications")
public class UserVerificationEntity extends BaseEntity {
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private LocalDateTime expiredAt;
    @Column(nullable = false)
    private int attempts;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @MapsId("id")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserVerificationEntity(final UUID userId, final String code, final LocalDateTime expireAt) {
        this.code = code;
        this.expiredAt = expireAt;
        setId(userId);
    }

    public void makeUserVerified() {
        user.setVerified(true);
    }

    public void incrementAttempts() {
        setAttempts(attempts + 1);
    }
}
