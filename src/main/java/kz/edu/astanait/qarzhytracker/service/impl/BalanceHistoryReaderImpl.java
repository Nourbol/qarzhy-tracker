package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.BalanceHistory;
import kz.edu.astanait.qarzhytracker.domain.BalanceHistoryRecord;
import kz.edu.astanait.qarzhytracker.exception.ResourceNotFoundException;
import kz.edu.astanait.qarzhytracker.mapper.BalanceHistoryMapper;
import kz.edu.astanait.qarzhytracker.repository.BalanceHistoryRepository;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import kz.edu.astanait.qarzhytracker.service.BalanceHistoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BalanceHistoryReaderImpl implements BalanceHistoryReader {

    private final UserRepository userRepository;
    private final BalanceHistoryRepository repository;
    private final BalanceHistoryMapper mapper;

    @Override
    public BalanceHistory getUserBalanceHistory(final UUID userId) {
        var user = userRepository.findById(userId).orElseThrow(() -> ResourceNotFoundException.userNotFound(userId));
        var balanceHistory = user.getBalanceHistory();
        return mapper.mapToBalanceHistory(balanceHistory);
    }

    @Override
    public Optional<BalanceHistoryRecord> getUserLastBalanceHistoryRecord(final UUID userId) {
        return repository.findFirstByUserIdOrderByRecordedAtDesc(userId)
                         .map(mapper::mapToBalanceHistoryRecord);
    }
}
