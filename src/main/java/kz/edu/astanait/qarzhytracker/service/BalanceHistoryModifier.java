package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.BalanceHistoryRecord;
import kz.edu.astanait.qarzhytracker.domain.SaveBalanceHistoryRecordRequest;
import java.util.UUID;

public interface BalanceHistoryModifier {

    BalanceHistoryRecord addNewRecord(SaveBalanceHistoryRecordRequest request, UUID userId);

    void updateRecord(UUID recordId, SaveBalanceHistoryRecordRequest request, UUID userId);

    void deleteRecord(UUID recordId, UUID userId);
}
