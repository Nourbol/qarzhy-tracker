package kz.edu.astanait.qarzhytracker.domain;

import java.time.LocalDateTime;

public record GeneratedToken(String token,
                             LocalDateTime expiredAt) {
}
