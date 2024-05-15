package kz.edu.astanait.qarzhytracker.specification;

import kz.edu.astanait.qarzhytracker.domain.TransactionFilter;
import kz.edu.astanait.qarzhytracker.domain.TransactionType;
import kz.edu.astanait.qarzhytracker.entity.BaseEntity_;
import kz.edu.astanait.qarzhytracker.entity.TransactionEntity;
import kz.edu.astanait.qarzhytracker.entity.TransactionEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class TransactionSearchSpecificationBuilder {

    public Specification<TransactionEntity> buildSpecification(final TransactionFilter filter, final UUID userId) {
        return hasUser(userId)
            .and(containsDetails(filter.search()))
            .and(hasOperationInDateRange(filter.from(), filter.to()))
            .and(hasType(filter.type()));
    }

    public Specification<TransactionEntity> hasUser(final UUID userId) {
        return SpecificationBuilder.<TransactionEntity, UUID>isEqualTo(root -> root.get(TransactionEntity_.user).get(BaseEntity_.id), userId)
                                   .build();
    }

    public Specification<TransactionEntity> containsDetails(final String details) {
        return SpecificationBuilder.containsText(TransactionEntity_.details, details)
                                   .returnEmptyIfInvalidSearch(details)
                                   .build();
    }

    public Specification<TransactionEntity> hasOperationInDateRange(final LocalDate from, final LocalDate to) {
        return SpecificationBuilder.between(TransactionEntity_.operationDate, from, to)
            .build();
    }

    public Specification<TransactionEntity> hasType(final TransactionType type) {
        return SpecificationBuilder.<TransactionEntity>wrap(
            (root, query, criteriaBuilder) -> {
                var path = root.get(TransactionEntity_.amount);
                return type == TransactionType.EXPENSE
                    ? criteriaBuilder.lessThan(path, BigDecimal.ZERO)
                    : criteriaBuilder.greaterThanOrEqualTo(path, BigDecimal.ZERO);
            }
        ).returnEmptyIfNull(type).build();
    }
}
