package kz.edu.astanait.qarzhytracker.service;

import technology.tabula.Table;

import java.util.stream.Stream;

public interface BankStatementTableFilter {

    Stream<Table> filterTables(Stream<Table> tables);
}
