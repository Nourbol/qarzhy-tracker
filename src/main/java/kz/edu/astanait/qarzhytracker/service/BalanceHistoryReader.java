package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.BalanceHistory;
import kz.edu.astanait.qarzhytracker.domain.BalanceHistoryRecord;
import java.util.Optional;
import java.util.UUID;

public interface BalanceHistoryReader {

    BalanceHistory getUserBalanceHistory(UUID userId);

    Optional<BalanceHistoryRecord> getUserLastBalanceHistoryRecord(UUID userId);
}
