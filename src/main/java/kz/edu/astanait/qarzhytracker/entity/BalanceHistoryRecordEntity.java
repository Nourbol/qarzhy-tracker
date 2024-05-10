package kz.edu.astanait.qarzhytracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "balance_history_records")
public class BalanceHistoryRecordEntity extends BaseEntity {

    private BigDecimal balance;
    private LocalDate recordedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
