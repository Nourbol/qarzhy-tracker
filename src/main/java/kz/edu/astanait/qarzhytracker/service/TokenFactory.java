package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.GeneratedToken;
import java.util.UUID;

public interface TokenFactory {

    GeneratedToken create(UUID userId);
}
