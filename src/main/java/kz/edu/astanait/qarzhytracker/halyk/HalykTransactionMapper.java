package kz.edu.astanait.qarzhytracker.halyk;

import kz.edu.astanait.qarzhytracker.domain.Finance;
import kz.edu.astanait.qarzhytracker.domain.TableRow;
import kz.edu.astanait.qarzhytracker.mapper.TableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HalykTransactionMapper {

    private final TableMapper tableMapper;

    public List<Finance> mapToFinances(final List<TableRow> rows) {
        return rows.stream()
            .map(this::mapToFinance)
            .toList();
    }

    public Finance mapToFinance(final TableRow row) {
        var halykTransaction = tableMapper.mapToObject(row, HalykTransaction.class);
        return mapToFinance(halykTransaction);
    }

    public Finance mapToFinance(final HalykTransaction transaction) {
        var amount = transaction.expense()
            .add(transaction.revenue())
            .add(transaction.commission());
        return new Finance(transaction.operationData(), amount, transaction.details());
    }
}
