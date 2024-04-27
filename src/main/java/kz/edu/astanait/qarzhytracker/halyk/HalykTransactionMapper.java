package kz.edu.astanait.qarzhytracker.halyk;

import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.domain.TableRow;
import kz.edu.astanait.qarzhytracker.mapper.TableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HalykTransactionMapper {

    private final TableMapper tableMapper;

    public List<BankStatementTransaction> mapToTransactions(final List<TableRow> rows) {
        return rows.stream()
            .map(this::mapToTransaction)
            .toList();
    }

    public BankStatementTransaction mapToTransaction(final TableRow row) {
        var halykTransaction = tableMapper.mapToObject(row, HalykTransaction.class);
        return mapToTransaction(halykTransaction);
    }

    public BankStatementTransaction mapToTransaction(final HalykTransaction transaction) {
        var amount = transaction.expense()
            .add(transaction.revenue())
            .add(transaction.commission());
        return new BankStatementTransaction(transaction.operationData(), amount, transaction.details());
    }
}
