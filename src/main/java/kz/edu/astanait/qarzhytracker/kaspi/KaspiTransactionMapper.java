package kz.edu.astanait.qarzhytracker.kaspi;

import kz.edu.astanait.qarzhytracker.domain.Finance;
import kz.edu.astanait.qarzhytracker.domain.FinanceType;
import kz.edu.astanait.qarzhytracker.util.BigDecimalUtils;
import org.springframework.stereotype.Component;

@Component
public class KaspiTransactionMapper {

    private static final String PLUS_SIGN = "+";

    public Finance mapToFinance(final KaspiTransaction transaction) {
        var amount = BigDecimalUtils.fromString(transaction.amount());
        var type = transaction.amount().contains(PLUS_SIGN) ? FinanceType.REVENUE : FinanceType.EXPENSE;
        return new Finance(transaction.date(), amount, type, transaction.details());
    }
}
