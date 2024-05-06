package kz.edu.astanait.qarzhytracker.specification;

import kz.edu.astanait.qarzhytracker.domain.TransactionFilter;
import kz.edu.astanait.qarzhytracker.domain.TransactionType;
import kz.edu.astanait.qarzhytracker.entity.TransactionEntity;
import kz.edu.astanait.qarzhytracker.entity.TransactionEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class TransactionSearchSpecificationBuilder {

    public Specification<TransactionEntity> buildSpecification(final TransactionFilter filter) {
        return byDetails(filter.search())
            .and(byOperationDateRange(filter.from(), filter.to()))
            .and(byType(filter.type()));
    }

    public Specification<TransactionEntity> byDetails(final String details) {
        return SpecificationBuilder.containsText(TransactionEntity_.details, details)
                                   .returnEmptyIfInvalidSearch(details)
                                   .build();
    }

    public Specification<TransactionEntity> byOperationDateRange(final LocalDate from, final LocalDate to) {
        return SpecificationBuilder
            .between(TransactionEntity_.operationDate, from, to)
            .build();
    }

    public Specification<TransactionEntity> byType(final TransactionType type) {
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
