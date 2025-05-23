package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.domain.BankStatementType;
import kz.edu.astanait.qarzhytracker.domain.BankStatementTransaction;
import kz.edu.astanait.qarzhytracker.service.BankStatementReaderMediator;
import kz.edu.astanait.qarzhytracker.service.BankStatementReaderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BankStatementReaderMediatorImpl implements BankStatementReaderMediator {

    private final Map<BankStatementType, BankStatementReaderAdapter> bankStatementReaderAdapterMap;

    @Override
    public List<BankStatementTransaction> read(final MultipartFile statement, final BankStatementType type) throws IOException {
        var adapter = bankStatementReaderAdapterMap.get(type);
        if (adapter == null) {
            throw new IllegalStateException("There is no implementation of a adapter with the type: %s".formatted(type));
        }
        return adapter.extract(statement);
    }
}
