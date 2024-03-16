package kz.edu.astanait.qarzhytracker.controller;

import kz.edu.astanait.qarzhytracker.service.BankStatementFinanceExtractor;
import kz.edu.astanait.qarzhytracker.service.BankStatementTableFilter;
import kz.edu.astanait.qarzhytracker.util.PdfUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class DemoController {

    private final BankStatementTableFilter tableFilter;
    private final BankStatementFinanceExtractor financeExtractor;

    @GetMapping("/upload-form")
    public String showUploadForm() {
        return "uploadForm";
    }

    @PostMapping("/upload-file")
    @SneakyThrows
    public String handleFileUpload(final @RequestParam("file") MultipartFile file,
                                   final Model model) {
        var pdf = PDDocument.load(file.getInputStream());
        var filteredTables = tableFilter.filterTables(PdfUtils.getTables(pdf));
        var extractedFinances = financeExtractor.extract(filteredTables);
        model.addAttribute("financeList", extractedFinances);
        return "financeList";
    }
}
