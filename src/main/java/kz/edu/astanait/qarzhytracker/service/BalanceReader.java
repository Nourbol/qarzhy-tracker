package kz.edu.astanait.qarzhytracker.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface BalanceReader {

    BigDecimal getUserBalance(UUID userId);
}
