package kz.edu.astanait.qarzhytracker.kaspi;

import kz.edu.astanait.qarzhytracker.converter.MoneyAmountConverter;
import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.TableRow;
import kz.edu.astanait.qarzhytracker.mapper.TableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KaspiTransactionMapper {

    private final MoneyAmountConverter moneyAmountConverter;
    private final TableMapper tableMapper;

    public List<BankStatementTransaction> mapToTransactions(final List<TableRow> rows) {
        return rows.stream()
            .map(this::mapToTransaction)
            .toList();
    }

    public BankStatementTransaction mapToTransaction(final TableRow row) {
        var kaspiTransaction = tableMapper.mapToObject(row, KaspiTransaction.class);
        return mapToTransaction(kaspiTransaction);
    }

    public BankStatementTransaction mapToTransaction(final KaspiTransaction transaction) {
        var amount = moneyAmountConverter.convert(transaction.amount());
        return new BankStatementTransaction(transaction.date(), amount, transaction.details());
    }
}
