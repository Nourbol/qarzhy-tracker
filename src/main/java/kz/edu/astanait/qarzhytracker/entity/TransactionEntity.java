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
@Table(name = "transactions")
public class TransactionEntity extends BaseEntity {

    private LocalDate operationDate;
    private BigDecimal amount;
    private String details;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
