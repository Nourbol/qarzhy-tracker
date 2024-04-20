package kz.edu.astanait.qarzhytracker.configuration;

import kz.edu.astanait.qarzhytracker.domain.BankStatementType;
import kz.edu.astanait.qarzhytracker.service.BankStatementReaderAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class BankStatementReaderConfig {

    @Bean
    public Map<BankStatementType, BankStatementReaderAdapter> bankStatementMap(final Collection<BankStatementReaderAdapter> adapters) {
        return adapters.stream().collect(Collectors.toMap(BankStatementReaderAdapter::getType, Function.identity()));
    }
}
