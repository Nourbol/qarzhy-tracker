package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.dto.Finance;
import technology.tabula.Table;

import java.util.List;
import java.util.stream.Stream;

public interface BankStatementFinanceExtractor {

    List<Finance> extract(Stream<Table> tableStream);
}
