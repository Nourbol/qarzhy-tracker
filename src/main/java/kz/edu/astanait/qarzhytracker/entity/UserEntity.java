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
import java.util.Objects;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<TokenEntity> tokens = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<BalanceHistoryRecordEntity> balanceHistory = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<CategoryEntity> categories = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<BudgetEntity> budgets = new ArrayList<>();

    public void addCategory(final CategoryEntity category) {
        if (category != null) {
            this.categories.add(category);
            category.setUser(this);
        }
    }

    public void addTransactions(final List<TransactionEntity> transactions) {
        transactions.forEach(this::addTransaction);
    }

    public void addTransaction(final TransactionEntity transaction) {
        if (transaction != null) {
            this.transactions.add(transaction);
            transaction.setUser(this);
        }
    }

    @Override
    public int hashCode() {
        return this.email != null ? this.email.hashCode() : super.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof UserEntity that) {
            return Objects.equals(getEmail(), that.getEmail());
        }
        return false;
    }
}
