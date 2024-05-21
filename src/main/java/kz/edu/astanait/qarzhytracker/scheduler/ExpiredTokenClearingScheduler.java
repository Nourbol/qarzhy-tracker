package kz.edu.astanait.qarzhytracker.scheduler;

import kz.edu.astanait.qarzhytracker.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class ExpiredTokenClearingScheduler {

    private final TokenRepository repository;
    private final Clock clock;
    private final Duration deleteExpiredAfter;

    public ExpiredTokenClearingScheduler(final TokenRepository repository,
                                         final Clock clock,
                                         final @Value("${qarzhy-tracker.tokens.delete-expired-after}") Duration deleteExpiredAfter) {
        this.repository = repository;
        this.clock = clock;
        this.deleteExpiredAfter = deleteExpiredAfter;
    }

    @Transactional
    @Scheduled(fixedDelay = 15L, timeUnit = TimeUnit.MINUTES)
    public void clearExpiredTokens() {
        repository.deleteAllByExpiredAtBefore(LocalDateTime.now(clock).minus(deleteExpiredAfter));
    }
}
