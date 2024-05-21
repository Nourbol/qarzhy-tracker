package kz.edu.astanait.qarzhytracker.scheduler;

import kz.edu.astanait.qarzhytracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class UnverifiedUserClearingScheduler {

    private final UserRepository userRepository;
    private final Clock clock;
    private final Duration deleteInactiveAfter;

    public UnverifiedUserClearingScheduler(final UserRepository userRepository,
                                           final Clock clock,
                                           final @Value("${qarzhy-tracker.user-activation.delete-unverified-after}") Duration deleteUnverifiedAfter) {
        this.userRepository = userRepository;
        this.clock = clock;
        this.deleteInactiveAfter = deleteUnverifiedAfter;
    }

    @Transactional
    @Scheduled(fixedDelay = 1L, timeUnit = TimeUnit.MINUTES)
    public void clearUnverifiedUsers() {
        userRepository.deleteAllByVerifiedIsFalseAndCreatedAtBefore(LocalDateTime.now(clock).minus(deleteInactiveAfter));
    }
}
