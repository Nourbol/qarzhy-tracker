package kz.edu.astanait.qarzhytracker.mapper;

import kz.edu.astanait.qarzhytracker.domain.BalanceHistory;
import kz.edu.astanait.qarzhytracker.domain.BalanceHistoryRecord;
import kz.edu.astanait.qarzhytracker.domain.SaveBalanceHistoryRecordRequest;
import kz.edu.astanait.qarzhytracker.entity.BalanceHistoryRecordEntity;
import kz.edu.astanait.qarzhytracker.entity.UserEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BalanceHistoryMapper {

    public BalanceHistoryRecordEntity mapToBalanceHistoryRecordEntity(final SaveBalanceHistoryRecordRequest request, final UserEntity user) {
        var balanceHistoryRecordEntity = new BalanceHistoryRecordEntity();
        balanceHistoryRecordEntity.setBalance(request.balance());
        balanceHistoryRecordEntity.setRecordedAt(request.recordedAt());
        balanceHistoryRecordEntity.setUser(user);
        return balanceHistoryRecordEntity;
    }

    public BalanceHistory mapToBalanceHistory(final List<BalanceHistoryRecordEntity> balanceHistory) {
        return new BalanceHistory(mapToBalanceHistoryRecords(balanceHistory));
    }

    public List<BalanceHistoryRecord> mapToBalanceHistoryRecords(final List<BalanceHistoryRecordEntity> balanceHistory) {
        return balanceHistory.stream()
            .map(this::mapToBalanceHistoryRecord)
            .toList();
    }

    public BalanceHistoryRecord mapToBalanceHistoryRecord(final BalanceHistoryRecordEntity balanceHistoryRecordEntity) {
        return new BalanceHistoryRecord(
            balanceHistoryRecordEntity.getId(),
            balanceHistoryRecordEntity.getBalance(),
            balanceHistoryRecordEntity.getRecordedAt()
        );
    }

    public void mapToBalanceHistoryEntity(final SaveBalanceHistoryRecordRequest request, final BalanceHistoryRecordEntity entity) {
        entity.setBalance(request.balance());
        entity.setRecordedAt(request.recordedAt());
    }
}
