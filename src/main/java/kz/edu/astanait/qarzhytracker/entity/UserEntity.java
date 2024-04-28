package kz.edu.astanait.qarzhytracker.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
@NaturalIdCache
public class UserEntity extends BaseEntity {

    private String name;
    @NaturalId
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<TransactionEntity> transactions = new ArrayList<>();

    public void addTransactions(final List<TransactionEntity> transactions) {
        transactions.forEach(this::addTransaction);
    }

    public void addTransaction(final TransactionEntity transaction) {
        if (transaction != null) {
            transaction.setUser(this);
            this.transactions.add(transaction);
        }
    }
}
