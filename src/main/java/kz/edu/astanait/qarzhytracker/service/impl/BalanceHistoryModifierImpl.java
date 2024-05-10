package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.BalanceHistoryRecord;
import kz.edu.astanait.qarzhytracker.domain.SaveBalanceHistoryRecordRequest;
import kz.edu.astanait.qarzhytracker.entity.BalanceHistoryRecordEntity;
import kz.edu.astanait.qarzhytracker.exception.ResourceNotFoundException;
import kz.edu.astanait.qarzhytracker.mapper.BalanceHistoryMapper;
import kz.edu.astanait.qarzhytracker.repository.BalanceHistoryRepository;
import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import kz.edu.astanait.qarzhytracker.service.BalanceHistoryModifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BalanceHistoryModifierImpl implements BalanceHistoryModifier {

    private final BalanceHistoryRepository repository;
    private final BalanceHistoryMapper mapper;
    private final UserRepository userRepository;

    @Override
    public BalanceHistoryRecord addNewRecord(final SaveBalanceHistoryRecordRequest request, final UUID userId) {
        var user = userRepository.getReferenceById(userId);
        var newRecord = repository.save(mapper.mapToBalanceHistoryRecordEntity(request, user));
        return mapper.mapToBalanceHistoryRecord(newRecord);
    }

    @Override
    public void updateRecord(final UUID recordId, final SaveBalanceHistoryRecordRequest request, final UUID userId) {
        var balanceHistoryRecord = getById(recordId, userId);
        mapper.mapToBalanceHistoryEntity(request, balanceHistoryRecord);
    }

    @Override
    public void deleteRecord(UUID recordId, UUID userId) {
        var balanceHistoryRecord = getById(recordId, userId);
        repository.delete(balanceHistoryRecord);
    }

    private BalanceHistoryRecordEntity getById(UUID recordId, UUID userId) {
        return repository.findByIdAndUserId(recordId, userId)
                         .orElseThrow(() -> ResourceNotFoundException.balanceHistoryRecordNotFound(recordId));
    }
}
