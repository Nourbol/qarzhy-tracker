package kz.edu.astanait.qarzhytracker.service.impl;

import kz.edu.astanait.qarzhytracker.service.BankStatementTableFilter;
import org.springframework.stereotype.Service;
import technology.tabula.Table;

import java.util.stream.Stream;

@Service
public class KaspiBankStatementTableFilter implements BankStatementTableFilter {

    private static final long SKIP_FIRST_TABLES = 3L;

    @Override
    public Stream<Table> filterTables(final Stream<Table> tables) {
        return tables.skip(SKIP_FIRST_TABLES);
    }
}
