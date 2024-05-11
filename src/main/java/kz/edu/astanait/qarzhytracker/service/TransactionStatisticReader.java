package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.TransactionStatistic;
import java.time.LocalDate;
import java.util.UUID;

public interface TransactionStatisticReader {

    TransactionStatistic getUserStatisticInRange(UUID userId, LocalDate from, LocalDate to);
}
