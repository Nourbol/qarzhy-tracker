package kz.edu.astanait.qarzhytracker.service;

import kz.edu.astanait.qarzhytracker.domain.BankStatementType;
import kz.edu.astanait.qarzhytracker.domain.Finance;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface BankStatementReaderAdapter {

    List<Finance> extract(MultipartFile statement) throws IOException;

    BankStatementType getType();
}
