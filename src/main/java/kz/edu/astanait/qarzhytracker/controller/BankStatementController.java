package kz.edu.astanait.qarzhytracker.controller;

import kz.edu.astanait.qarzhytracker.dto.Finance;
import kz.edu.astanait.qarzhytracker.service.BankStatementFinanceExtractor;
import kz.edu.astanait.qarzhytracker.service.BankStatementTableFilter;
import kz.edu.astanait.qarzhytracker.util.PdfUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/statements")
@RequiredArgsConstructor
public class BankStatementController {

    private final BankStatementTableFilter tableFilter;
    private final BankStatementFinanceExtractor financeExtractor;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @SneakyThrows
    public List<Finance> uploadBankStatement(final @RequestParam("statement") MultipartFile statement) {
        var pdf = PDDocument.load(statement.getInputStream());
        var filteredTables = tableFilter.filterTables(PdfUtils.getTables(pdf));
        return financeExtractor.extract(filteredTables);
    }
}
